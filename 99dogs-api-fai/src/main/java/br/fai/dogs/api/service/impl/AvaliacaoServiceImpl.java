package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.AvaliacaoService;
import br.fai.dogs.db.dao.AvaliacaoDao;
import br.fai.dogs.model.entities.Avaliacao;

@Service
public class AvaliacaoServiceImpl implements AvaliacaoService {
	
	@Autowired
	private AvaliacaoDao avaliacaoDao;
	
	@Override
	public List<Avaliacao> readByProfissionalId(Long id) {
		return avaliacaoDao.readByProfissionalId(id);
	}

	@Override
	public List<Avaliacao> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Avaliacao entity) {
		return avaliacaoDao.create(entity);
	}

	@Override
	public Avaliacao readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Avaliacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String rating(Long id) {
		return avaliacaoDao.rating(id);
	}

}
