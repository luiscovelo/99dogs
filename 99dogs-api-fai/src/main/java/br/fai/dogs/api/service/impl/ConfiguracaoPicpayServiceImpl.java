package br.fai.dogs.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ConfiguracaoPicpayService;
import br.fai.dogs.db.dao.ConfiguracaoPicpayDao;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;

@Service
public class ConfiguracaoPicpayServiceImpl implements ConfiguracaoPicpayService {
	
	@Autowired
	private ConfiguracaoPicpayDao configuracaoPicpayDao;
	
	@Override
	public ConfiguracaoPicpay readByProfissionalId(Long id) {
		return configuracaoPicpayDao.readByProfissionalId(id);
	}

	@Override
	public List<ConfiguracaoPicpay> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(ConfiguracaoPicpay entity) {
		return configuracaoPicpayDao.create(entity);
	}

	@Override
	public ConfiguracaoPicpay readById(Long id) {
		return configuracaoPicpayDao.readById(id);
	}

	@Override
	public boolean update(ConfiguracaoPicpay entity) {
		return configuracaoPicpayDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return configuracaoPicpayDao.deleteById(id);
	}
	
}
