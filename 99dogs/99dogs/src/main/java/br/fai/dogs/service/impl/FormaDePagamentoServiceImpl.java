package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.service.FormaDePagamentoService;

@Service
public class FormaDePagamentoServiceImpl implements FormaDePagamentoService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpSession session;
	
	@Override
	public List<FormaDePagamento> readAll() {
		
		List<FormaDePagamento> response = null;
		String endpoint = "http://localhost:8082/api/v1/forma-de-pagamento/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<FormaDePagamento[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				FormaDePagamento[].class
			);
			
			FormaDePagamento[] formasDePagamentos = requestResponse.getBody();
			
			response = Arrays.asList(formasDePagamentos);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean create(FormaDePagamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FormaDePagamento readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(FormaDePagamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
