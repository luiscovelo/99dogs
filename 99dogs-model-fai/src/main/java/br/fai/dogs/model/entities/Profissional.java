package br.fai.dogs.model.entities;

public class Profissional extends BasePojo {

	private int pessoaId;
	private Pessoa pessoa;
	
	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	@Override
	public String toString() {
		return "Profissional [pessoaId=" + pessoaId + "]";
	}
	
}
