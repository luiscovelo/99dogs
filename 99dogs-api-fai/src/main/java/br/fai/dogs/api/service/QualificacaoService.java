package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Qualificacao;

public interface QualificacaoService {
	
	List<Qualificacao> readAll();

	boolean create(Qualificacao entity);

	Qualificacao readById(Long id);

	boolean update(Qualificacao entity);

	boolean deleteById(Long id);
	
	List<Qualificacao> readByProfissionalId(Long id);
	
}
