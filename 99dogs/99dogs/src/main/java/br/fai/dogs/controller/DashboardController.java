package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.service.PasseioService;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.ProfissionalService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@GetMapping("/cliente")
	public String getDashboardCliente(Model model) {
		
		try {
						
			Long cliente_id = pessoaService.sessaoAtual("c").getId();
			List<Passeio> passeios = new ArrayList<Passeio>();
					
			passeios = passeioService.passeiosPorCliente(cliente_id);
			
			Map<String, String> map = new HashMap<>();
			Stack<JSONObject> jsonPasseios = new Stack<JSONObject>();
			
			for(Passeio passeio: passeios) {
				
				map.put("title", passeio.getCliente().getPessoa().getNome().toString());
				map.put("start", passeio.getDatahora().toString());
				map.put("url", "/passeio/cliente/detalhes/" + passeio.getId());
				
				JSONObject json = new JSONObject(map);
				jsonPasseios.push(json);
				
			}
			
			String diaPrimeiroDoMes = Helper.getDataAtual("yyyy-MM-dd");
			
			model.addAttribute("jsonPasseios", jsonPasseios);
			model.addAttribute("diaPrimeiroDoMes", diaPrimeiroDoMes);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/dashboard";
		
	}
	
	@GetMapping("/profissional")
	public String getDashboardProfissional(Model model) {
		
		try {
		
			Long profissional_id = pessoaService.sessaoAtual("p").getId();
					
			Map<String,String> passeiosAgrupadoPorMes = profissionalService.passeiosAgrupadoPorMes(profissional_id);
			Map<String,String> ticketMedioAgrupadoPorMes = profissionalService.ticketMedioAgrupadoPorMes(profissional_id);
						
			JSONArray grafPasseioPorMesJsonNomesMeses = new JSONArray();
			JSONArray grafPasseioPorMesJsonValoresMeses = new JSONArray();
			JSONArray grafPasseioPorMesJsonCores = new JSONArray();
			
			for (Map.Entry<String,String> entry : passeiosAgrupadoPorMes.entrySet()) {
				
				grafPasseioPorMesJsonNomesMeses.put(entry.getKey());
				grafPasseioPorMesJsonValoresMeses.put(entry.getValue());
				grafPasseioPorMesJsonCores.put("rgba(49, 54, 56, 0.75)");
				
			}
			
			JSONArray grafTicketMedioJsonNomesMeses = new JSONArray();
			JSONArray grafTicketMedioJsonValoresMeses = new JSONArray();
			JSONArray grafTicketMedioJsonCores = new JSONArray();
			
			for (Map.Entry<String,String> entry : ticketMedioAgrupadoPorMes.entrySet()) {
				
				grafTicketMedioJsonNomesMeses.put(entry.getKey());
				grafTicketMedioJsonValoresMeses.put(entry.getValue());
				grafTicketMedioJsonCores.put("rgb(255, 99, 132)");
				
			}
						
			model.addAttribute("grafPasseioPorMesJsonNomesMeses", grafPasseioPorMesJsonNomesMeses);
			model.addAttribute("grafPasseioPorMesJsonValoresMeses", grafPasseioPorMesJsonValoresMeses);
			model.addAttribute("grafPasseioPorMesJsonCores", grafPasseioPorMesJsonCores);
			model.addAttribute("grafTicketMedioJsonNomesMeses", grafTicketMedioJsonNomesMeses);
			model.addAttribute("grafTicketMedioJsonValoresMeses", grafTicketMedioJsonValoresMeses);
			model.addAttribute("grafTicketMedioJsonCores", grafTicketMedioJsonCores);
						
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/dashboard";
		
	}
	
}
