package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;

public interface ConfiguracaoDaAgendaDao {
	
	boolean create(ConfiguracaoDaAgenda entity);
	
	List<ConfiguracaoDaAgenda> readByProfissionalId(Long id);
	
	ConfiguracaoDaAgenda readById(Long id);
	
	boolean update(ConfiguracaoDaAgenda entity);
	
	boolean delete(Long id);
	
}
