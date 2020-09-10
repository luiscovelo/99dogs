package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/quero-encontrar-dogwalkers")
	public String getCreateContaClientePage() {
		return "create-conta-cliente";
	}
	
	@GetMapping("/quero-ser-um-dogwalker")
	public String getCreateContaDogWalkerPage() {
		return "create-conta-dog-walker";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
}
