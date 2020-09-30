package br.fai.dogs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaService {

	List<Pessoa> readAll();
	
	boolean create(Pessoa entity);
	
	Pessoa validarLogin(Pessoa entity);
	
	Pessoa sessaoAtual(String tipo);
	
	Boolean gravarSessao(HttpSession session, Pessoa entity);
	
	List<Pessoa> readAllProfissional();
	
}
