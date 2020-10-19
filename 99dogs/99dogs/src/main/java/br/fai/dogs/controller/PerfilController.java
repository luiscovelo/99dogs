package br.fai.dogs.controller;

import java.util.Base64;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.ConfiguracaoPicpayService;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("/perfil")
public class PerfilController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ConfiguracaoPicpayService configuracaoPicpayService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/cliente/meu-perfil")
	public String getPagePerfilDoCliente(Model model) {
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();
			Pessoa cliente = pessoaService.readById(cliente_id);

			model.addAttribute("cliente", cliente);
			
			if(cliente.getFoto() != null) {
				model.addAttribute("foto", Base64.getEncoder().encodeToString(cliente.getFoto()));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/perfil/meu-perfil";
		
	}
	
	@GetMapping("/profissional/meu-perfil")
	public String getPagePerfilDoProfissional(Model model) {
		
		try {
			
			Long profissional_id = Helper.getSessao(session).getId();
			
			Pessoa profissional = pessoaService.readById(profissional_id);
			ConfiguracaoPicpay configPicpay = configuracaoPicpayService.readByProfissionalId(profissional_id);
			
			model.addAttribute("profissional", profissional);
			model.addAttribute("configPicpay", configPicpay);
			
			if(profissional.getFoto() != null) {
				model.addAttribute("foto", Base64.getEncoder().encodeToString(profissional.getFoto()));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/perfil/meu-perfil";
		
	}
	
	@PostMapping("/post-atualizar-dados")
	public String postAtualizarDados(Pessoa pessoa, RedirectAttributes redirect) {
		
		try {
			
			Long id = Helper.getSessao(session).getId();
			
			pessoa.setPais("Brasil");
			pessoa.setId(id);
					
			boolean response = pessoaService.update(pessoa);
			
			if(response) {
				redirect.addFlashAttribute("message", "Informações alteradas com sucesso.");
			}else {
				redirect.addFlashAttribute("message", "Ocorreu um problema ao atualizar as informações.");
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		if(pessoa.getTipo().equals("CLIENTE")) {
			return "redirect:/perfil/cliente/meu-perfil";
		}else {
			return "redirect:/perfil/profissional/meu-perfil";
		}
		
	}
	
	@PostMapping("/put-alterar-imagem")
	public String putAlterarImagem(@RequestParam("image") MultipartFile file, RedirectAttributes redirect) {
		
		Pessoa usuario = (Pessoa) Helper.getSessao(session);
		
		String prefix = null;

		if(usuario.getTipo().equals("CLIENTE")) {
			prefix = "cliente";
		}else {
			prefix = "profissional";
		}
		
		try {
			
			if(file.isEmpty()) {
				redirect.addFlashAttribute("message", "Escolha um iamgem para ser alterada.");
				return "redirect:/perfil/"+prefix+"/meu-perfil";
			}
			
			boolean response = pessoaService.uploadImage(usuario.getId(), file);
			
			if(response) {
				redirect.addFlashAttribute("message", "Imagem alterada com sucesso.");
				
			}else {
				redirect.addFlashAttribute("message", "Não foi possível alterar a imagem.");
			}
			
			return "redirect:/perfil/"+prefix+"/meu-perfil";
			
		} catch (Exception e) {
			redirect.addFlashAttribute("message", e.getMessage());
			return "redirect:/perfil/"+prefix+"/meu-perfil";
		}
		
	}
	
}
