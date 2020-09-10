package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Pessoa;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@PostMapping("/autenticacao")
	public String autenticacao(Pessoa pessoa) {

		if(pessoa.getEmail().equals("cliente")) {
			
			return "redirect:/cliente/dashboard";
			
		}else if(pessoa.getEmail().equals("dogwalker")){
			
			return "redirect:/dogwalker/dashboard";
			
		}else {
			
			return "redirect:/";
			
		}
		
	}
	
}
