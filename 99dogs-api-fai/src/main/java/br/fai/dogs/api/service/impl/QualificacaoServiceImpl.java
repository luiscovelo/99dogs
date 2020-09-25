package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Qualificacao;

@Service
public class QualificacaoServiceImpl implements BaseService {

	@Autowired
	private BaseDao<Object> qualificacaoDao;
	
	@Override
	public boolean create(Object entity) {
		
		return qualificacaoDao.create(entity);
	}

	@Override
	public Qualificacao readById(Long id) {
		
		return (Qualificacao) qualificacaoDao.readById(id);
	}

	@Override
	public boolean update(Object entity) {
		
		return qualificacaoDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return qualificacaoDao.deleteById(id);
	}

	@Override
	public List readAll() {
		
		return qualificacaoDao.readAll();
	}

}
