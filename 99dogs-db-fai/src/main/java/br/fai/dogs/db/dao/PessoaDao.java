package br.fai.dogs.db.dao;

import java.util.List;

import br.fai.dogs.model.dto.UploadImage;
import br.fai.dogs.model.entities.Pessoa;

public interface PessoaDao {
	
	List<Pessoa> readAll();
	
	Long create(Pessoa entity);

	Pessoa readById(Long id);

	boolean update(Pessoa entity);

	boolean deleteById(Long id);
	
	Pessoa validarLogin(Pessoa entity);
	
	List<Pessoa> readAllProfissional();
	
	Pessoa readByEmail(String email);
	
	boolean uploadImage(UploadImage uploadImage);
	
}
