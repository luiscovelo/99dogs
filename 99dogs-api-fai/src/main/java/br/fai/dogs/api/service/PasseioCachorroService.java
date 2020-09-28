package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.PasseioCachorro;

public interface PasseioCachorroService {
	
	List<PasseioCachorro> readAll();

	boolean create(PasseioCachorro entity);

	PasseioCachorro readById(Long id);

	boolean update(PasseioCachorro entity);

	boolean deleteById(Long id);
	
}
