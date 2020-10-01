package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.Pessoa;

public interface ClienteService {
	
	List<Cliente> readAll();

	boolean create(Cliente entity);

	Pessoa readById(Long id);

	boolean update(Cliente entity);

	boolean deleteById(Long id);
	
}
