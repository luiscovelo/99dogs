package br.fai.dogs.db.dao;

import java.util.List;

public interface BaseCrudDao<T> {
	
	List<T> readAll();
	
	boolean create(T entity);

	T readById(Long id);

	boolean update(T entity);

	boolean deleteById(Long id);

}
