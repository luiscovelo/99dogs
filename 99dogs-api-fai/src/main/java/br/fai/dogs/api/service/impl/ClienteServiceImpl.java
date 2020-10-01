package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ClienteService;
import br.fai.dogs.db.dao.ClienteDao;
import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.Pessoa;

@Service
public class ClienteServiceImpl implements ClienteService {

	@Autowired
	private ClienteDao clienteDao;
	
	@Override
	public boolean create(Cliente entity) {
		
		return clienteDao.create(entity);
	}

	@Override
	public Pessoa readById(Long id) {
		
		return clienteDao.readById(id);
	}

	@Override
	public boolean update(Cliente entity) {
		
		return clienteDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		
		return clienteDao.deleteById(id);
	}

	@Override
	public List<Cliente> readAll() {
		
		return clienteDao.readAll();
	}
}
