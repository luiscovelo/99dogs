package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroDao {
	
	List<Cachorro> readAll();

	boolean create(Cachorro entity);

	Cachorro readById(Long id);

	boolean update(Cachorro entity);

	boolean deleteById(Long id);
	
	List<Cachorro> cachorrosPorCliente(Long cliente_id);
	
}
