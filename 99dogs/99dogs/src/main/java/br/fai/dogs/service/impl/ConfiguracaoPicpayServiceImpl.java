package br.fai.dogs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;
import br.fai.dogs.service.ConfiguracaoPicpayService;

@Service
public class ConfiguracaoPicpayServiceImpl implements ConfiguracaoPicpayService {
	
	@Autowired
	private HttpSession session;
	
	@Override
	public List<ConfiguracaoPicpay> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(ConfiguracaoPicpay entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-picpay/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
					
			HttpEntity<ConfiguracaoPicpay> requestEntity = new HttpEntity<>(entity,headers);
			 
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
			System.out.println("Ocorreu um problema na solicitação de criação da configuração do picpay: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public ConfiguracaoPicpay readById(Long id) {
		
		ConfiguracaoPicpay response = new ConfiguracaoPicpay();
		String endpoint  = "http://localhost:8082/api/v1/configuracao-picpay/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<ConfiguracaoPicpay> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				ConfiguracaoPicpay.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				response = requestResponse.getBody();
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de obter a configuração picpay por id: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean update(ConfiguracaoPicpay entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-picpay/update";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
					
			HttpEntity<ConfiguracaoPicpay> requestEntity = new HttpEntity<>(entity,headers);
			 
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.PUT, 
				requestEntity,
				Boolean.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de alterar da configuração do picpay: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/configuracao-picpay/delete-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
					
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.DELETE, 
				requestEntity,
				Boolean.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de deletar da configuração do picpay: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public ConfiguracaoPicpay readByProfissionalId(Long id) {
		
		ConfiguracaoPicpay response = new ConfiguracaoPicpay();
		String endpoint  = "http://localhost:8082/api/v1/configuracao-picpay/read-by-profissional-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
			 
			ResponseEntity<ConfiguracaoPicpay> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				ConfiguracaoPicpay.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				response = requestResponse.getBody();
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na solicitação de obter a configuração picpay do profissional_id: " + e.getMessage());
		}
		
		return response;
		
	}

}
