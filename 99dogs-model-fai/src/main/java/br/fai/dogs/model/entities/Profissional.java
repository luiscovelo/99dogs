package br.fai.dogs.model.entities;

public class Profissional extends BasePojo {

	private int pessoaId;
	private int mediaAvaliacao;
	private int qtdeAvaliacao;
	private Pessoa pessoa;

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	public int getMediaAvaliacao() {
		return mediaAvaliacao;
	}

	public void setMediaAvaliacao(int mediaAvaliacao) {
		this.mediaAvaliacao = mediaAvaliacao;
	}

	public int getQtdeAvaliacao() {
		return qtdeAvaliacao;
	}

	public void setQtdeAvaliacao(int qtdeAvaliacao) {
		this.qtdeAvaliacao = qtdeAvaliacao;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	@Override
	public String toString() {
		return "Profissional [pessoaId=" + pessoaId + ", mediaAvaliacao=" + mediaAvaliacao + ", qtdeAvaliacao="
				+ qtdeAvaliacao + ", pessoa=" + pessoa + "]";
	}

}
