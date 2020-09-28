package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Porte;

public interface PorteService {
	
	List<Porte> readAll();

	boolean create(Porte entity);

	Porte readById(Long id);

	boolean update(Porte entity);

	boolean deleteById(Long id);
	
}
