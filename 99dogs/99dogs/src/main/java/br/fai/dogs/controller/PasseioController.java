package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.PasseioCachorro;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.CachorroService;
import br.fai.dogs.service.ClienteService;
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
	private ClienteService clienteService;
	
	@GetMapping("/cliente/meus-passeios")
	public String getListaDePasseiosPorCliente(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		List<Passeio> passeios = new ArrayList<Passeio>();
		
		passeios = passeioService.passeiosPorCliente(cliente_id);
		
		model.addAttribute("passeios", passeios);
		
		return "/cliente/passeio/meus-passeios";
	}
	
	@GetMapping("/cliente/solicitar-passeio")
	public String getPageSolicitarPasseio(Model model) {
				
		List<Pessoa> profissionais = pessoaService.readAllProfissional();
		model.addAttribute("profissionais", profissionais);
				
		List<FormaDePagamento> formasDePagamento = formaDePagamentoService.readAll();
		model.addAttribute("formasDePagamento", formasDePagamento);
		
		return "/cliente/passeio/solicitar-passeio";
		
	}
	
	@PostMapping("/cliente/post-solicitar-passeio")
	public String postSolicitarPasseio(Passeio passeio, BindingResult bindingResult) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();

		passeio.setClienteId(cliente_id);
		passeio.setStatus("Espera");
				
		Long idPasseio = passeioService.create(passeio);
				
		if(idPasseio != null && idPasseio != 0) {
			return "redirect:/passeio/cliente/adicionar-cachorro-ao-passeio/" + idPasseio;
		}
		
		return "redirect:/passeio/cliente/solicitar-passeio";
		
	}
	
	@GetMapping("/cliente/adicionar-cachorro-ao-passeio/{id}")
	public String getPageAdicionarCachorroAoPasseio(@PathVariable("id") Long id, Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		
		List<Cachorro> cachorros = cachorroService.cachorrosPorCliente(cliente_id);
		
		model.addAttribute("passeioId", id);
		model.addAttribute("cachorros", cachorros);
		
		return "/cliente/passeio/adicionar-cachorro-ao-passeio";
		
	}
	
	@PostMapping("/cliente/post-adicionar-cachorro-ao-passeio")
	public String postAdicionarCachorroAoPasseio(PasseioCachorro passeioCachorro) {
		
		boolean response = passeioCachorroService.create(passeioCachorro);
		
		if(response == true) {
			return "redirect:/passeio/cliente/meus-passeios";
		}
		
		return "redirect:/passeio/cliente/adicionar-cachorro-ao-passeio/" + passeioCachorro.getPasseioId();
		
	}
	
	@GetMapping("/cliente/detalhes/{id}")
	public String getPageDetalhesDoPasseioCliente(@PathVariable("id") Long id, Model model) {
		
		Passeio passeio = passeioService.readById(id);
		List<Cachorro> cachorros = passeioCachorroService.readByPasseioId(passeio.getId());

		model.addAttribute("passeio", passeio);
		model.addAttribute("cachorros", cachorros);
		
		return "/cliente/passeio/detalhes";
		
	}
	
	@GetMapping("/profissional/meus-passeios")
	public String getListaDePasseiosPorProfissional(Model model) {
		
		Long profissional = pessoaService.sessaoAtual("p").getId();
		List<Passeio> passeios = new ArrayList<Passeio>();
		
		passeios = passeioService.passeiosPorProfissional(profissional);
		
		model.addAttribute("passeios", passeios);
		
		return "/profissional/passeio/meus-passeios";
	}
	
	@GetMapping("/profissional/minha-agenda")
	public String getPageMinhaAgenda(Model model) {
		
		Long profissional = pessoaService.sessaoAtual("p").getId();
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
		
		return "/profissional/passeio/minha-agenda";
		
	}
	
	@GetMapping("/profissional/detalhes/{id}")
	public String getPageDetalhesDoPasseioProfissional(@PathVariable("id") Long id, Model model) {
		
		Passeio passeio = passeioService.readById(id);
		Pessoa cliente  = clienteService.readById(passeio.getClienteId());
		List<Cachorro> cachorros = passeioCachorroService.readByPasseioId(passeio.getId());
		
		model.addAttribute("passeio", passeio);
		model.addAttribute("cliente", cliente);
		model.addAttribute("cachorros", cachorros);
		
		return "/profissional/passeio/detalhes";
		
	}
	
	@GetMapping("/profissional/aprovar-passeio/{id}")
	public String aprovarPasseio(@PathVariable("id") Long id) {
		
		Passeio passeio = new Passeio();
		
		passeio.setId(id);
		passeio.setStatus("Aprovado");
		
		boolean response = passeioService.alterarStatus(passeio);
		
		return "redirect:/passeio/profissional/detalhes/" + id;
				
	}
	
	@GetMapping("/profissional/recusar-passeio/{id}")
	public String recusarPasseio(@PathVariable("id") Long id) {
		
		Passeio passeio = new Passeio();
		
		passeio.setId(id);
		passeio.setStatus("Recusado");
		
		boolean response = passeioService.alterarStatus(passeio);
		
		return "redirect:/passeio/profissional/detalhes/" + id;
				
	}
	
	@GetMapping("/profissional/finalizar-passeio/{id}")
	public String finaliarPasseio(@PathVariable("id") Long id) {
		
		Passeio passeio = new Passeio();
		
		passeio.setId(id);
		passeio.setStatus("Finalizado");
		
		boolean response = passeioService.alterarStatus(passeio);
		
		return "redirect:/passeio/profissional/detalhes/" + id;
				
	}
	
}
