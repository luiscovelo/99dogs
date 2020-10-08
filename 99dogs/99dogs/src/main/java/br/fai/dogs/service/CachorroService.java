package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;

public interface CachorroService extends BaseCrudService<Cachorro> {
	
	List<Cachorro> cachorrosPorCliente(Long cliente_id);
	
}
