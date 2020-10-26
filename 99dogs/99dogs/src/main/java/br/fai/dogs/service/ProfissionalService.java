package br.fai.dogs.service;

import java.util.Map;

import br.fai.dogs.model.entities.Profissional;

public interface ProfissionalService extends BaseCrudService<Profissional> {
		
	Map<String, String> passeiosAgrupadoPorMes(Long id);
	
	Map<String, String> ticketMedioAgrupadoPorMes(Long id);
	
	Map<String, String> recebimentoAgrupadoPorMes(Long id);
	
}
