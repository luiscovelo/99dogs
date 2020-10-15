package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.Collections;
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
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Recebimento;
import br.fai.dogs.service.RecebimentoService;

@Service
public class RecebimentoServiceImpl implements RecebimentoService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpSession session;
	
	@Override
	public List<Recebimento> readAllByProfissionalId(Long id) {
		
		List<Recebimento> response = null;
		String endpoint = "http://localhost:8082/api/v1/recebimento/read-all-by-profissional-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Recebimento[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Recebimento[].class
			);
			
			Recebimento[] recebimentos = requestResponse.getBody();
			
			response = Arrays.asList(recebimentos);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na requisição para obter recebimento por profissional: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean create(Recebimento entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/recebimento/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("dataRecebimento", entity.getDataRecebimento());
			map.put("valor", entity.getValor());
			map.put("passeioId", entity.getPasseioId());
			map.put("formaDePagamentoId", entity.getFormaDePagamentoId());
			map.put("profissionalId", entity.getProfissionalId());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Void> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Void.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição de criar o recebimento: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<Passeio> readPasseiosSemRecebimentoPorProfissional(Long id) {
		
		List<Passeio> response = null;
		String endpoint = "http://localhost:8082/api/v1/recebimento/read-passeios-sem-recebimento-por-profissional/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Passeio[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Passeio[].class
			);
			
			Passeio[] passeios = requestResponse.getBody();
			
			response = Arrays.asList(passeios);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição de ler os passeios sem recebimento: " + e.getMessage());
		}
		
		return response;
		
	}

}
