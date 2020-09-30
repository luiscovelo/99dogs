package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface ProfissionalService {
	
	List<Pessoa> readAll();
	
}
