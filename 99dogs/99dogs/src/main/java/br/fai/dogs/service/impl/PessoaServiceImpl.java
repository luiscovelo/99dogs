package br.fai.dogs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.service.PessoaService;

public class PessoaServiceImpl implements PessoaService{

	@Autowired
	private BaseDao pessoaDao;
	
	@Override
	public List<Pessoa> readAll() {
		
		return pessoaDao.readAll();
	}

	@Override
	public boolean create(Pessoa entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
