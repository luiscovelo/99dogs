package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.ReclamacaoSugestao;

public interface ReclamacaoSugestaoService {
	
	List<ReclamacaoSugestao> reclamacaoSugestaoPorCliente(Long id);
	
	boolean create(ReclamacaoSugestao entity);
	
}
