package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.Raca;
import br.fai.dogs.service.CachorroService;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.RacaService;

@Controller
@RequestMapping("/cachorro")
public class CachorroController {
	
	@Autowired
	private CachorroService cachorroService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private RacaService racaService;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/cliente/meus-caes")
	public String getListaDeCaesPorCliente(Model model, RedirectAttributes redirect) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		
		Map<String, Object> responseRequest = new HashMap<>();
		List<Cachorro> cachorros            = new ArrayList<Cachorro>();
		
		try {
			
			responseRequest = cachorroService.cachorrosPorCliente(cliente_id);

			if(responseRequest.get("hasError").equals(false)) {
				cachorros = (List<Cachorro>) responseRequest.get("response");
			}else {
				throw new Exception(responseRequest.get("message").toString());
			}
			
			model.addAttribute("cachorros", cachorros);
			
		} catch (Exception e) {

			model.addAttribute("responseError", e.getMessage());
			
		}
		
		return "/cliente/cachorro/meus-caes";
		
	}
	
	@GetMapping("/cliente/adicionar-cachorro")
	public String getPageAdicionarCachorro(Model model) {
		
		List<Raca> racas = racaService.readAll();

		model.addAttribute("racas", racas);
		
		return "/cliente/cachorro/adicionar-cachorro";
		
	}
	
	@PostMapping("/cliente/post-adicionar-cachorro")
	public String postAdicionarCachorro(Cachorro cachorro) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		
		cachorro.setClienteId(cliente_id);
		
		boolean response = cachorroService.create(cachorro);
		
		return "redirect:/cachorro/cliente/meus-caes";
		
	}
	
	@GetMapping("/cliente/alterar-cachorro/{id}")
	public String getPageAlterarCachorro(@PathVariable("id") Long id, Model model) {
		
		List<Raca> racas = racaService.readAll();
		model.addAttribute("racas", racas);
		
		Cachorro cachorro = cachorroService.readById(id);

		model.addAttribute("cachorro", cachorro);
		
		return "/cliente/cachorro/alterar-cachorro";
		
	}
	
	@PostMapping("/cliente/put-alterar-cachorro")
	public String putAlterarchorro(Cachorro cachorro) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		
		cachorro.setClienteId(cliente_id);
		System.out.println(cachorro);
		boolean response = cachorroService.update(cachorro);
		
		return "redirect:/cachorro/cliente/meus-caes";
		
	}
	
	@GetMapping("/cliente/deletar-cachorro/{id}")
	public String deleteCachorro(@PathVariable("id") Long id) {
		
		boolean response = cachorroService.deleteById(id);
		
		return "redirect:/cachorro/cliente/meus-caes";
		
	}
	
}
