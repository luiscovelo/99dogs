package br.fai.dogs.model.entities;

public class ReclamacaoSugestao extends BasePojo {
	
	private String nome;
	private String email;
	private String assunto;
	private String mensagem;
	private Long pessoaId;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Long getPessoaId() {
		return pessoaId;
	}
	public void setPessoaId(Long pessoaId) {
		this.pessoaId = pessoaId;
	}
	
	@Override
	public String toString() {
		return "ReclamacaoSugestao [nome=" + nome + ", email=" + email + ", assunto=" + assunto + ", mensagem="
				+ mensagem + ", pessoaId=" + pessoaId + "]";
	}
	
}
