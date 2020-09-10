package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String getHomePage() {
		return "home";
	}
	
	@GetMapping("/creat-conta-cliente")
	public String getCreatContaClientePage() {
		return "creat-conta-cliente";
	}
	
	@GetMapping("/creat-conta-dog-walker")
	public String getCreatContaDogWalkerPage() {
		return "creat-conta-dog-walker";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {
		return "login";
	}
}
