package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.ReclamacaoSugestao;

public interface ReclamacaoSugestaoService extends BaseCrudService<ReclamacaoSugestao> {
		
	List<ReclamacaoSugestao> reclamacaoSugestaoPorCliente(Long id);
	
}
