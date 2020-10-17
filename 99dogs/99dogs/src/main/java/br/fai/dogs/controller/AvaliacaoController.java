package br.fai.dogs.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Avaliacao;
import br.fai.dogs.service.AvaliacaoService;

@Controller
@RequestMapping("/avaliacao")
public class AvaliacaoController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping("/cliente/registrar-avaliacao/{id}")
	public String getPageRegistrarAvaliacao(@PathVariable("id") Long id, Model model) {
		
		try {
			
			model.addAttribute("profissionalId", id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/avaliacao/registrar-avaliacao";
		
	}
	
	@PostMapping("/cliente/post-registrar-avaliacao")
	public String postRegistrarAvaliacao(Avaliacao avaliacao, RedirectAttributes redirect) {
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();
			
			avaliacao.setClienteId(cliente_id);
			
			boolean response = avaliacaoService.create(avaliacao);
			
			if(response) {
				redirect.addFlashAttribute("message", "Avaliação realizada com sucesso.");
				return "redirect:/profissional/cliente/detalhes/" + avaliacao.getProfissionalId();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		redirect.addFlashAttribute("message", "Ocorreu um problema para avaliar o profissional.");
		return "redirect:/avaliacao/cliente/registrar-avaliacao/" + avaliacao.getProfissionalId();
		
	}
	
}
