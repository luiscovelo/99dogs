package br.fai.dogs.db.dao;

import java.util.Map;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroDao extends BaseCrudDao<Cachorro> {
		
	Map<String, Object> cachorrosPorCliente(Long cliente_id);
	
}
