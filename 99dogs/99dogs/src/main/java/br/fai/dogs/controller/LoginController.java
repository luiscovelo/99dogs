package br.fai.dogs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@PostMapping("/autenticacao")
	public String autenticacao(Pessoa dadosForm, HttpSession session) {
		
		Pessoa pessoa = new Pessoa();   
		
		pessoa = pessoaService.validarLogin(dadosForm);

		if(pessoa != null) {
			
			pessoaService.gravarSessao(session, pessoa);
			
			if(pessoa.getTipo().equals("CLIENTE")) {
				
				return "redirect:/dashboard/cliente";
				
			}else if(pessoa.getTipo().equals("PROFISSIONAL")) {
				
				return "redirect:/dashboard/profissional";
				
			}
			
			return "redirect:/login";
			
		}
		
		return "redirect:/login";
		
		
	}
	
	@GetMapping("/deslogar")
	public String deslogar(HttpSession session) {
		
		session.invalidate();
		
		return "redirect:/login";
		
	}
	
}
