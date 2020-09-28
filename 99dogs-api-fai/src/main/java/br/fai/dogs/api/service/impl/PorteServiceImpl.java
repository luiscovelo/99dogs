package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.PorteService;
import br.fai.dogs.db.dao.PorteDao;
import br.fai.dogs.model.entities.Porte;

@Service
public class PorteServiceImpl implements PorteService {

	@Autowired
	private PorteDao porteDao;
	
	@Override
	public boolean create(Porte entity) {
		
		return false;
	}

	@Override
	public Porte readById(Long id) {
		
		return (Porte) porteDao.readById(id);
	}

	@Override
	public boolean update(Porte entity) {
		
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		
		return false;
	}

	@Override
	public List<Porte> readAll() {
		
		return porteDao.readAll();
	}
}
