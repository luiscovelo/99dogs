package br.fai.dogs.api.service;

import java.util.List;
import java.util.Map;

import br.fai.dogs.model.entities.Profissional;

public interface ProfissionalService {
	
	List<Profissional> readAll();

	boolean create(Profissional entity);

	Profissional readById(Long id);

	boolean update(Profissional entity);

	boolean deleteById(Long id);
	
	Map<String,String> passeiosAgrupadoPorMes(Long id);
	
	Map<String,String> ticketMedioAgrupadoPorMes(Long id);
	
	Map<String,String> recebimentoAgrupadoPorMes(Long id);
	
}
