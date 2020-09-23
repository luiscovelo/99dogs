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
	private BaseDao pessoaDao;
	
	@Override
	public boolean create(Object entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Object entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List readAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
