package br.fai.dogs.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;
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
	
	@PostMapping("/post-atualizar-dados")
	public String postAtualizarDados(Pessoa pessoa, RedirectAttributes redirect) {
		
		Long id = null;
		
		if(pessoa.getTipo().equals("CLIENTE")) {
			id = pessoaService.sessaoAtual("c").getId();
		}else {
			id = pessoaService.sessaoAtual("p").getId();
		}
		
		pessoa.setPais("Brasil");
		pessoa.setFoto("#");
		pessoa.setId(id);
				
		
		boolean response = pessoaService.update(pessoa);
		
		if(response) {
			redirect.addFlashAttribute("message", "Informações alteradas com sucesso.");
		}else {
			redirect.addFlashAttribute("message", "Ocorreu um problema ao atualizar as informações.");
		}
		
		if(pessoa.getTipo().equals("CLIENTE")) {
			return "redirect:/perfil/cliente/meu-perfil";
		}else {
			return "redirect:/perfil/profissional/meu-perfil";
		}
		
	}
	
}
