package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaDao {
	
	List<Pessoa> readAll();
}
