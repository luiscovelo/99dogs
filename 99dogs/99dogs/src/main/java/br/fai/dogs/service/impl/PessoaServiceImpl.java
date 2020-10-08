package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Autowired
	HttpSession session;
		
	@Override
	public List<Pessoa> readAll() {
		
		List<Pessoa> response = null;
		String endpoint = "http://localhost:8082/api/v1/pessoa/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Pessoa[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Pessoa[].class
			);
			
			Pessoa[] pessoas = requestResponse.getBody();
			
			response = Arrays.asList(pessoas);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para obter as pessoas: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean create(Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pessoa validarLogin(Pessoa entity) {
		
		Pessoa pessoa = null;
		String endpoint  = "http://localhost:8082/api/v1/pessoa/validarLogin";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("email", entity.getEmail());
			map.put("senha", entity.getSenha());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Pessoa> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Pessoa.class
			);			
						
			if(requestResponse.getStatusCodeValue() == 200) {				
				pessoa = requestResponse.getBody();
			}
			
		} catch (Exception e) {
			System.out.println("Problema na requisição de validação pessoa: " + e.getMessage());
		}
		
		return pessoa;
		
	}

	@Override
	public Pessoa sessaoAtual(String tipo) {
		
		Pessoa pessoa = new Pessoa();
		
		if(session.getAttribute("pessoa") != null) {
			pessoa = (Pessoa) session.getAttribute("pessoa");

		}else {
			
			if(tipo == "p") {
				
				pessoa.setId((long) 4);
				pessoa.setTipo("PROFISSIONAL");
				pessoa.setEmail("dogwalker@gmail.com");
				pessoa.setNome("Dogwlaker");
				
			}else {
				
				pessoa.setId((long) 2); 
				pessoa.setTipo("CLIENTE");
				pessoa.setEmail("cliente@hotmail.com");
				pessoa.setNome("Cliente");
				
			}
			
		}
		
		return pessoa;
		
	}

	@Override
	public Boolean gravarSessao(HttpSession session, Pessoa entity) {
		
		try {
			
			session.setAttribute("pessoa", entity);
			
			if(session.getAttribute("pessoa") != null) {
				return true;
			}
			
		} catch (Exception e) {
			return false;
		}
		
		return false;
		
	}

	@Override
	public List<Pessoa> readAllProfissional() {
		
		List<Pessoa> response = null;
		String endpoint = "http://localhost:8082/api/v1/pessoa/read-all-profissional";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Pessoa[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Pessoa[].class
			);
			
			Pessoa[] profissionais = requestResponse.getBody();
			
			response = Arrays.asList(profissionais);
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para obter os profissionais: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public Pessoa readById(Long id) {
		
		Pessoa response = new Pessoa();
		String endpoint = "http://localhost:8082/api/v1/pessoa/read-by-id/" + id;

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
			System.out.println("Ocorreu um problema ao realizar a requisição para obter a pessoa por id: " + e.getMessage());
		}
		
		return response;
		
	}
	
}
