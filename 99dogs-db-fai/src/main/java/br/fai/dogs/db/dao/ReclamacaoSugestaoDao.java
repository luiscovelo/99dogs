package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.ReclamacaoSugestao;

public interface ReclamacaoSugestaoDao {
	
	List<ReclamacaoSugestao> readAll();

	boolean create(ReclamacaoSugestao entity);

	ReclamacaoSugestao readById(Long id);

	boolean update(ReclamacaoSugestao entity);

	boolean deleteById(Long id);
	
	List<ReclamacaoSugestao> reclamacaoSugestaoPorCliente(Long id);
	
}
