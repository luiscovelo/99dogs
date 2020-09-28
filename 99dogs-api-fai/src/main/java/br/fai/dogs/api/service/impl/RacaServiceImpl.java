package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.RacaService;
import br.fai.dogs.db.dao.RacaDao;
import br.fai.dogs.model.entities.Raca;

@Service
public class RacaServiceImpl implements RacaService {

	@Autowired
	private RacaDao racaDao;
	
	@Override
	public boolean create(Raca entity) {
		
		return false;
	}

	@Override
	public Raca readById(Long id) {
		
		return (Raca) racaDao.readById(id);
	}

	@Override
	public boolean update(Raca entity) {
		
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		
		return false;
	}

	@Override
	public List<Raca> readAll() {
		
		return racaDao.readAll();
	}
}
