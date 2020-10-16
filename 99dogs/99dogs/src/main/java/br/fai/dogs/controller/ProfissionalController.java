package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.Base64;
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
		
		try {
			
			List<Pessoa> newList = new ArrayList<>();
			List<Pessoa> profissionais = profissionalService.readAll();
			
			for(Pessoa p: profissionais) {
				
				if(p.getFoto() != null) {
					p.setBase64Foto(Base64.getEncoder().encodeToString(p.getFoto()));
				}
				
				newList.add(p);
				
			}
			
			model.addAttribute("profissionais", newList);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/profissional/encontrar-profissionais";
		
	}
	
}
