package br.fai.dogs.service;

import br.fai.dogs.model.entities.Pessoa;

public interface ClienteService {
	
	Pessoa readById(Long id);
	
}
