package br.fai.dogs.api.service;

import java.util.List;


public interface BaseService <Entity>{
	
	List<Entity> readAll();

	boolean create(Entity entity);

	Entity readById(Long id);

	boolean update(Entity entity);

	boolean deleteById(Long id);
}
