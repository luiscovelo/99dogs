package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;
import br.fai.dogs.service.ProfissionalService;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpSession session;
	
	@Override
	public List<Profissional> readAll() {
		
		List<Profissional> response = null;
		String endpoint = "http://localhost:8082/api/v1/profissional/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Profissional[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Profissional[].class
			);
			
			Profissional[] profissionais = requestResponse.getBody();

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
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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

	@Override
	public boolean create(Profissional entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Profissional readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Profissional entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Map<String, String> ticketMedioAgrupadoPorMes(Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/profissional/ticket-medio-agrupado-por-mes/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {};
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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

	@Override
	public Map<String, String> recebimentoAgrupadoPorMes(Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/profissional/recebimento-agrupado-por-mes/" + id;
		
		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			ParameterizedTypeReference<Map<String, String>> typeRef = new ParameterizedTypeReference<Map<String, String>>() {};
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
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
