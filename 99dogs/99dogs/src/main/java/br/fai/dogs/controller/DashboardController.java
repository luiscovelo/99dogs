package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@GetMapping("/cliente")
	public String getDashboardCliente() {
		
		return "/cliente/dashboard";
		
	}
	
}
