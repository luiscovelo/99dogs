package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Profissional;

@Service
public class ProfissionalServiceImpl implements BaseService{
	
	@Autowired
	private BaseDao<Object> profissionalDao;
	
	@Override
	public boolean create(Object entity) {
		
		return profissionalDao.create(entity);
	}

	@Override
	public Profissional readById(Long id) {
		
		return (Profissional) profissionalDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return profissionalDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return profissionalDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return profissionalDao.readAll();
	}

}