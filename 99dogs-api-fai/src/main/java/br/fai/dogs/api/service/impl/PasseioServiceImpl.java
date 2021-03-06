package br.fai.dogs.api.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.PasseioService;
import br.fai.dogs.db.dao.PasseioDao;
import br.fai.dogs.model.dto.UploadImage;
import br.fai.dogs.model.entities.Localizacao;
import br.fai.dogs.model.entities.Passeio;

@Service
public class PasseioServiceImpl implements PasseioService {
	
	@Autowired
	private PasseioDao passeioDao;
	
	@Override
	public List<Passeio> readAll() {
		return passeioDao.readAll();
	}

	@Override
	public Long create(Passeio entity) {
		return passeioDao.create(entity);
	}

	@Override
	public Passeio readById(Long id) {
		return passeioDao.readById(id);
	}

	@Override
	public boolean update(Passeio entity) {
		return passeioDao.update(entity);
	}

	@Override
	public boolean deleteById(Long id) {
		return passeioDao.deleteById(id);
	}

	@Override
	public List<Passeio> passeiosPorCliente(Long cliente_id) {
		return passeioDao.passeiosPorCliente(cliente_id);
	}

	@Override
	public List<Passeio> passeiosPorProfissional(Long profissional) {
		return passeioDao.passeiosPorProfissional(profissional);
	}

	@Override
	public boolean verificarDisponibilidade(String datahora, Long id) {
		return passeioDao.verificarDisponibilidade(datahora, id);
	}

	@Override
	public boolean alterarStatus(Passeio entity) {
		return passeioDao.alterarStatus(entity);
	}

	@Override
	public List<Passeio> readAllByProfissionalIdAndStatus(Long id, String status) {
		return passeioDao.readAllByProfissionalIdAndStatus(id,status);
	}

	@Override
	public boolean createLocalization(Localizacao entity) {
		return passeioDao.createLocalization(entity);
	}

	@Override
	public Map<Double, Double> localizacao(Long id) {
		return passeioDao.localizacao(id);
	}

	@Override
	public boolean createLocalizationObj(Long id, Map<Double, Double> localizacoes) {
		return passeioDao.createLocalizationObj(id, localizacoes);
	}

}
