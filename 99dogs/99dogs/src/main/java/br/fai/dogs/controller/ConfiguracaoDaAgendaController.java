package br.fai.dogs.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;
import br.fai.dogs.service.ConfiguracaoDaAgendaService;

@Controller
@RequestMapping("configuracao-da-agenda")
public class ConfiguracaoDaAgendaController {
		
	@Autowired
	private ConfiguracaoDaAgendaService configuracaoDaAgendaService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/profissional/minha-configuracao")
	public String getPageMinhaConfiguracao(Model model) {
		
		try {
			
			Long profissional_id = Helper.getSessao(session).getId();
			
			List<ConfiguracaoDaAgenda> configs = configuracaoDaAgendaService.readByProfissionalId(profissional_id);
			Map<Integer,String> diasDaSemana = new HashMap<Integer,String>();
			
			diasDaSemana.put(2, "Segunda-feira");
			diasDaSemana.put(3, "Terça-feira");
			diasDaSemana.put(4, "Quarta-feira");
			diasDaSemana.put(5, "Quinta-feira");
			diasDaSemana.put(6, "Sexta-feira");
			diasDaSemana.put(7, "Sábado");
			diasDaSemana.put(1, "Domingo");
			
			model.addAttribute("configs", configs);
			model.addAttribute("diasDaSemana", diasDaSemana);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return "/profissional/configuracao-da-agenda/minha-configuracao";
		
	}
	
	@GetMapping("/profissional/adicionar-configuracao")
	public String getPageAdicionarConfiguracaoDaAgenda(Model model) {
		
		try {
			
			Long profissional_id = Helper.getSessao(session).getId();
			
			List<ConfiguracaoDaAgenda> configs = configuracaoDaAgendaService.readByProfissionalId(profissional_id);
			Map<Integer,String> diasDaSemana = new HashMap<Integer,String>();
			
			diasDaSemana.put(2, "Segunda-feira");
			diasDaSemana.put(3, "Terça-feira");
			diasDaSemana.put(4, "Quarta-feira");
			diasDaSemana.put(5, "Quinta-feira");
			diasDaSemana.put(6, "Sexta-feira");
			diasDaSemana.put(7, "Sábado");
			diasDaSemana.put(1, "Domingo");
			
			for(ConfiguracaoDaAgenda cda: configs) {
				
				if(diasDaSemana.containsKey(cda.getDiaSemana().intValue())) {
					diasDaSemana.remove(cda.getDiaSemana().intValue());
				}
				
			}
			
			model.addAttribute("diasDaSemana", diasDaSemana);
			model.addAttribute("profissional_id", profissional_id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/configuracao-da-agenda/adicionar-configuracao";
		
	}
	
	@PostMapping("/profissional/post-adicionar-configuracao-da-agenda")
	public String postConfiguracaoDaAgenda(ConfiguracaoDaAgenda configuracaoDaAgenda) {
		
		try {
			
			boolean response = configuracaoDaAgendaService.create(configuracaoDaAgenda);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/configuracao-da-agenda/profissional/minha-configuracao";
		
	}
	
	@GetMapping("/profissional/alterar-configuracao/{id}")
	public String getPageAlterarConfiguracaoDaAgenda(@PathVariable("id") Long id, Model model) {
		
		try {
			
			ConfiguracaoDaAgenda config = configuracaoDaAgendaService.readById(id);
			
			Map<Integer,String> diasDaSemana = new HashMap<Integer,String>();
			
			diasDaSemana.put(2, "Segunda-feira");
			diasDaSemana.put(3, "Terça-feira");
			diasDaSemana.put(4, "Quarta-feira");
			diasDaSemana.put(5, "Quinta-feira");
			diasDaSemana.put(6, "Sexta-feira");
			diasDaSemana.put(7, "Sábado");
			diasDaSemana.put(1, "Domingo");
			
			model.addAttribute("diasDaSemana", diasDaSemana);
			model.addAttribute("config", config);
			model.addAttribute("id", id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/configuracao-da-agenda/alterar-configuracao";
		
	}
	
	@PostMapping("/profissional/put-alterar-configuracao-da-agenda")
	public String putConfiguracaoDaAgenda(ConfiguracaoDaAgenda configuracaoDaAgenda) {
		
		try {
			
			boolean response = configuracaoDaAgendaService.update(configuracaoDaAgenda);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/configuracao-da-agenda/profissional/minha-configuracao";
		
	}
	
	@GetMapping("/profissional/deletar-configuracao/{id}")
	public String deletarConfiguracaoDaAgenda(@PathVariable("id") Long id) {
		
		try {
			
			boolean response = configuracaoDaAgendaService.delete(id);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "redirect:/configuracao-da-agenda/profissional/minha-configuracao";
		
	}
	
	@GetMapping("/cliente/horarios-disponveis-por-data/{data}/{id}")
	public HttpEntity<Map<String,String>> horariosDisponiveisPorData(@PathVariable("data") String data, @PathVariable("id") Long id){
		
		try {
			
			Map<String,String> response = configuracaoDaAgendaService.horariosDisponiveisPorData(data, id);
			return ResponseEntity.ok(response);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return ResponseEntity.ok(null);
		
	}
	
}
