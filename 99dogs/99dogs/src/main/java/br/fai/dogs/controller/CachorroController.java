package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.service.CachorroService;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("/cachorro")
public class CachorroController {
	
	@Autowired
	private CachorroService cachorroService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/cliente/meus-caes")
	public String getListaDeCaesPorCliente(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		List<Cachorro> cachorros = new ArrayList<Cachorro>();
		
		cachorros = cachorroService.cachorrosPorCliente(cliente_id);
		
		model.addAttribute("cachorros", cachorros);
		
		return "/cliente/cachorro/meus-caes";
		
	}
	
}
