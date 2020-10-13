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
	public Long create(Pessoa entity, String tokenTemporario) {
		
		Long pessoa_id = null;
		String endpoint  = "http://localhost:8082/api/v1/pessoa/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", tokenTemporario);

			Map<String, Object> map = new HashMap<>();
			
			map.put("nome", entity.getNome());
			map.put("telefone", entity.getTelefone());
			map.put("email", entity.getEmail());
			map.put("senha", entity.getSenha());
			map.put("rua", entity.getRua());
			map.put("bairro", entity.getBairro());
			map.put("cidade", entity.getCidade());
			map.put("estado", entity.getEstado());
			map.put("pais", entity.getPais());
			map.put("numero", entity.getNumero());
			map.put("foto", entity.getFoto());
			map.put("tipo", entity.getTipo());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Long> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Long.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				pessoa_id = requestResponse.getBody();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um problema na requisição de criar o cliente: " + e.getMessage());
		}
		
		return pessoa_id;
		
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

	@Override
	public boolean update(Pessoa entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/pessoa/update";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));

			Map<String, Object> map = new HashMap<>();
			
			map.put("id", entity.getId());
			map.put("nome", entity.getNome());
			map.put("telefone", entity.getTelefone());
			map.put("email", entity.getEmail());
			map.put("senha", entity.getSenha());
			map.put("rua", entity.getRua());
			map.put("bairro", entity.getBairro());
			map.put("cidade", entity.getCidade());
			map.put("estado", entity.getEstado());
			map.put("pais", entity.getPais());
			map.put("numero", entity.getNumero());
			map.put("foto", entity.getFoto());
			map.put("tipo", entity.getTipo());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.PUT, 
				requestEntity,
				Boolean.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = requestResponse.getBody();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Ocorreu um problema na requisição de atualizar a pessoa: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pessoa readByEmail(String email, String tokenTemporario) {
		
		Pessoa response = new Pessoa();
		String endpoint = "http://localhost:8082/api/v1/pessoa/read-by-email?email=" + email;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", tokenTemporario);
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Pessoa> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Pessoa.class
			);
			
			response = requestResponse.getBody();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para obter a pessoa por email: " + e.getMessage());
		}
		
		return response;
		
	}
	
}
