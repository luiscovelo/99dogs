package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.Collections;
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

import br.fai.dogs.model.entities.Qualificacao;
import br.fai.dogs.service.QualificacaoService;

@Service
public class QualificacaoServiceImpl implements QualificacaoService {

	@Override
	public List<Qualificacao> readByProfissionalId(Long id) {
		
		List<Qualificacao> response = null;
		String endpoint = "http://localhost:8082/api/v1/qualificacao/read-by-profissional-id/" + id;
	
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Qualificacao[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Qualificacao[].class
			);
			
			Qualificacao[] qualificacoes = requestResponse.getBody();
			
			response = Arrays.asList(qualificacoes);
			return response;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}

	@Override
	public Qualificacao readById(Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/qualificacao/read-by-id/" + id;
	
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Qualificacao> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Qualificacao.class
			);
			
			Qualificacao qualificacao = requestResponse.getBody();
			
			return qualificacao;
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}

	@Override
	public boolean update(Qualificacao entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/qualificacao/update";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("id", entity.getId());
			map.put("titulo", entity.getTitulo());
			map.put("modalidade", entity.getModalidade());
			map.put("descricao", entity.getDescricao());
			map.put("profissionalId", entity.getProfissionalId());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Void> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.PUT, 
				requestEntity,
				Void.class
			);			
			
			if(requestResponse.getStatusCode().equals(200)) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("Caiu aqui: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean create(Qualificacao entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/qualificacao/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("titulo", entity.getTitulo());
			map.put("modalidade", entity.getModalidade());
			map.put("descricao", entity.getDescricao());
			map.put("profissionalId", entity.getProfissionalId());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Void> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Void.class
			);			
			
			if(requestResponse.getStatusCode().equals(200)) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("Caiu aqui: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/qualificacao/delete/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Void> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.DELETE, 
				requestEntity,
				Void.class
			);
			
			if(requestResponse.getStatusCodeValue() == 200) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
		
	}
	
}
