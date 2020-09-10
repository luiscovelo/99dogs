package br.fai.dogs.model.entities;

public class Comportamento extends BasePojo{
	
	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	

	@Override
	public String toString() {
		return "Comportamento [descricao=" + descricao + "]";
	}

}
