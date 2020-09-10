package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.PessoaService;
import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.model.entities.Pessoa;

@Service
public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private PessoaDao pessoaDao;
	
	@Override
	public List<Pessoa> readAll() {
		
		return pessoaDao.readAll();
	}

	@Override
	public boolean create(Pessoa entity) {
		
		return pessoaDao.create(entity);
	}

}
