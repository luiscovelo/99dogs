package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Cachorro;


@Service
public class CachorroServiceImpl implements BaseService{

	@Autowired
	private BaseDao<Object> cachorroDao;
	
	@Override
	public boolean create(Object entity) {
		
		return cachorroDao.create(entity);
	}

	@Override
	public Cachorro readById(Long id) {
		
		return (Cachorro) cachorroDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return cachorroDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return cachorroDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return cachorroDao.readAll();
	}

}
