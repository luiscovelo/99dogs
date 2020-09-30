package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.CachorroService;
import br.fai.dogs.service.FormaDePagamentoService;
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
	
	@GetMapping("/cliente/meus-passeios")
	public String getListaDePasseiosPorCliente(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		List<Passeio> passeios = new ArrayList<Passeio>();
		
		passeios = passeioService.passeiosPorCliente(cliente_id);
		
		model.addAttribute("passeios", passeios);
		
		return "/cliente/passeio/meus-passeios";
	}
	
	@GetMapping("/cliente/solicitar-passeio")
	public String getPageSolicitarPasseio(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		
		List<Pessoa> profissionais = pessoaService.readAllProfissional();
		model.addAttribute("profissionais", profissionais);
		
		List<Cachorro> cachorros = cachorroService.cachorrosPorCliente(cliente_id);
		model.addAttribute("cachorros", cachorros);
		
		List<FormaDePagamento> formasDePagamento = formaDePagamentoService.readAll();
		model.addAttribute("formasDePagamento", formasDePagamento);
		
		return "/cliente/passeio/solicitar-passeio";
		
	}
	
	@PostMapping("/cliente/post-solicitar-passeio")
	public String postSolicitarPasseio(Passeio passeio, BindingResult bindingResult) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();

		passeio.setClienteId(cliente_id);
		passeio.setStatus("Espera");
				
		boolean response = passeioService.create(passeio);
		
		return "redirect:/passeio/cliente/meus-passeios";
		
	}
	
	@GetMapping("/cliente/detalhes/{id}")
	public String getPageDetalhesDoPasseio(@PathVariable("id") Long id) {
		
		return "/cliente/passeio/detalhes";
		
	}
	
}
