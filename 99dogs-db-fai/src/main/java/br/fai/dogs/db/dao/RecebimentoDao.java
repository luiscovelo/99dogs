package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Recebimento;

public interface RecebimentoDao extends BaseCrudDao<Recebimento> {
	
	List<Recebimento> readAllByProfissionalId(Long id);
	
	List<Passeio> readPasseiosSemRecebimentoPorProfissional(Long id);
	
}
