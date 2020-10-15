package br.fai.dogs.service;

import java.util.Map;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroService extends BaseCrudService<Cachorro> {
	
	Map<String, Object> cachorrosPorCliente(Long cliente_id);
	
}
