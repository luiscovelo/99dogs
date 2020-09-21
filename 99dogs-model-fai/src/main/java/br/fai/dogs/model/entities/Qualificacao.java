package br.fai.dogs.model.entities;

public class Qualificacao extends BasePojo{
	
	private String titulo;
	private String modalidade;
	private String descricao;
	private int profissionalId;
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getModalidade() {
		return modalidade;
	}
	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public int getProfissionalId() {
		return profissionalId;
	}
	public void setProfissionalId(int profissionalId) {
		this.profissionalId = profissionalId;
	}
	@Override
	public String toString() {
		return "Qualificacao [titulo=" + titulo + ", modalidade=" + modalidade + ", descricao=" + descricao
				+ ", profissionalId=" + profissionalId + "]";
	}
	
	

}
