package br.fai.dogs.service.impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.db.dao.PessoaDao;
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
	public Pessoa sessaoAtual() {
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setId((long) 3);
		pessoa.setTipo("CLIENTE");
		
		return pessoa;
		
		//return (Pessoa) session.getAttribute("pessoa");
		
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
	
}
