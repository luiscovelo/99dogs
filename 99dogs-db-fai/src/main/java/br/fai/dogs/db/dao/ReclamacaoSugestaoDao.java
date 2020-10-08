package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.ReclamacaoSugestao;

public interface ReclamacaoSugestaoDao extends BaseCrudDao<ReclamacaoSugestao> {
		
	List<ReclamacaoSugestao> reclamacaoSugestaoPorCliente(Long id);
	
}
