package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.ReclamacaoSugestao;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.ReclamacaoSugestaoService;

@Controller
@RequestMapping("/reclamacaoSugestao")
public class ReclamacaoSugestaoController {
		
	@Autowired
	private ReclamacaoSugestaoService reclamacaoSugestaoService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/cliente/meus-feedbacks")
	public String getListaDeReclamacaoSugestaoPorCliente(Model model) {
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();

			List<ReclamacaoSugestao> listReclamacaoSugestao = reclamacaoSugestaoService.reclamacaoSugestaoPorCliente(cliente_id);
			model.addAttribute("listReclamacaoSugestao", listReclamacaoSugestao);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/reclamacaoSugestao/meus-feedbacks";
		
	}
	
	@GetMapping("/cliente/registrar-feedback")
	public String getPageRegistrarFeedback(Model model) {
		
		try {
			
			Pessoa cliente = new Pessoa();
			
			cliente.setNome(Helper.getSessao(session).getNome());
			cliente.setEmail(Helper.getSessao(session).getEmail());
			
			model.addAttribute("cliente", cliente);
			
			List<String> assuntos = new ArrayList<String>();
			
			assuntos.add("Profissional");
			assuntos.add("Cliente");
			assuntos.add("Aplicacao");
			assuntos.add("Falhas");
			
			model.addAttribute("assuntos", assuntos);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/reclamacaoSugestao/registrar-feedback";
		
	}
	
	@PostMapping("/cliente/post-registrar-feedback")
	public String postRegistrarFeedback(ReclamacaoSugestao reclamacaoSugestao) {
		
		try {
			
			Long cliente_id = Helper.getSessao(session).getId();
			
			reclamacaoSugestao.setPessoaId(cliente_id);

			boolean response = reclamacaoSugestaoService.create(reclamacaoSugestao);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/reclamacaoSugestao/cliente/meus-feedbacks";
		
	}
	
}
