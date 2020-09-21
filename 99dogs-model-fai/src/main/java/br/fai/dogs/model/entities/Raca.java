package br.fai.dogs.model.entities;

public class Raca extends BasePojo{
	
	private String nome;
	private int comportamentoId;
	private int porteId;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getComportamentoId() {
		return comportamentoId;
	}
	public void setComportamentoId(int comportamentoId) {
		this.comportamentoId = comportamentoId;
	}
	public int getPorteId() {
		return porteId;
	}
	public void setPorteId(int porteId) {
		this.porteId = porteId;
	}
	@Override
	public String toString() {
		return "Raca [nome=" + nome + ", comportamento_id=" + comportamentoId + ", porte_id=" + porteId + "]";
	}
	

}
