package br.fai.dogs.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;
import br.fai.dogs.service.ConfiguracaoDaAgendaService;

@Service
public class ConfiguracaoDaAgendaServiceImpl implements ConfiguracaoDaAgendaService {

	@Override
	public boolean create(ConfiguracaoDaAgenda entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-da-agenda/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
									
			Map<String, Object> map = new HashMap<>();
			
			map.put("diaSemana", entity.getDiaSemana());
			map.put("horaInicio", entity.getHoraInicio());
			map.put("horaFinal", entity.getHoraFinal());
			map.put("tempoDePasseio", entity.getTempoDePasseio());
			map.put("profissionalId", entity.getProfissionalId());
			map.put("valorPasseio", entity.getValorPasseio());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map);
			 
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
															
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			 
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
															
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			 
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
									
			Map<String, Object> map = new HashMap<>();
			
			map.put("id", entity.getId());
			map.put("diaSemana", entity.getDiaSemana());
			map.put("horaInicio", entity.getHoraInicio());
			map.put("horaFinal", entity.getHoraFinal());
			map.put("tempoDePasseio", entity.getTempoDePasseio());
			map.put("valorPasseio", entity.getValorPasseio());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map);
			 
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
									
			HttpEntity<String> requestEntity = new HttpEntity<>("");
			 
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
	
}
