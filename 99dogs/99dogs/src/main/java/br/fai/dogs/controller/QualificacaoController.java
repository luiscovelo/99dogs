package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/qualificacao")
public class QualificacaoController {
	
	@GetMapping("/profissional/minhas-qualificacoes")
	public String getPageMinhasQualificacoes() {
		
		return "/profissional/qualificacao/minhas-qualificacoes";
		
	}
	
}
