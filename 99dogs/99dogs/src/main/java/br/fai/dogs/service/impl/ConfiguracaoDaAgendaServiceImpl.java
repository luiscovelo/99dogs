package br.fai.dogs.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;
import br.fai.dogs.service.ConfiguracaoDaAgendaService;

@Service
public class ConfiguracaoDaAgendaServiceImpl implements ConfiguracaoDaAgendaService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpSession session;
	
	@Override
	public boolean create(ConfiguracaoDaAgenda entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("diaSemana", entity.getDiaSemana());
			map.put("horaInicio", entity.getHoraInicio());
			map.put("horaFinal", entity.getHoraFinal());
			map.put("tempoDePasseio", entity.getTempoDePasseio());
			map.put("profissionalId", entity.getProfissionalId());
			map.put("valorPasseio", entity.getValorPasseio());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map,headers);
			 
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Boolean.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de criação da configuração da agenda: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<ConfiguracaoDaAgenda> readByProfissionalId(Long id) {
		
		List<ConfiguracaoDaAgenda> response = new ArrayList<ConfiguracaoDaAgenda>();
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/read-by-profissional-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<ConfiguracaoDaAgenda[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				ConfiguracaoDaAgenda[].class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				response = Arrays.asList(requestResponse.getBody());
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de obter a configuração da agenda por profissional_id: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public ConfiguracaoDaAgenda readById(Long id) {
		
		ConfiguracaoDaAgenda response = new ConfiguracaoDaAgenda();
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<ConfiguracaoDaAgenda> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				ConfiguracaoDaAgenda.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				response = requestResponse.getBody();
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de obter a configuração da agenda por id: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean update(ConfiguracaoDaAgenda entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/update";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("id", entity.getId());
			map.put("diaSemana", entity.getDiaSemana());
			map.put("horaInicio", entity.getHoraInicio());
			map.put("horaFinal", entity.getHoraFinal());
			map.put("tempoDePasseio", entity.getTempoDePasseio());
			map.put("valorPasseio", entity.getValorPasseio());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map,headers);
			 
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.PUT, 
				requestEntity,
				Boolean.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de update da configuração da agenda: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean delete(Long id) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/delete/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.DELETE, 
				requestEntity,
				Boolean.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de delete da configuração da agenda: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public Map<String, String> horariosDisponiveisPorData(String data, Long id) {
		
		Map<String,String> response = null;
		
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/horarios-disponveis-por-data/" + data + "/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ParameterizedTypeReference<Map<String, String>> responseType = 
		               new ParameterizedTypeReference<Map<String, String>>() {};
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<Map<String,String>> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				responseType
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = requestResponse.getBody();
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de obter horarios dispoveis por data: " + e.getMessage());
		}
		
		return response;
		
	}
	
}
