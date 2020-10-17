package br.fai.dogs.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Avaliacao;
import br.fai.dogs.service.AvaliacaoService;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<Avaliacao> readByProfissionalId(Long id) {
		
		List<Avaliacao> response = new ArrayList<>();
		String endpoint = "http://localhost:8082/api/v1/avaliacao/read-by-profissional-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Avaliacao[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Avaliacao[].class
			);
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				Avaliacao[] avaliacoes = requestResponse.getBody();
				response = Arrays.asList(avaliacoes);
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<Avaliacao> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Avaliacao entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/avaliacao/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.add("Authorization", Helper.getUserTokenJwt(session));
						
			HttpEntity<Avaliacao> requestEntity = new HttpEntity<>(entity, headers);
			 
			ResponseEntity<Void> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Void.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Problema na requisição para avaliar o profissional: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public Avaliacao readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Avaliacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String rating(Long id) {
		
		String rating = null;
		String endpoint = "http://localhost:8082/api/v1/avaliacao/rating-by-profissional-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<String> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				String.class
			);
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				rating = requestResponse.getBody();
				
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return rating;
		
	}

}
