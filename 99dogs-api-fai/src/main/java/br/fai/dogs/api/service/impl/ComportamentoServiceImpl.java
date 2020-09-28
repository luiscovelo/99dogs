package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ComportamentoService;
import br.fai.dogs.db.dao.ComportamentoDao;
import br.fai.dogs.model.entities.Comportamento;

@Service
public class ComportamentoServiceImpl implements ComportamentoService {
	
	@Autowired
	private ComportamentoDao comportamentoDao;

	@Override
	public boolean create(Comportamento entity) {
		
		return false;
	}

	@Override
	public Comportamento readById(Long id) {
		
		return (Comportamento) comportamentoDao.readById(id);
	}

	@Override
	public boolean update(Comportamento entity) {
		
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		
		return false;
	}

	@Override
	public List<Comportamento> readAll() {
		
		return comportamentoDao.readAll();
	}
}
