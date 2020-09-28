package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Cliente;

public interface ClienteDao {

	List<Cliente> readAll();

	boolean create(Cliente entity);

	Cliente readById(Long id);

	boolean update(Cliente entity);

	boolean deleteById(Long id);
	
}
