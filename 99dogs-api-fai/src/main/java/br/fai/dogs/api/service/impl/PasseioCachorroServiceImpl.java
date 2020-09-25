package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.PasseioCachorro;

@Service
public class PasseioCachorroServiceImpl implements BaseService{


	@Autowired
	private BaseDao<Object> passeioCachorroDao;
	
	@Override
	public boolean create(Object entity) {
		
		return passeioCachorroDao.create(entity);
	}

	@Override
	public PasseioCachorro readById(Long id) {
		
		return (PasseioCachorro) passeioCachorroDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return passeioCachorroDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return passeioCachorroDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return passeioCachorroDao.readAll();
	}
}
