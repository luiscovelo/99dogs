package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;

public interface ProfissionalDao {
	
	List<Pessoa> readAll();

	boolean create(Profissional entity);

	Profissional readById(Long id);

	boolean update(Profissional entity);

	boolean deleteById(Long id);
	
}
