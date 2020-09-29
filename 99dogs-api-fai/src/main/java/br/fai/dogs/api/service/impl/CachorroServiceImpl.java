package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.CachorroService;
import br.fai.dogs.db.dao.CachorroDao;
import br.fai.dogs.model.entities.Cachorro;


@Service
public class CachorroServiceImpl implements CachorroService {

	@Autowired
	private CachorroDao cachorroDao;
	
	@Override
	public boolean create(Cachorro entity) {
		
		return cachorroDao.create(entity);
	}

	@Override
	public Cachorro readById(Long id) {
		
		return (Cachorro) cachorroDao.readById(id);
	}

	@Override
	public boolean update(Cachorro entity) {
		
		return cachorroDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return cachorroDao.deleteById(id);
	}

	@Override
	public List<Cachorro> readAll() {
		
		return cachorroDao.readAll();
	}

	@Override
	public List<Cachorro> cachorrosPorCliente(Long cliente_id) {
		return cachorroDao.cachorrosPorCliente(cliente_id);
	}
	
}
