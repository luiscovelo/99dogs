package br.fai.dogs.service;

public interface BaseCrudService<T> {

	boolean create(T entity);

	T readById(Long id);

	boolean update(T entity);

	boolean deleteById(Long id);

}
