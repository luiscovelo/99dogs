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

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.service.CachorroService;

@Service
public class CachorroServiceImpl implements CachorroService {

	@Override
	public List<Cachorro> cachorrosPorCliente(Long cliente_id) {
		
		List<Cachorro> response = null;
		String endpoint = "http://localhost:8082/api/v1/cachorro/cachorrosPorCliente/" + cliente_id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Cachorro[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Cachorro[].class
			);
			
			Cachorro[] cachorros = requestResponse.getBody();
			
			response = Arrays.asList(cachorros);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean create(Cachorro entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/cachorro/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("nome", entity.getNome());
			map.put("dataNascimento", entity.getDataNascimento());
			map.put("racaId", entity.getRacaId());
			map.put("clienteId", entity.getClienteId());
			
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
	public Cachorro readById(Long id) {
		
		Cachorro response = null;
		String endpoint = "http://localhost:8082/api/v1/cachorro/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Cachorro> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Cachorro.class
			);
			
			response = requestResponse.getBody();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean update(Cachorro entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/cachorro/update";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("id", entity.getId());
			map.put("nome", entity.getNome());
			map.put("dataNascimento", entity.getDataNascimento());
			map.put("racaId", entity.getRacaId());
			map.put("clienteId", entity.getClienteId());
			
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
	public boolean deleteById(Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/cachorro/delete/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Cachorro> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.DELETE, 
				requestEntity,
				Cachorro.class
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
