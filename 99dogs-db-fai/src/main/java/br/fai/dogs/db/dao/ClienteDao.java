package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.Pessoa;

public interface ClienteDao {

	List<Cliente> readAll();

	boolean create(Cliente entity);

	Pessoa readById(Long id);

	boolean update(Cliente entity);

	boolean deleteById(Long id);
	
}
