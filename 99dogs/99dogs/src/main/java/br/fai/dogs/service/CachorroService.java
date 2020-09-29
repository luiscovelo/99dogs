package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroService {
	
	List<Cachorro> cachorrosPorCliente(Long cliente_id);
	
	boolean create(Cachorro entity);
	
	Cachorro readById(Long id);
	
	boolean update(Cachorro entity);
	
}
