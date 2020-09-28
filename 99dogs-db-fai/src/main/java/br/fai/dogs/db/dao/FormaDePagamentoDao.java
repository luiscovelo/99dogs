package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.FormaDePagamento;

public interface FormaDePagamentoDao {
	
	List<FormaDePagamento> readAll();

	boolean create(FormaDePagamento entity);

	FormaDePagamento readById(Long id);

	boolean update(FormaDePagamento entity);

	boolean deleteById(Long id);
	
}
