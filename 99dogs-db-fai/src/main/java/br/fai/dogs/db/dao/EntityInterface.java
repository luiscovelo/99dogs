package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface EntityInterface <Entity>{
	
	List<Entity> readAll();

	boolean create(Entity object);

	Entity readById(Long id);

	boolean update(Entity object);

	boolean deleteById(Long id);
}
