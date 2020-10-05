package br.fai.dogs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;
import br.fai.dogs.service.ConfiguracaoDaAgendaService;
import br.fai.dogs.service.PessoaService;

@Controller
@RequestMapping("configuracao-da-agenda")
public class ConfiguracaoDaAgendaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private ConfiguracaoDaAgendaService configuracaoDaAgendaService;
	
	@GetMapping("/profissional/minha-configuracao")
	public String getPageMinhaConfiguracao(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
		
		List<ConfiguracaoDaAgenda> configs = configuracaoDaAgendaService.readByProfissionalId(profissional_id);
		Map<Integer,String> diasDaSemana = new HashMap<Integer,String>();
		
		diasDaSemana.put(0, "Segunda-feira");
		diasDaSemana.put(1, "Terça-feira");
		diasDaSemana.put(2, "Quarta-feira");
		diasDaSemana.put(3, "Quinta-feira");
		diasDaSemana.put(4, "Sexta-feira");
		diasDaSemana.put(5, "Sábado");
		diasDaSemana.put(6, "Domingo");
		
		model.addAttribute("configs", configs);
		model.addAttribute("diasDaSemana", diasDaSemana);
		
		return "/profissional/configuracao-da-agenda/minha-configuracao";
		
	}
	
	@GetMapping("/profissional/adicionar-configuracao")
	public String getPageAdicionarConfiguracaoDaAgenda(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
				
		Map<Integer,String> diasDaSemana = new HashMap<Integer,String>();
		
		diasDaSemana.put(0, "Segunda-feira");
		diasDaSemana.put(1, "Terça-feira");
		diasDaSemana.put(2, "Quarta-feira");
		diasDaSemana.put(3, "Quinta-feira");
		diasDaSemana.put(4, "Sexta-feira");
		diasDaSemana.put(5, "Sábado");
		diasDaSemana.put(6, "Domingo");
		
		model.addAttribute("diasDaSemana", diasDaSemana);
		model.addAttribute("profissional_id", profissional_id);
		
		return "/profissional/configuracao-da-agenda/adicionar-configuracao";
		
	}
	
	@PostMapping("/profissional/post-adicionar-configuracao-da-agenda")
	public String postConfiguracaoDaAgenda(ConfiguracaoDaAgenda configuracaoDaAgenda) {
		
		boolean response = configuracaoDaAgendaService.create(configuracaoDaAgenda);
		
		return "redirect:/configuracao-da-agenda/profissional/minha-configuracao";
		
	}
	
	@GetMapping("/profissional/alterar-configuracao/{id}")
	public String getPageAlterarConfiguracaoDaAgenda(@PathVariable("id") Long id, Model model) {
		
		ConfiguracaoDaAgenda config = configuracaoDaAgendaService.readById(id);
		
		Map<Integer,String> diasDaSemana = new HashMap<Integer,String>();
		
		diasDaSemana.put(0, "Segunda-feira");
		diasDaSemana.put(1, "Terça-feira");
		diasDaSemana.put(2, "Quarta-feira");
		diasDaSemana.put(3, "Quinta-feira");
		diasDaSemana.put(4, "Sexta-feira");
		diasDaSemana.put(5, "Sábado");
		diasDaSemana.put(6, "Domingo");
		
		model.addAttribute("diasDaSemana", diasDaSemana);
		model.addAttribute("config", config);
		model.addAttribute("id", id);
		
		return "/profissional/configuracao-da-agenda/alterar-configuracao";
		
	}
	
	@PostMapping("/profissional/put-alterar-configuracao-da-agenda")
	public String putConfiguracaoDaAgenda(ConfiguracaoDaAgenda configuracaoDaAgenda) {
		
		boolean response = configuracaoDaAgendaService.update(configuracaoDaAgenda);
		
		return "redirect:/configuracao-da-agenda/profissional/minha-configuracao";
		
	}
	
	@GetMapping("profissional/deletar-configuracao/{id}")
	public String deletarConfiguracaoDaAgenda(@PathVariable("id") Long id) {
		
		boolean response = configuracaoDaAgendaService.delete(id);
		return "redirect:/configuracao-da-agenda/profissional/minha-configuracao";
		
	}
	
}
