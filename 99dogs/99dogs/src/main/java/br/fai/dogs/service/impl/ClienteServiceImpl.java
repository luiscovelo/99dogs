package br.fai.dogs.service.impl;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Override
	public Pessoa readById(Long id) {
		
		Pessoa response = new Pessoa();
		String endpoint = "http://localhost:8082/api/v1/cliente/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
			ResponseEntity<Pessoa> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Pessoa.class
			);
			
			response = requestResponse.getBody();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para obter o cliente por id: " + e.getMessage());
		}
		
		return response;
		
	}

}
