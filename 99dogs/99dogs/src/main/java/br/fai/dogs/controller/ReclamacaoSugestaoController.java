package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.ReclamacaoSugestao;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.ReclamacaoSugestaoService;

@Controller
@RequestMapping("/reclamacaoSugestao")
public class ReclamacaoSugestaoController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ReclamacaoSugestaoService reclamacaoSugestaoService;
	
	@GetMapping("/cliente/meus-feedbacks")
	public String getListaDeReclamacaoSugestaoPorCliente(Model model) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		
		List<ReclamacaoSugestao> listReclamacaoSugestao = reclamacaoSugestaoService.reclamacaoSugestaoPorCliente(cliente_id);
		model.addAttribute("listReclamacaoSugestao", listReclamacaoSugestao);
		
		return "/cliente/reclamacaoSugestao/meus-feedbacks";
		
	}
	
	@GetMapping("/cliente/registrar-feedback")
	public String getPageRegistrarFeedback(Model model) {
		
		Pessoa cliente = new Pessoa();
		
		cliente.setNome(pessoaService.sessaoAtual().getNome());
		cliente.setEmail(pessoaService.sessaoAtual().getEmail());
		
		model.addAttribute("cliente", cliente);
		
		List<String> assuntos = new ArrayList<String>();
		
		assuntos.add("Profissional");
		assuntos.add("Cliente");
		assuntos.add("Aplicacao");
		assuntos.add("Falhas");
		
		model.addAttribute("assuntos", assuntos);
		
		return "/cliente/reclamacaoSugestao/registrar-feedback";
		
	}
	
	@PostMapping("/cliente/post-registrar-feedback")
	public String postRegistrarFeedback(ReclamacaoSugestao reclamacaoSugestao) {
		
		Long cliente_id = pessoaService.sessaoAtual().getId();
		
		reclamacaoSugestao.setClienteId(cliente_id);

		boolean response = reclamacaoSugestaoService.create(reclamacaoSugestao);
		
		return "redirect:/reclamacaoSugestao/cliente/meus-feedbacks";
		
	}
	
}
