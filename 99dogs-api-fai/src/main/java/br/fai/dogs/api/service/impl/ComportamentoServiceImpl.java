package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Comportamento;

@Service
public class ComportamentoServiceImpl implements BaseService{
	
	@Autowired
	private BaseDao<Object> comportamentoDao;

	@Override
	public boolean create(Object entity) {
		
		return (Boolean) null;
	}

	@Override
	public Comportamento readById(Long id) {
		
		return (Comportamento) comportamentoDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return (Boolean) null;
	}

	@Override
	public boolean deleteById(Long id) {
		
		return (Boolean) null;
	}

	@Override
	public List readAll() {
		
		return comportamentoDao.readAll();
	}
}
