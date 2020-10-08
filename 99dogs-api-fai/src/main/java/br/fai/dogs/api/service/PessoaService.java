package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaService extends BaseCrudService<Pessoa> {
		
	Pessoa validarLogin(Pessoa entity);
	
	List<Pessoa> readAllProfissional();
	
}
