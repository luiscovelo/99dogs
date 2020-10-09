package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.RecebimentoService;
import br.fai.dogs.db.dao.RecebimentoDao;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Recebimento;

@Service
public class RecebimentoServiceImpl implements RecebimentoService {
	
	@Autowired
	private RecebimentoDao recebimentoDao;
	
	@Override
	public List<Recebimento> readAllByProfissionalId(Long id) {
		
		return recebimentoDao.readAllByProfissionalId(id);
		
	}

	@Override
	public boolean create(Recebimento entity) {
		
		return recebimentoDao.create(entity);
		
	}

	@Override
	public List<Passeio> readPasseiosSemRecebimentoPorProfissional(Long id) {
		
		return recebimentoDao.readPasseiosSemRecebimentoPorProfissional(id);
		
	}

}
