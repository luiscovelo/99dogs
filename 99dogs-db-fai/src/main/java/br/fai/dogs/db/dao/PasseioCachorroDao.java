package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;

public interface PasseioCachorroDao {
	
	List<PasseioCachorro> readAll();

	boolean create(PasseioCachorro entity);

	PasseioCachorro readById(Long id);

	boolean update(PasseioCachorro entity);
	
	List<Cachorro> readByPasseioId(Long id);
	
}
