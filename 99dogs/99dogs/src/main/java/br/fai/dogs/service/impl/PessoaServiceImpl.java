package br.fai.dogs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

@Service
public class PessoaServiceImpl implements PessoaService {
	
	@Autowired
	HttpSession session;
	
	@Autowired
	private PessoaDao pessoaDao;
	
	@Override
	public List<Pessoa> readAll() {
		
		return pessoaDao.readAll();
	}

	@Override
	public boolean create(Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Pessoa validarLogin(Pessoa entity) {
		
		return pessoaDao.validarLogin(entity);
		
	}

	@Override
	public Pessoa sessaoAtual(String tipo) {
		
		Pessoa pessoa = new Pessoa();
		
		if(tipo == "p") {
			
			pessoa.setId((long) 4);
			pessoa.setTipo("PROFISSIONAL");
			pessoa.setEmail("dogwalker@gmail.com");
			pessoa.setNome("Dogwlaker");
			
			return pessoa;
			
		}
		
		pessoa.setId((long) 2);
		pessoa.setTipo("CLIENTE");
		pessoa.setEmail("cliente@hotmail.com");
		pessoa.setNome("Cliente");
		
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
		
		return pessoaDao.readAllProfissional();
		
	}

	@Override
	public Pessoa readById(Long id) {
		
		Pessoa response = new Pessoa();
		String endpoint = "http://localhost:8082/api/v1/pessoa/read-by-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
			
			HttpEntity<String> requestEntity = new HttpEntity<String>("");
			
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
