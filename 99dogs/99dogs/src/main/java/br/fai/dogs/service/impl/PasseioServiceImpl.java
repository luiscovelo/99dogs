package br.fai.dogs.service.impl;

import java.util.ArrayList;
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

import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.service.PasseioService;

@Service
public class PasseioServiceImpl implements PasseioService {
	
	@Override
	public List<Passeio> readAll() {
		
		List<Passeio> response = null;
		String endpoint = "http://localhost:8082/api/v1/passeio/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Passeio[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Passeio[].class
			);
			
			Passeio[] passeios = requestResponse.getBody();
			
			response = Arrays.asList(passeios);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;

	}

	@Override
	public boolean create(Passeio entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/passeio/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("cliente_id", entity.getClienteId());
			map.put("datahora", entity.getDatahora());
			map.put("profissional_id", entity.getProfissionalId());
			map.put("status", entity.getStatus());
			map.put("valor", entity.getValor());
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Void> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Void.class
			);			
			
		} catch (Exception e) {
			System.out.println("Caiu aqui: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public Passeio readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Passeio entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Passeio> passeiosPorCliente(Long cliente_id) {
		
		List<Passeio> response = null;
		String endpoint = "http://localhost:8082/api/v1/passeio/passeiosPorCliente/" + cliente_id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Passeio[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Passeio[].class
			);
			
			Passeio[] passeios = requestResponse.getBody();
			
			response = Arrays.asList(passeios);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

}
