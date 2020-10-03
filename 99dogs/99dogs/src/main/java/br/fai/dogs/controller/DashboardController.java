package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
		
		Long cliente_id = pessoaService.sessaoAtual("c").getId();
		List<Passeio> passeios = new ArrayList<Passeio>();
				
		passeios = passeioService.passeiosPorCliente(cliente_id);
		
		Map<String, String> map = new HashMap<>();
		Stack<JSONObject> jsonPasseios = new Stack<JSONObject>();
		
		for(Passeio passeio: passeios) {
			
			map.put("title", passeio.getId().toString());
			map.put("start", passeio.getDatahora().toString());
			map.put("url", "/passeio/cliente/detalhes/" + passeio.getId());
			
			JSONObject json = new JSONObject(map);
			jsonPasseios.push(json);
			
		}
		
		String diaPrimeiroDoMes = Helper.getDataAtual("yyyy-MM-dd");
		
		model.addAttribute("jsonPasseios", jsonPasseios);
		model.addAttribute("diaPrimeiroDoMes", diaPrimeiroDoMes);
		
		return "/cliente/dashboard";
		
	}
	
	@GetMapping("/profissional")
	public String getDashboardProfissional(Model model) {
		
		Long profissional_id = pessoaService.sessaoAtual("p").getId();
				
		Map<String,String> passeiosAgrupadoPorMes = profissionalService.passeiosAgrupadoPorMes(profissional_id);
		
		JSONArray jsonNomesMeses = new JSONArray();
		JSONArray jsonValoresMeses = new JSONArray();
		JSONArray jsonCores = new JSONArray();
		
		for (Map.Entry<String,String> entry : passeiosAgrupadoPorMes.entrySet()) {
			
			jsonNomesMeses.put(entry.getKey());
			jsonValoresMeses.put(entry.getValue());
			jsonCores.put("rgba(49, 54, 56, 0.75)");
		}
		
		model.addAttribute("jsonNomesMeses", jsonNomesMeses);
		model.addAttribute("jsonValoresMeses", jsonValoresMeses);
		model.addAttribute("jsonCores", jsonCores);
		
		return "/profissional/dashboard";
		
	}
	
}
