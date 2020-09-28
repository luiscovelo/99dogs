package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Raca;

public interface RacaDao {

	List<Raca> readAll();

	boolean create(Raca entity);

	Raca readById(Long id);

	boolean update(Raca entity);

	boolean deleteById(Long id);
	
}
