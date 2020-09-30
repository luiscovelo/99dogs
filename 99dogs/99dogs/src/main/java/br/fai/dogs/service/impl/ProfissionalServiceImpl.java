package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {

	@Override
	public List<Pessoa> readAll() {
		
		List<Pessoa> response = null;
		String endpoint = "http://localhost:8082/api/v1/profissional/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Pessoa[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Pessoa[].class
			);
			
			Pessoa[] profissionais = requestResponse.getBody();

			response = Arrays.asList(profissionais);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

}
