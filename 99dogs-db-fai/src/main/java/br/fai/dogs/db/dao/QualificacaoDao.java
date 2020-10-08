package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Qualificacao;

public interface QualificacaoDao extends BaseCrudDao<Qualificacao> {
		
	List<Qualificacao> readByProfissionalId(Long id);
	
}
