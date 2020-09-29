package br.fai.dogs.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.service.PasseioService;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("/passeio")
public class PasseioController {
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/cliente/meus-passeios")
	public String getListaDePasseiosPorCliente(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		List<Passeio> passeios = new ArrayList<Passeio>();
		
		passeios = passeioService.passeiosPorCliente(cliente_id);
		
		model.addAttribute("passeios", passeios);
		
		System.out.println(passeios);
		
		return "/cliente/passeio/meus-passeios";
	}
	
	@GetMapping("/cliente/solicitar-passeio")
	public String getPageSolicitarPasseio() {
			
		return "/cliente/passeio/solicitar-passeio";
		
	}
	
	@PostMapping("/cliente/post-solicitar-passeio")
	public String postSolicitarPasseio(Passeio passeio) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		Long profissional_id = (long) 2;
		
		passeio.setClienteId(cliente_id);
		passeio.setProfissionalId(profissional_id);
		passeio.setStatus("Espera");
		passeio.setDatahora(LocalDateTime.now());
		
		boolean response = passeioService.create(passeio);
		
		return "redirect:/passeio/cliente/meus-passeios";
		
	}
	
}
