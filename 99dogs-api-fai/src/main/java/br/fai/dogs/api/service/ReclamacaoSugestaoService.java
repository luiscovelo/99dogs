package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.ReclamacaoSugestao;

public interface ReclamacaoSugestaoService {
	
	List<ReclamacaoSugestao> readAll();

	boolean create(ReclamacaoSugestao entity);

	ReclamacaoSugestao readById(Long id);

	boolean update(ReclamacaoSugestao entity);

	boolean deleteById(Long id);
	
	List<ReclamacaoSugestao> reclamacaoSugestaoPorCliente(Long id);
	
}
