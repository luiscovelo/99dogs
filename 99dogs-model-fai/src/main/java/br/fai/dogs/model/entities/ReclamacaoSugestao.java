package br.fai.dogs.model.entities;

public class ReclamacaoSugestao extends BasePojo {
	
	private String nome;
	private String email;
	private String assunto;
	private String mensagem;
	private Long clienteId;
	
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
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	@Override
	public String toString() {
		return "ReclamacaoSugestao [nome=" + nome + ", email=" + email + ", assunto=" + assunto + ", mensagem="
				+ mensagem + ", clienteId=" + clienteId + "]";
	}
	
}
