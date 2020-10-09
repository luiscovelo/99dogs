package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.PessoaService;
import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.model.entities.Pessoa;

@Service
public class PessoaServiceImpl implements PessoaService {

	@Autowired
	private PessoaDao pessoaDao;
	
	@Override
	public Long create(Pessoa entity) {
		
		return pessoaDao.create(entity);
	}

	@Override
	public Pessoa readById(Long id) {
		
		return (Pessoa) pessoaDao.readById(id);
	}

	@Override
	public boolean update(Pessoa entity) {
		
		return pessoaDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return pessoaDao.deleteById(id);
	}

	@Override
	public List<Pessoa> readAll() {
		
		return pessoaDao.readAll();
	}

	@Override
	public Pessoa validarLogin(Pessoa entity) {
		
		return pessoaDao.validarLogin(entity);
	}

	@Override
	public List<Pessoa> readAllProfissional() {
		
		return pessoaDao.readAllProfissional();
		
	}

	@Override
	public Pessoa readByEmail(String email) {
		
		return pessoaDao.readByEmail(email);
		
	}

}
