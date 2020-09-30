package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.FormaDePagamento;

public interface FormaDePagamentoService {
	
	List<FormaDePagamento> readAll();
	
}
