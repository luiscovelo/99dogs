package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.PasseioCachorroService;
import br.fai.dogs.db.dao.PasseioCachorroDao;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;

@Service
public class PasseioCachorroServiceImpl implements PasseioCachorroService {


	@Autowired
	private PasseioCachorroDao passeioCachorroDao;
	
	@Override
	public boolean create(PasseioCachorro entity) {
		
		return passeioCachorroDao.create(entity);
	}

	@Override
	public PasseioCachorro readById(Long id) {
		
		return (PasseioCachorro) passeioCachorroDao.readById(id);
	}

	@Override
	public boolean update(PasseioCachorro entity) {
		
		return passeioCachorroDao.update(entity);
	}

	@Override
	public List<PasseioCachorro> readAll() {
		
		return passeioCachorroDao.readAll();
	}

	@Override
	public List<Cachorro> readByPasseioId(Long id) {
		
		return passeioCachorroDao.readByPasseioId(id);
		
	}
}
