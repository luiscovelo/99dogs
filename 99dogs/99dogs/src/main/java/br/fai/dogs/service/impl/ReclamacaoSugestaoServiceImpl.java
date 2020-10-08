package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.ReclamacaoSugestao;
import br.fai.dogs.service.ReclamacaoSugestaoService;

@Service
public class ReclamacaoSugestaoServiceImpl  implements ReclamacaoSugestaoService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Override
	public List<ReclamacaoSugestao> reclamacaoSugestaoPorCliente(Long id) {
		
		List<ReclamacaoSugestao> response = null;
		String endpoint = "http://localhost:8082/api/v1/reclamacao-sugestao/reclamacaoSugestaoPorCliente/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<ReclamacaoSugestao[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				ReclamacaoSugestao[].class
			);
			
			ReclamacaoSugestao[] listReclamacaoSugestao = requestResponse.getBody();
			
			response = Arrays.asList(listReclamacaoSugestao);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean create(ReclamacaoSugestao entity) {
		
		Boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/reclamacao-sugestao/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("pessoaId", entity.getPessoaId());
			map.put("email", entity.getEmail());
			map.put("nome", entity.getNome());
			map.put("assunto", entity.getAssunto());
			map.put("mensagem", entity.getMensagem());
						
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
			System.out.println("Caiu aqui: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<ReclamacaoSugestao> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ReclamacaoSugestao readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(ReclamacaoSugestao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
