package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ReclamacaoSugestaoService;
import br.fai.dogs.db.dao.ReclamacaoSugestaoDao;
import br.fai.dogs.model.entities.ReclamacaoSugestao;

@Service
public class ReclamacaoSugestaoServiceImpl implements ReclamacaoSugestaoService {

	@Autowired
	private ReclamacaoSugestaoDao reclamacaoSugestaoDao;
	
	@Override
	public boolean create(ReclamacaoSugestao entity) {
		
		return reclamacaoSugestaoDao.create(entity);
	}

	@Override
	public ReclamacaoSugestao readById(Long id) {
		
		return (ReclamacaoSugestao) reclamacaoSugestaoDao.readById(id);
	}

	@Override
	public boolean update(ReclamacaoSugestao entity) {
		
		return reclamacaoSugestaoDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return reclamacaoSugestaoDao.deleteById(id);
	}

	@Override
	public List<ReclamacaoSugestao> readAll() {
		
		return reclamacaoSugestaoDao.readAll();
	}
}
