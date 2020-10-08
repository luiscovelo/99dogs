package br.fai.dogs.api.service;

import java.util.List;

public interface BaseCrudService<T> {
	
	List<T> readAll();
	
	boolean create(T entity);

	T readById(Long id);

	boolean update(T entity);

	boolean deleteById(Long id);

}
