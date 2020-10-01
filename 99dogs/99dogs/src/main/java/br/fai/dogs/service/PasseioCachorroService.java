package br.fai.dogs.service;

import java.util.List;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;

public interface PasseioCachorroService {
	
	boolean create(PasseioCachorro entity);
	
	List<Cachorro> readByPasseioId(Long id);
	
}
