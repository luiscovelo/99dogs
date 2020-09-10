package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaService {
	List<Pessoa> readAll();
	boolean create(Pessoa entity);
}
