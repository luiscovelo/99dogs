package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.model.entities.Raca;
import br.fai.dogs.service.RacaService;

@Service
public class RacaServiceImpl implements RacaService {

	@Override
	public List<Raca> readAll() {
		
		List<Raca> response = null;
		String endpoint = "http://localhost:8082/api/v1/raca/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Raca[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Raca[].class
			);
			
			Raca[] racas = requestResponse.getBody();
			
			response = Arrays.asList(racas);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

}
