package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Passeio;

public interface PasseioDao {
	
	List<Passeio> readAll();

	boolean create(Passeio entity);

	Passeio readById(Long id);

	boolean update(Passeio entity);

	boolean deleteById(Long id);
	
}