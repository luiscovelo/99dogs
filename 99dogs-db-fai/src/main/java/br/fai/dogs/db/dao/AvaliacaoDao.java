package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Avaliacao;

public interface AvaliacaoDao extends BaseCrudDao<Avaliacao> {
	
	List<Avaliacao> readByProfissionalId(Long id);
	
	String rating(Long id);
	
}
