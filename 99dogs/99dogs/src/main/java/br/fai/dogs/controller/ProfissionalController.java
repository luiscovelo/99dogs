package br.fai.dogs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.ProfissionalService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@GetMapping("/cliente/encontrar-profissionais")
	public String getPageEncontrarProfissionais(Model model) {
		
		List<Pessoa> profissionais = profissionalService.readAll();
		model.addAttribute("profissionais", profissionais);
		
		return "/cliente/profissional/encontrar-profissionais";
		
	}
	
}
