package br.fai.dogs.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.service.CachorroService;

@Service
public class CachorroServiceImpl implements CachorroService {

	@Override
	public List<Cachorro> cachorrosPorCliente(Long cliente_id) {
		// TODO Auto-generated method stub
		return null;
	}

}
