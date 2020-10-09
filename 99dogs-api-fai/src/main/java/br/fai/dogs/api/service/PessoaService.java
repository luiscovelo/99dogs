package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaService {
	
	List<Pessoa> readAll();
	
	Long create(Pessoa entity);

	Pessoa readById(Long id);

	boolean update(Pessoa entity);

	boolean deleteById(Long id);
	
	Pessoa validarLogin(Pessoa entity);
	
	List<Pessoa> readAllProfissional();
	
	Pessoa readByEmail(String email);
	
}
