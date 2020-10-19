package br.fai.dogs.api.service;

import br.fai.dogs.model.entities.ConfiguracaoPicpay;

public interface ConfiguracaoPicpayService extends BaseCrudService<ConfiguracaoPicpay> {
	
	ConfiguracaoPicpay readByProfissionalId(Long id);
	
}
