package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.service.CachorroService;

@Service
public class CachorroServiceImpl implements CachorroService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	ParameterizedTypeReference<Map<String, Object>> responseMapStringObjectType = 
            new ParameterizedTypeReference<Map<String, Object>>() {};
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> cachorrosPorCliente(Long cliente_id) {
		
		Map<String, Object> responseRequest = new HashMap<>();
		
		List<Cachorro> listaCachorros = null;
		
		String endpoint = "http://localhost:8082/api/v1/cachorro/cachorrosPorCliente/" + cliente_id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Map<String,Object>> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				responseMapStringObjectType
			);
			
			if(requestResponse.getStatusCodeValue() == 200 && requestResponse.getBody().get("hasError").equals(false)) {
				
				listaCachorros =  (List<Cachorro>) requestResponse.getBody().get("response");
				
				responseRequest.put("hasError", false);
				responseRequest.put("response", listaCachorros);
				
			} else {
								
				responseRequest.put("hasError", requestResponse.getBody().get("hasError"));
				responseRequest.put("message",  requestResponse.getBody().get("message"));
				
			}
			
		} catch (Exception e) {
			responseRequest.put("hasError", true);
			responseRequest.put("message", "Ocorreu um problema ao realizar a requisição para obter todos os cachorros do cliente: " + e.getMessage());
		}
		
		return responseRequest;
		
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
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
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
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
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
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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

	@Override
	public List<Cachorro> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
