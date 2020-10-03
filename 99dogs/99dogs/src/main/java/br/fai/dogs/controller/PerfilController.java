package br.fai.dogs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/cliente/meu-perfil")
	public String getPagePerfilDoCliente(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		Pessoa cliente = pessoaService.readById(cliente_id);
		
		model.addAttribute("cliente", cliente);
		
		return "/cliente/perfil/meu-perfil";
		
	}
	
	@GetMapping("/profissional/meu-perfil")
	public String getPagePerfilDoProfissional(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
		Pessoa profissional = pessoaService.readById(profissional_id);
		
		model.addAttribute("profissional", profissional);
		
		return "/profissional/perfil/meu-perfil";
		
	}
	
}
