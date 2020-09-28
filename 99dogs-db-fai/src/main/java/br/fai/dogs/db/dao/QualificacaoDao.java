package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Qualificacao;

public interface QualificacaoDao {
	
	List<Qualificacao> readAll();

	boolean create(Qualificacao entity);

	Qualificacao readById(Long id);

	boolean update(Qualificacao entity);

	boolean deleteById(Long id);
	
}
