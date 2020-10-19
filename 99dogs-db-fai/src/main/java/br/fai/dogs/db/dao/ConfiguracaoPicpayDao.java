package br.fai.dogs.db.dao;

import br.fai.dogs.model.entities.ConfiguracaoPicpay;

public interface ConfiguracaoPicpayDao extends BaseCrudDao<ConfiguracaoPicpay> {
	
	ConfiguracaoPicpay readByProfissionalId(Long id);
	
}
