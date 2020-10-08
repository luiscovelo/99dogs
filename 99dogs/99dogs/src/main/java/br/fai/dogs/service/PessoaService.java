package br.fai.dogs.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import br.fai.dogs.model.entities.Pessoa;

public interface PessoaService extends BaseCrudService<Pessoa> {
	
	Pessoa validarLogin(Pessoa entity);
	
	Pessoa sessaoAtual(String tipo);
	
	Boolean gravarSessao(HttpSession session, Pessoa entity);
	
	List<Pessoa> readAllProfissional();
	
}
