package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Cliente;

@Service
public class ClienteServiceImpl implements BaseService{

	@Autowired
	private BaseDao<Object> clienteDao;
	
	@Override
	public boolean create(Object entity) {
		
		return clienteDao.create(entity);
	}

	@Override
	public Cliente readById(Long id) {
		
		return (Cliente) clienteDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return clienteDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return clienteDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return clienteDao.readAll();
	}
}
