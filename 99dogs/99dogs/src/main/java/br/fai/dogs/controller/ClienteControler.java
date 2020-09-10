package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteControler {
	
	@GetMapping("/feedback")
	public String getFeedbackPage(){
		return "cliente/feedback";
	}
	
	@GetMapping("/index")
	public String getIndexPage(){
		return "cliente/index";
	}
	
	@GetMapping("/meus-caes")
	public String getMeusCaesPage(){
		return "cliente/meus-caes";
	}
	
	@GetMapping("/passeios")
	public String getPasseiosPage(){
		return "cliente/passeios";
	}
	
	@GetMapping("/perfil")
	public String getPerfilPage(){
		return "cliente/perfil";
	}
	
	@GetMapping("/servicos")
	public String getServicosPage(){
		return "cliente/servicos";
	}
}
