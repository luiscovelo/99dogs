package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.QualificacaoService;
import br.fai.dogs.db.dao.QualificacaoDao;
import br.fai.dogs.model.entities.Qualificacao;

@Service
public class QualificacaoServiceImpl implements QualificacaoService {

	@Autowired
	private QualificacaoDao qualificacaoDao;
	
	@Override
	public boolean create(Qualificacao entity) {
		
		return qualificacaoDao.create(entity);
	}

	@Override
	public Qualificacao readById(Long id) {
		
		return (Qualificacao) qualificacaoDao.readById(id);
	}

	@Override
	public boolean update(Qualificacao entity) {
		
		return qualificacaoDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return qualificacaoDao.deleteById(id);
	}

	@Override
	public List<Qualificacao> readAll() {
		
		return qualificacaoDao.readAll();
	}

}
