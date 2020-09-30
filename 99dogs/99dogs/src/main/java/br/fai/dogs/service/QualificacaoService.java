package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.Qualificacao;

public interface QualificacaoService {
	
	List<Qualificacao> readByProfissionalId(Long id);
	
	Qualificacao readById(Long id);
	
	boolean update (Qualificacao entity);
	
	boolean create (Qualificacao entity);
	
	boolean deleteById(Long id);
	
}
