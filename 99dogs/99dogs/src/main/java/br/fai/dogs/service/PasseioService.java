package br.fai.dogs.service;

import java.util.List;
import java.util.Map;

import br.fai.dogs.model.entities.Passeio;

public interface PasseioService {
	
	List<Passeio> readAll();

	Long create(Passeio entity);

	Passeio readById(Long id);

	boolean update(Passeio entity);

	boolean deleteById(Long id);
	
	List<Passeio> passeiosPorCliente(Long cliente_id);
	
	List<Passeio> passeiosPorProfissional(Long profissional_id);
	
	Map<String, Object> detalhes(Long id);
	
	boolean verificarDisponibilidade(String datahora, Long id);
	
	boolean alterarStatus(Passeio entity);
	
	List<Passeio> readAllByProfissionalIdAndStatus(Long id, String status);
	
	Map<Double, Double> localizacao(Long id);
	
}
