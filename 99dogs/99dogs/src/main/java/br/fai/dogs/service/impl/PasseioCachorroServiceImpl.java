package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;
import br.fai.dogs.service.PasseioCachorroService;

@Service
public class PasseioCachorroServiceImpl implements PasseioCachorroService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpSession session;
	
	@Override
	public boolean create(PasseioCachorro entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/passeio-cachorro/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("passeioId", entity.getPasseioId());
			map.put("cachorroId", entity.getCachorroId());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
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
			System.out.println("Ocorreu um problema ao realizar a requisição para criar passeio-cachorro: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<Cachorro> readByPasseioId(Long id) {
		
		List<Cachorro> response = null;
		String endpoint = "http://localhost:8082/api/v1/passeio-cachorro/read-by-passeio-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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

}
