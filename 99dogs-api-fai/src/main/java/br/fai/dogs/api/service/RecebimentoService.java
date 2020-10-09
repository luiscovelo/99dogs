package br.fai.dogs.api.service;

import java.util.List;

import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Recebimento;

public interface RecebimentoService {
	
	List<Recebimento> readAllByProfissionalId(Long id);
	
	boolean create(Recebimento entity);
	
	List<Passeio> readPasseiosSemRecebimentoPorProfissional(Long id);
	
}
