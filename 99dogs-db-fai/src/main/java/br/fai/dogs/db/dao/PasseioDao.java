package br.fai.dogs.db.dao;

import java.util.List;

import org.json.JSONObject;

import br.fai.dogs.model.entities.Passeio;

public interface PasseioDao {
	
	List<Passeio> readAll();

	Long create(Passeio entity);

	Passeio readById(Long id);

	boolean update(Passeio entity);

	boolean deleteById(Long id);
	
	List<Passeio> passeiosPorCliente(Long cliente_id);
	
	List<Passeio> passeiosPorProfissional(Long profissional_id);
	
	JSONObject detalhes(Long id);
	
}
