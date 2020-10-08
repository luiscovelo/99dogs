package br.fai.dogs.service.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.service.PasseioService;

@Service
public class PasseioServiceImpl implements PasseioService {
	
	@Autowired
	HttpServletRequest httpRequest;
	
	@Override
	public List<Passeio> readAll() {
		
		List<Passeio> response = null;
		String endpoint = "http://localhost:8082/api/v1/passeio/read-all";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
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
			System.out.println(e.getMessage());
		}
		
		return response;

	}

	@Override
	public Long create(Passeio entity) {
		
		Long idPasseio = null;
		String endpoint  = "http://localhost:8082/api/v1/passeio/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("clienteId", entity.getClienteId());
			map.put("datahora", entity.getDatahora());
			map.put("profissionalId", entity.getProfissionalId());
			map.put("status", entity.getStatus());
			map.put("valor", entity.getValor());
			map.put("formaDePagamentoId", entity.getFormaDePagamentoId());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Long> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				Long.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				idPasseio = requestResponse.getBody();
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema na requisição de criar o passeio: " + e.getMessage());
		}
		
		return idPasseio;
		
	}

	@Override
	public Passeio readById(Long id) {
		
		Passeio response = new Passeio();
		String endpoint = "http://localhost:8082/api/v1/passeio/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Passeio> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Passeio.class
			);
			
			response = requestResponse.getBody();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para obter o passeio por id: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean update(Passeio entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Passeio> passeiosPorCliente(Long cliente_id) {
		
		List<Passeio> response = null;
		String endpoint = "http://localhost:8082/api/v1/passeio/passeiosPorCliente/" + cliente_id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
				
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
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
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<Passeio> passeiosPorProfissional(Long profissional_id) {
		
		List<Passeio> response = null;
		String endpoint = "http://localhost:8082/api/v1/passeio/passeiosPorProfissional/" + profissional_id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
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
			System.out.println(e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public Map<String, Object> detalhes(Long id) {
		
		Map<String, Object> response = new HashMap<String, Object>();
		String endpoint = "http://localhost:8082/api/v1/passeio/detalhes/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		ParameterizedTypeReference<Map<String, Object>> responseType = 
	               new ParameterizedTypeReference<Map<String, Object>>() {};
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Map<String, Object>> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				responseType
			);
			
			response = requestResponse.getBody();
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para obter o passeio por id: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean verificarDisponibilidade(String datahora, Long id) {
		
		String endpoint = "http://localhost:8082/api/v1/passeio/verificar-disponibilidade/" + datahora + "/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			HttpEntity<String> requestEntity = new HttpEntity<String>(headers);
			
			ResponseEntity<Boolean> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				Boolean.class
			);
			
			if(requestResponse.getStatusCodeValue() == 200) {
				return true;
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return false;
		
	}

	@Override
	public boolean alterarStatus(Passeio entity) {
		
		boolean response = false;
		String endpoint  = "http://localhost:8082/api/v1/passeio/alterar-status";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Authorization", Helper.getUserTokenJwt(httpRequest));
			
			Map<String, Object> map = new HashMap<>();
			
			map.put("id", entity.getId());
			map.put("status", entity.getStatus());
						
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(map, headers);
			 
			ResponseEntity<Long> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.PUT, 
				requestEntity,
				Long.class
			);			
			
			if(requestResponse.getStatusCodeValue() == 200) {
				response = true;
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao realizar a requisição para aprovar o passeio: " + e.getMessage());
		}
		
		return response;
		
	}

}
