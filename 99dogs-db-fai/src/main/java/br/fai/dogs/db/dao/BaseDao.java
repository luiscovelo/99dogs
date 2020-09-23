package br.fai.dogs.db.dao;

import java.util.List;


public interface BaseDao <Entity>{
	
	List<Entity> readAll();

	boolean create(Entity entity);

	Entity readById(Long id);

	boolean update(Entity entity);

	boolean deleteById(Long id);
}
