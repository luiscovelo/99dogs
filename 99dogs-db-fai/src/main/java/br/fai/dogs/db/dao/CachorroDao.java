package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroDao extends BaseCrudDao<Cachorro> {
		
	List<Cachorro> cachorrosPorCliente(Long cliente_id);
	
}
