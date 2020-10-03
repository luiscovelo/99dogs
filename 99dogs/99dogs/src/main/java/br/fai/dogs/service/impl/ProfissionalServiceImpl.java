package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
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

	@Override
	public Map<String, String> passeiosAgrupadoPorMes(Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/profissional/passeios-agrupado-por-mes/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {};
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Map<String, String>> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				typeRef
			);
			
			if(requestResponse.getStatusCodeValue() == 200) {
				return requestResponse.getBody();
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return null;
		
		
	}

}
