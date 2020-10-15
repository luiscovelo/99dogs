package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Passeio;

public interface PasseioService {
	
	List<Passeio> readAll();

	Long create(Passeio entity);

	Passeio readById(Long id);

	boolean update(Passeio entity);

	boolean deleteById(Long id);
	
	List<Passeio> passeiosPorCliente(Long cliente_id);
	
	List<Passeio> passeiosPorProfissional(Long profissional);
	
	boolean verificarDisponibilidade(String datahora, Long id);
	
	boolean alterarStatus(Passeio entity);
	
	List<Passeio> readAllByProfissionalIdAndStatus(Long id, String status);
	
}
