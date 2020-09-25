package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Pessoa;

@Service
public class PessoaServiceImpl implements BaseService{

	@Autowired
	private BaseDao<Object> pessoaDao;
	
	@Override
	public boolean create(Object entity) {
		
		return pessoaDao.create(entity);
	}

	@Override
	public Pessoa readById(Long id) {
		
		return (Pessoa) pessoaDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return pessoaDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return pessoaDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return pessoaDao.readAll();
	}

}
