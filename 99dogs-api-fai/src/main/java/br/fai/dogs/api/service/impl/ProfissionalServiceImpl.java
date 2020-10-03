package br.fai.dogs.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ProfissionalService;
import br.fai.dogs.db.dao.ProfissionalDao;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;

@Service
public class ProfissionalServiceImpl implements ProfissionalService {
	
	@Autowired
	private ProfissionalDao profissionalDao;
	
	@Override
	public boolean create(Profissional entity) {
		
		return profissionalDao.create(entity);
	}

	@Override
	public Profissional readById(Long id) {
		
		return (Profissional) profissionalDao.readById(id);
	}

	@Override
	public boolean update(Profissional entity) {
		
		return profissionalDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return profissionalDao.deleteById(id);
	}

	@Override
	public List<Pessoa> readAll() {
		
		return profissionalDao.readAll();
	}

	@Override
	public Map<String,String> passeiosAgrupadoPorMes(Long id) {
		
		return profissionalDao.passeiosAgrupadoPorMes(id);
		
	}

}
