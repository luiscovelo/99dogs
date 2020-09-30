package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Qualificacao;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.QualificacaoService;

@Controller
@RequestMapping("/qualificacao")
public class QualificacaoController {
	
	@Autowired
	private QualificacaoService qualificacaoService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/profissional/minhas-qualificacoes")
	public String getPageMinhasQualificacoes(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
		
		List<Qualificacao> qualificacoes = qualificacaoService.readByProfissionalId(profissional_id);
		model.addAttribute("qualificacoes", qualificacoes);
		
		return "/profissional/qualificacao/minhas-qualificacoes";
		
	}
	
	@GetMapping("/profissional/alterar-qualificacao/{id}")
	public String getPageAlterarQualificacao(@PathVariable("id") Long id, Model model) {
		
		Qualificacao qualificacao = qualificacaoService.readById(id);
		model.addAttribute("qualificacao", qualificacao);
		
		List<String> modalidades = new ArrayList<String>();
		
		modalidades.add("Graduacao");
		modalidades.add("Tecnico");
		modalidades.add("Pos-Graduacao");
		modalidades.add("Mestrado");
		
		model.addAttribute("modalidades", modalidades);
		
		return "/profissional/qualificacao/alterar-qualificacao";
		
	}
	
	@PostMapping("/profissional/put-alterar-qualificacao")
	public String putAlterarQualificao(Qualificacao qualificacao) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
		qualificacao.setProfissionalId(profissional_id);
				
		boolean response = qualificacaoService.update(qualificacao);
		
		return "redirect:/qualificacao/profissional/minhas-qualificacoes";
		
	}
	
	@GetMapping("/profissional/adicionar-qualificacao")
	public String getPageAdicionarQualificacao(Model model) {
		
		List<String> modalidades = new ArrayList<String>();
		
		modalidades.add("Graduacao");
		modalidades.add("Tecnico");
		modalidades.add("Pos-Graduacao");
		modalidades.add("Mestrado");
		
		model.addAttribute("modalidades", modalidades);
		
		return "/profissional/qualificacao/adicionar-qualificacao";
		
	}
	
	@PostMapping("/profissional/post-alterar-qualificacao")
	public String postAlterarQualificao(Qualificacao qualificacao) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
		qualificacao.setProfissionalId(profissional_id);
		
		boolean response = qualificacaoService.create(qualificacao);
		
		return "redirect:/qualificacao/profissional/minhas-qualificacoes";
		
	}
	
	@GetMapping("/profissional/deletar-qualificacao/{id}")
	public String deleteQualificacao(@PathVariable("id") Long id) {
		
		boolean response = qualificacaoService.deleteById(id);
		
		return "redirect:/qualificacao/profissional/minhas-qualificacoes"; 
		
	}
	
}
