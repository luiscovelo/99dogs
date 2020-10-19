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
import br.fai.dogs.model.entities.ConfiguracaoPicpay;
import br.fai.dogs.service.ConfiguracaoPicpayService;

@Controller
@RequestMapping("/configuracao-picpay")
public class ConfiguracaoPicpayController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private ConfiguracaoPicpayService configuracaoPicpayService;
	
	@GetMapping("/profissional/adicionar")
	public String getPageAdicionar() {
		
		return "/profissional/configuracao-picpay/adicionar";
		
	}
	
	@PostMapping("/profissional/post-adicionar")
	public String postAdicionar(ConfiguracaoPicpay configuracaoPicpay, RedirectAttributes redirect) {
		
		try {
			
			Long profissional_id = Helper.getSessao(session).getId();
			
			configuracaoPicpay.setProfissionalId(profissional_id);

			boolean response = configuracaoPicpayService.create(configuracaoPicpay);
			
			if(response) {
				redirect.addFlashAttribute("message", "Configuração adicionado com sucesso.");
				return "redirect:/perfil/profissional/meu-perfil";
			}
			
			redirect.addFlashAttribute("message", "Ocorre um problema ao criar a configuração do picpay.");
			
		} catch (Exception e) {
			redirect.addFlashAttribute("message", "Ocorreu um problema ao criar a configuração do picpay: " + e.getMessage());
		}
		
		return "redirect:/configuracao-picpay/profissional/adicionar";
		
	}
	
	@GetMapping("/profissional/alterar/{id}")
	public String getPageAlterar(@PathVariable("id") Long id, Model model) {
		
		try {
			
			ConfiguracaoPicpay configPicpay = configuracaoPicpayService.readById(id);
			
			model.addAttribute("configPicpay", configPicpay);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/configuracao-picpay/alterar";
		
	}
	
	@PostMapping("/profissional/put-adicionar")
	public String putAlterar(ConfiguracaoPicpay configuracaoPicpay, RedirectAttributes redirect) {

		try {
			
			boolean response = configuracaoPicpayService.update(configuracaoPicpay);
			
			if(response) {
				redirect.addFlashAttribute("message", "Configuração alterada com sucesso.");
				return "redirect:/perfil/profissional/meu-perfil";
			}
			
			redirect.addFlashAttribute("message", "Ocorre um problema ao alterar a configuração do picpay.");
			
		} catch (Exception e) {
			redirect.addFlashAttribute("message", "Ocorreu um problema ao alterar a configuração do picpay: " + e.getMessage());
		}
		
		return "redirect:/configuracao-picpay/profissional/alterar/" + configuracaoPicpay.getId();
		
	}
	
	@GetMapping("/profissional/delete/{id}")
	public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirect) {
		
		try {
			
			boolean response = configuracaoPicpayService.deleteById(id);
			
			if(response) {
				redirect.addFlashAttribute("message", "Configuração deletada com sucesso.");
				return "redirect:/perfil/profissional/meu-perfil";
			}
			
		} catch (Exception e) {
			redirect.addFlashAttribute("message", "Ocorreu um problema ao deletar a configuração do picpay: " + e.getMessage());
		}
		
		return "redirect:/perfil/profissional/meu-perfil";
		
	}
	
}
