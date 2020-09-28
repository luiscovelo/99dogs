package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaDao {
	
	List<Pessoa> readAll();

	boolean create(Pessoa entity);

	Pessoa readById(Long id);

	boolean update(Pessoa entity);

	boolean deleteById(Long id);
	
}