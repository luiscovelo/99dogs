package br.fai.dogs.service;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import br.fai.dogs.model.entities.Pessoa;

public interface ProfissionalService {
	
	List<Pessoa> readAll();
	
	Map<String, String> passeiosAgrupadoPorMes(Long id);
	
}
