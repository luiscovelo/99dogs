package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaDao extends BaseCrudDao<Pessoa> {
		
	Pessoa validarLogin(Pessoa entity);
	
	List<Pessoa> readAllProfissional();
	
}
