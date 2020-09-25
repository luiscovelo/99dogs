package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.ReclamacaoSugestao;

@Service
public class ReclamacaoSugestaoServiceImpl implements BaseService{

	@Autowired
	private BaseDao<Object> reclamacaoSugestaoDao;
	
	@Override
	public boolean create(Object entity) {
		
		return reclamacaoSugestaoDao.create(entity);
	}

	@Override
	public ReclamacaoSugestao readById(Long id) {
		
		return (ReclamacaoSugestao) reclamacaoSugestaoDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return reclamacaoSugestaoDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return reclamacaoSugestaoDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return reclamacaoSugestaoDao.readAll();
	}
}
