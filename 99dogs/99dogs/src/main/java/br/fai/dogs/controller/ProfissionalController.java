package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.Avaliacao;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;
import br.fai.dogs.model.entities.Qualificacao;
import br.fai.dogs.service.AvaliacaoService;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.ProfissionalService;
import br.fai.dogs.service.QualificacaoService;

@Controller
@RequestMapping("/profissional")
public class ProfissionalController {
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private QualificacaoService qualificacaoService;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping("/cliente/encontrar-profissionais")
	public String getPageEncontrarProfissionais(Model model) {
		
		try {
			
			List<Profissional> profissionais = profissionalService.readAll();
			model.addAttribute("profissionais", profissionais);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/profissional/encontrar-profissionais";
		
	}
	
	@GetMapping("/cliente/detalhes/{id}")
	public String getPageDetalhesPerfilCliente(@PathVariable("id") Long id, Model model) {
		
		try {
			
			Pessoa pessoa = pessoaService.readById(id);
			List<Qualificacao> qualificacoes = qualificacaoService.readByProfissionalId(id);
			List<Avaliacao> avaliacoes = avaliacaoService.readByProfissionalId(id);
			List<Avaliacao> newAvaliacoes = new ArrayList<>();
			String rating = avaliacaoService.rating(id);
			
			if(pessoa.getFoto() != null) {
				pessoa.setBase64Foto(Base64.getEncoder().encodeToString(pessoa.getFoto()));
			}
			
			for(Avaliacao avaliacao : avaliacoes) {
				
				Pessoa cliente = new Pessoa();
				
				cliente = avaliacao.getCliente().getPessoa();
				
				if(cliente.getFoto() != null) {
					cliente.setBase64Foto(Base64.getEncoder().encodeToString(cliente.getFoto()));
				}
				
				avaliacao.getCliente().setPessoa(cliente);
				newAvaliacoes.add(avaliacao);
				
			}
			
			model.addAttribute("pessoa", pessoa);
			model.addAttribute("qualificacoes", qualificacoes);
			model.addAttribute("avaliacoes", newAvaliacoes);
			model.addAttribute("rating", Integer.valueOf(rating));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/profissional/detalhes";
		
	}
	
}
