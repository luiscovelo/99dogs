package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Porte;

public interface PorteDao {
	
	List<Porte> readAll();

	boolean create(Porte entity);

	Porte readById(Long id);

	boolean update(Porte entity);

	boolean deleteById(Long id);
	
}
