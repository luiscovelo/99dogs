package br.fai.dogs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaService {
	
	List<Pessoa> readAll();
	
	Long create(Pessoa entity, String tokenTemporario);

	Pessoa readById(Long id);

	boolean update(Pessoa entity);

	boolean deleteById(Long id);
		
	List<Pessoa> readAllProfissional();
	
	Pessoa readByEmail(String email, String tokenTemporario);
	
	boolean uploadImage(Long id, MultipartFile file);
	
}
