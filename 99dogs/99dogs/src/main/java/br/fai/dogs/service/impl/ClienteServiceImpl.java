package br.fai.dogs.service.impl;

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
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Override
	public Pessoa readById(Long id) {
		
		Pessoa response = new Pessoa();
		String endpoint = "http://localhost:8082/api/v1/cliente/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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

	@Override
	public List<Pessoa> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
