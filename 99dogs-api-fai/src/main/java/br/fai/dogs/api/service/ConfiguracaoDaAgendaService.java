package br.fai.dogs.api.service;

import java.util.List;
import java.util.Map;

import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;

public interface ConfiguracaoDaAgendaService {
	
	boolean create(ConfiguracaoDaAgenda entity);
	
	boolean update(ConfiguracaoDaAgenda entity);
	
	List<ConfiguracaoDaAgenda> readByProfissionalId(Long id);
	
	ConfiguracaoDaAgenda readById(Long id);
	
	boolean delete(Long id);
	
	Map<String,String> horariosDisponiveisPorData(String data, Long id);
	
}
