package br.fai.dogs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.service.AvaliacaoService;
import br.fai.dogs.service.PasseioService;
import br.fai.dogs.service.PessoaService;
import br.fai.dogs.service.ProfissionalService;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private ProfissionalService profissionalService;
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/cliente")
	public String getDashboardCliente(Model model) {
		
		try {
						
			Long cliente_id = Helper.getSessao(session).getId();

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
		
			Long profissional_id = Helper.getSessao(session).getId();
					
			Map<String,String> passeiosAgrupadoPorMes = profissionalService.passeiosAgrupadoPorMes(profissional_id);
			Map<String,String> ticketMedioAgrupadoPorMes = profissionalService.ticketMedioAgrupadoPorMes(profissional_id);
			Map<String,String> recebimentoAgrupadoPorMes = profissionalService.recebimentoAgrupadoPorMes(profissional_id);
			String rating = avaliacaoService.rating(profissional_id);

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
				grafTicketMedioJsonCores.put("rgba(49, 54, 56, 0.60");
				
			}
			
			JSONArray grafRecebimentoJsonNomesMeses = new JSONArray();
			JSONArray grafRecebimentoJsonValoresMeses = new JSONArray();
			JSONArray grafRecebimentoJsonCores = new JSONArray();
			
			for (Map.Entry<String,String> entry : recebimentoAgrupadoPorMes.entrySet()) {
				
				grafRecebimentoJsonNomesMeses.put(entry.getKey());
				grafRecebimentoJsonValoresMeses.put(entry.getValue());
				grafRecebimentoJsonCores.put("rgba(49, 54, 56, 0.5)");
				
			}
			
			model.addAttribute("grafPasseioPorMesJsonNomesMeses", grafPasseioPorMesJsonNomesMeses);
			model.addAttribute("grafPasseioPorMesJsonValoresMeses", grafPasseioPorMesJsonValoresMeses);
			model.addAttribute("grafPasseioPorMesJsonCores", grafPasseioPorMesJsonCores);
			model.addAttribute("grafTicketMedioJsonNomesMeses", grafTicketMedioJsonNomesMeses);
			model.addAttribute("grafTicketMedioJsonValoresMeses", grafTicketMedioJsonValoresMeses);
			model.addAttribute("grafTicketMedioJsonCores", grafTicketMedioJsonCores);
			model.addAttribute("grafRecebimentoJsonNomesMeses", grafRecebimentoJsonNomesMeses);
			model.addAttribute("grafRecebimentoJsonValoresMeses", grafRecebimentoJsonValoresMeses);
			model.addAttribute("grafRecebimentoJsonCores", grafRecebimentoJsonCores);
			model.addAttribute("rating", Integer.valueOf(rating));
						
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/profissional/dashboard";
		
	}
	
}
