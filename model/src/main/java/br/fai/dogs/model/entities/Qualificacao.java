package br.fai.dogs.model.entities;

public class Qualificacao extends BasePojo{
	
	private String titular;
	private String modalidade;
	private String descricao;
	private int profissional_id;
	public String getTitular() {
		return titular;
	}
	public void setTitular(String titular) {
		this.titular = titular;
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
	public int getProfissional_id() {
		return profissional_id;
	}
	public void setProfissional_id(int profissional_id) {
		this.profissional_id = profissional_id;
	}
	@Override
	public String toString() {
		return "Qualificacao [titular=" + titular + ", modalidade=" + modalidade + ", descricao=" + descricao
				+ ", profissional_id=" + profissional_id + "]";
	}
	
	

}
