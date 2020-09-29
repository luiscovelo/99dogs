package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	
	@GetMapping("/cliente/meus-feedbacks")
	public String getListaDeFeedbackPorCliente() {
		
		return "/cliente/feedback";
		
	}
	
}
