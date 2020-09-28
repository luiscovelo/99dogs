package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Raca;

public interface RacaService {
	
	List<Raca> readAll();

	boolean create(Raca entity);

	Raca readById(Long id);

	boolean update(Raca entity);

	boolean deleteById(Long id);
	
}
