package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Qualificacao;

public interface QualificacaoService extends BaseCrudService<Qualificacao> {
		
	List<Qualificacao> readByProfissionalId(Long id);
	
}
