package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;

public interface PasseioCachorroService extends BaseCrudService<PasseioCachorro> {
		
	List<Cachorro> readByPasseioId(Long id);
	
}
