package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroService {

	List<Cachorro> readAll();

	boolean create(Cachorro entity);

	Cachorro readById(Long id);

	boolean update(Cachorro entity);

	boolean deleteById(Long id);
	
}
