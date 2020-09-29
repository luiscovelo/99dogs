package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.Passeio;

public interface PasseioService {
	
	List<Passeio> readAll();

	boolean create(Passeio entity);

	Passeio readById(Long id);

	boolean update(Passeio entity);

	boolean deleteById(Long id);
	
	List<Passeio> passeiosPorCliente(Long cliente_id);
	
}
