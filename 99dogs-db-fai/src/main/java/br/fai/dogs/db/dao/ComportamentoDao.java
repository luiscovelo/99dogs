package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Comportamento;

public interface ComportamentoDao {
	
	List<Comportamento> readAll();

	boolean create(Comportamento entity);

	Comportamento readById(Long id);

	boolean update(Comportamento entity);

	boolean deleteById(Long id);
	
}
