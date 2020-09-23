package br.fai.dogs.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dogwalker")
public class DogwalkerController {
	
	@GetMapping("/dashboard")
	public String getIndexPage(){
		return "dogwalker/dashboard";
	}

}