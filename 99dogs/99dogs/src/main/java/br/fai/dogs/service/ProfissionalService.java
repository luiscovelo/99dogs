package br.fai.dogs.service;

import java.util.Map;

import br.fai.dogs.model.entities.Pessoa;

public interface ProfissionalService extends BaseCrudService<Pessoa> {
		
	Map<String, String> passeiosAgrupadoPorMes(Long id);
	
}
