package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Raca;
import br.fai.dogs.service.RacaService;

@Service
public class RacaServiceImpl implements RacaService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Override
	public List<Raca> readAll() {
		
		List<Raca> response = null;
		String endpoint = "http://localhost:8082/api/v1/raca/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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

	@Override
	public boolean create(Raca entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Raca readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Raca entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
