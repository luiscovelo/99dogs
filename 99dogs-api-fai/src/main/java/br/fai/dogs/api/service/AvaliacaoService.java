package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Avaliacao;

public interface AvaliacaoService extends BaseCrudService<Avaliacao> {
	
	List<Avaliacao> readByProfissionalId(Long id);
	
	String rating(Long id);
	
}
