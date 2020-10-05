package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ConfiguracaoDaAgendaService;
import br.fai.dogs.db.dao.ConfiguracaoDaAgendaDao;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;

@Service
public class ConfiguracaoDaAgendaServiceImpl implements ConfiguracaoDaAgendaService {
	
	@Autowired
	private ConfiguracaoDaAgendaDao configuracaoDaAgendaDao;
	
	@Override
	public boolean create(ConfiguracaoDaAgenda entity) {
		
		boolean response = configuracaoDaAgendaDao.create(entity);
		return response;
		
	}

	@Override
	public List<ConfiguracaoDaAgenda> readByProfissionalId(Long id) {
		
		List<ConfiguracaoDaAgenda> response = configuracaoDaAgendaDao.readByProfissionalId(id);
		return response;
		
	}

	@Override
	public ConfiguracaoDaAgenda readById(Long id) {
		
		ConfiguracaoDaAgenda response = configuracaoDaAgendaDao.readById(id);
		return response;
		
	}

	@Override
	public boolean delete(Long id) {
		
		boolean response = configuracaoDaAgendaDao.delete(id);
		return response;
		
	}

}
