package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.FormaDePagamento;

public interface FormaDePagamentoService {
	
	List<FormaDePagamento> readAll();

	boolean create(FormaDePagamento entity);

	FormaDePagamento readById(Long id);

	boolean update(FormaDePagamento entity);

	boolean deleteById(Long id);
	
}
