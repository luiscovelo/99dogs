package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.FormaDePagamentoService;
import br.fai.dogs.db.dao.FormaDePagamentoDao;
import br.fai.dogs.model.entities.FormaDePagamento;

@Service
public class FormaDePagamentoServiceImpl implements FormaDePagamentoService {

	@Autowired
	private FormaDePagamentoDao formaDePagamentoDao;
	
	@Override
	public boolean create(FormaDePagamento entity) {
		
		return false;
	}

	@Override
	public FormaDePagamento readById(Long id) {
		
		return (FormaDePagamento) formaDePagamentoDao.readById(id);
	}

	@Override
	public boolean update(FormaDePagamento entity) {
		
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		
		return false;
	}

	@Override
	public List<FormaDePagamento> readAll() {
		
		return formaDePagamentoDao.readAll();
	}
}
