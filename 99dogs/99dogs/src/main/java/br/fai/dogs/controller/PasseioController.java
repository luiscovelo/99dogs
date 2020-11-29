package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.email.MailStrategy;
import br.fai.dogs.email.Mailtrap;
import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.PasseioCachorro;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.TransacaoPicpay;
import br.fai.dogs.payment.picpay.PaymentPicpayService;
import br.fai.dogs.service.CachorroService;
import br.fai.dogs.service.ConfiguracaoPicpayService;
import br.fai.dogs.service.FormaDePagamentoService;
import br.fai.dogs.service.PasseioCachorroService;
import br.fai.dogs.service.PasseioService;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("/passeio")
public class PasseioController {
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private CachorroService cachorroService;
	
	@Autowired
	private FormaDePagamentoService formaDePagamentoService;
	
	@Autowired
	private PasseioCachorroService passeioCachorroService;
	
	@Autowired
	private ConfiguracaoPicpayService configuracaoPicpayService;
	
	@Autowired
	private PaymentPicpayService paymentPicpayService;
	
	@Autowired
	private HttpSession session;
	
	private MailStrategy sendMail;
	
	public PasseioController() {
		if(this.sendMail == null) {
			this.sendMail = new Mailtrap();
		}
	}
	
	@GetMapping("/cliente/meus-passeios")
	public String getListaDePasseiosPorCliente(Model model) {
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();
			List<Passeio> passeios = new ArrayList<Passeio>();
			
			passeios = passeioService.passeiosPorCliente(cliente_id);
					
			model.addAttribute("passeios", passeios);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/passeio/meus-passeios";
	}
	
