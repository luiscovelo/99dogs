package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;

public interface ProfissionalService {
	
	List<Pessoa> readAll();

	boolean create(Profissional entity);

	Profissional readById(Long id);

	boolean update(Profissional entity);

	boolean deleteById(Long id);
	
}
