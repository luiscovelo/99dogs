package br.fai.dogs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.fai.dogs.model.entities.Profissional;
import br.fai.dogs.service.ProfissionalService;

@Controller
public class LandingPageController {
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private ContaController contaController;
	
	@Autowired
	private HttpSession httpSession;
	
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
	
	@GetMapping("/profissionais")
	public String getPageProfissionais(HttpServletResponse httpResponse, Model model) {
		
		try {
			
			String token = contaController.tokenJwt(httpResponse, "anonimo", 30 * 60);
			
			if(token != null) {
				httpSession.setAttribute("token", token);
			}
			
			List<Profissional> profissionais = profissionalService.readAll();
			model.addAttribute("profissionais", profissionais);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "profissionais";
	}
	
	@GetMapping("/login")
	public String getLoginPage() {		
		return "login";
	}
	
	@GetMapping("/logout")
	public String logout() {
		return "redirect:/";
	}
	
}