	@GetMapping("/cliente/solicitar-passeio")
	public String getPageSolicitarPasseio(Model model) {
				
		try {
			
			List<Pessoa> profissionais = pessoaService.readAllProfissional();
			model.addAttribute("profissionais", profissionais);
					
			List<FormaDePagamento> formasDePagamento = formaDePagamentoService.readAll();
			model.addAttribute("formasDePagamento", formasDePagamento);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/passeio/solicitar-passeio";
		
	}
	
	@PostMapping("/cliente/post-solicitar-passeio")
	public String postSolicitarPasseio(Passeio passeio, BindingResult bindingResult) {
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();

			passeio.setClienteId(cliente_id);
			passeio.setStatus("Espera");
					
			Long idPasseio = passeioService.create(passeio);
					
			if(idPasseio != null && idPasseio != 0) {
				return "redirect:/passeio/cliente/adicionar-cachorro-ao-passeio/" + idPasseio;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/passeio/cliente/solicitar-passeio";
		
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/cliente/adicionar-cachorro-ao-passeio/{id}")
	public String getPageAdicionarCachorroAoPasseio(@PathVariable("id") Long id, Model model) {
		
		Map<String, Object> responseRequest = new HashMap<>();
		List<Cachorro> cachorros            = new ArrayList<Cachorro>();
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();
			
			responseRequest = cachorroService.cachorrosPorCliente(cliente_id);

			if(responseRequest.get("hasError").equals(false)) {
				cachorros = (List<Cachorro>) responseRequest.get("response");
			} else {
				throw new Exception(responseRequest.get("message").toString());
			}
			
			model.addAttribute("passeioId", id);
			model.addAttribute("cachorros", cachorros);
			
		} catch (Exception e) {
			model.addAttribute("responseError", e.getMessage());
		}
		
		return "/cliente/passeio/adicionar-cachorro-ao-passeio";
		
	}
	
	@PostMapping("/cliente/post-adicionar-cachorro-ao-passeio")
	public String postAdicionarCachorroAoPasseio(PasseioCachorro passeioCachorro) {
		
		try {
			
			Passeio passeio = passeioService.readById(Long.valueOf(passeioCachorro.getPasseioId()));
			boolean response = passeioCachorroService.create(passeioCachorro);
			
			if(response == true) {
				
				boolean integracaoPagamento = verificaIntegracaoDePagamento(passeio);
				
				if(integracaoPagamento) {
					return "redirect:/pagamento/cliente/dados-do-pagamento/" + passeioCachorro.getPasseioId();
				}
				
				sendMail.passeioSolicitado(passeio);
				return "redirect:/passeio/cliente/detalhes/" + passeioCachorro.getPasseioId();
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/passeio/cliente/adicionar-cachorro-ao-passeio/" + passeioCachorro.getPasseioId();
		
	}
	
	@GetMapping("/cliente/detalhes/{id}")
	public String getPageDetalhesDoPasseioCliente(@PathVariable("id") Long id, Model model) {
		
		try {
			
			Passeio passeio = passeioService.readById(id);
			List<Cachorro> cachorros = passeioCachorroService.readByPasseioId(id);
			List<TransacaoPicpay> transacaoPicpay = paymentPicpayService.readByPasseioId(id);
			Map<Double, Double> localizacoes = passeioService.localizacao(id);
			
			JSONObject jsonLocations = new JSONObject(localizacoes);
			
			if(passeio.getProfissional().getPessoa().getFoto() != null) {
				
				Pessoa profissional = new Pessoa();
				
				profissional = passeio.getProfissional().getPessoa();
				
				if(profissional.getFoto() != null) {
					profissional.setBase64Foto(Base64.getEncoder().encodeToString(profissional.getFoto()));
				}
				
				passeio.getProfissional().setPessoa(profissional);
				
			}
			
			if(passeio.getCliente().getPessoa().getFoto() != null) {
				
				Pessoa cliente = new Pessoa();
				
				cliente = passeio.getCliente().getPessoa();
				
				if(cliente.getFoto() != null) {
					cliente.setBase64Foto(Base64.getEncoder().encodeToString(cliente.getFoto()));
				}
				
				passeio.getCliente().setPessoa(cliente);
				
			}
			
			model.addAttribute("passeio", passeio);
			model.addAttribute("cachorros", cachorros);
			model.addAttribute("transacaoPicpay", transacaoPicpay);
			model.addAttribute("jsonLocations", jsonLocations);
			model.addAttribute("localizacoes", localizacoes);
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/passeio/detalhes";
		
	}
	
	@GetMapping("/profissional/meus-passeios")
	public String getListaDePasseiosPorProfissional(Model model) {
		
		try {
			
			Long profissional = Helper.getSessao(session).getId();
			List<Passeio> passeios = new ArrayList<Passeio>();
			
			passeios = passeioService.passeiosPorProfissional(profissional);
			
			model.addAttribute("passeios", passeios);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/passeio/meus-passeios";
	}
	
	@GetMapping("/profissional/minha-agenda")
	public String getPageMinhaAgenda(Model model) {
		
		try {
			
			Long profissional = Helper.getSessao(session).getId();
			List<Passeio> passeios = new ArrayList<Passeio>();
					
			passeios = passeioService.passeiosPorProfissional(profissional);
			
			Map<String, String> map = new HashMap<>();
			Stack<JSONObject> jsonPasseios = new Stack<JSONObject>();
			
			for(Passeio passeio: passeios) {
				
				map.put("title", passeio.getCliente().getPessoa().getNome().toString());
				map.put("start", passeio.getDatahora().toString());
				map.put("url", "/passeio/profissional/detalhes/" + passeio.getId());
				
				JSONObject json = new JSONObject(map);
				jsonPasseios.push(json);
				
			}
			
			String diaPrimeiroDoMes = Helper.getDataAtual("yyyy-MM-dd");
			
			model.addAttribute("jsonPasseios", jsonPasseios);
			model.addAttribute("diaPrimeiroDoMes", diaPrimeiroDoMes);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/passeio/minha-agenda";
		
	}
	
	@GetMapping("/profissional/detalhes/{id}")
	public String getPageDetalhesDoPasseioProfissional(@PathVariable("id") Long id, Model model) {
		
		try {
			
			Passeio passeio = passeioService.readById(id);
			List<Cachorro> cachorros = passeioCachorroService.readByPasseioId(passeio.getId());
			List<TransacaoPicpay> transacaoPicpay = paymentPicpayService.readByPasseioId(id);
			Map<Double, Double> localizacoes = passeioService.localizacao(id);
			
			JSONObject jsonLocations = new JSONObject(localizacoes);
			
			if(passeio.getProfissional().getPessoa().getFoto() != null) {
				
				Pessoa profissional = new Pessoa();
				
				profissional = passeio.getProfissional().getPessoa();
				
				if(profissional.getFoto() != null) {
					profissional.setBase64Foto(Base64.getEncoder().encodeToString(profissional.getFoto()));
				}
				
				passeio.getProfissional().setPessoa(profissional);
				
			}
			
			if(passeio.getCliente().getPessoa().getFoto() != null) {
				
				Pessoa cliente = new Pessoa();
				
				cliente = passeio.getCliente().getPessoa();
				
				if(cliente.getFoto() != null) {
					cliente.setBase64Foto(Base64.getEncoder().encodeToString(cliente.getFoto()));
				}
				
				passeio.getCliente().setPessoa(cliente);
				
			}
			
			model.addAttribute("passeio", passeio);
			model.addAttribute("cachorros", cachorros);
			model.addAttribute("transacaoPicpay", transacaoPicpay);
			model.addAttribute("jsonLocations", jsonLocations);
			model.addAttribute("localizacoes", localizacoes);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
				
		return "/profissional/passeio/detalhes";
		
	}
	
	@GetMapping("/profissional/aprovar-passeio/{id}")
	public String aprovarPasseio(@PathVariable("id") Long id) {
		
		try {
			
			Passeio passeioDetalhes = passeioService.readById(id);
			Passeio passeio = new Passeio();
			
			passeio.setId(id);
			passeio.setStatus("Aprovado");
			
			boolean response = passeioService.alterarStatus(passeio);
			
			if(response) {
				sendMail.passeioAprovado(passeioDetalhes);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/passeio/profissional/detalhes/" + id;
				
	}
	
	@GetMapping("/profissional/recusar-passeio/{id}")
	public String recusarPasseio(@PathVariable("id") Long id) {
		
		try {
			
			Passeio passeioDetalhes = passeioService.readById(id);
			Passeio passeio = new Passeio();
			
			passeio.setId(id);
			passeio.setStatus("Recusado");
			
			boolean response = passeioService.alterarStatus(passeio);
			
			if(response) {
				sendMail.passeioRecusado(passeioDetalhes);
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/passeio/profissional/detalhes/" + id;
				
	}
	
	@GetMapping("/profissional/finalizar-passeio/{id}")
	public String finaliarPasseio(@PathVariable("id") Long id) {
		
		try {
			
			Passeio passeio = new Passeio();
			
			passeio.setId(id);
			passeio.setStatus("Finalizado");
			
			boolean response = passeioService.alterarStatus(passeio);
			
			if(response) {
				
				Passeio passeioDetalhes = passeioService.readById(id);
				if(passeioDetalhes != null) {
					sendMail.passeioFinalizado(passeioDetalhes);
				}
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/passeio/profissional/detalhes/" + id;
				
	}
	
	@GetMapping("/cliente/verificar-disponibilidade/{datahora}/{id}")
	public HttpEntity<Boolean> verificarDisponibilidade(@PathVariable("datahora") String datahora, @PathVariable("id") Long id){
				
		try {
			
			boolean response = passeioService.verificarDisponibilidade(datahora, id);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ResponseEntity.ok(null);
		
	}
	
	public boolean verificaIntegracaoDePagamento(Passeio entity) {
		
		boolean response = false;
		
		try {
			
			if(entity.getFormaDePagamentoId() == 3) {
				
				ConfiguracaoPicpay configPicpay = configuracaoPicpayService.readByProfissionalId(entity.getProfissionalId());
				
				if(configPicpay != null && configPicpay.isAtivo()) {
					
					response = true;
					
				}else {
					response = false;
				}
				
			}else {
				response = false;
			}
			
		} catch (Exception e) {
			System.out.println("Problema pra verificar integração do pagamento: " + e.getMessage());
		}
		
		return response;
		
	}
	
}
