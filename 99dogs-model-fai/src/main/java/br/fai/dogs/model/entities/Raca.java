package br.fai.dogs.model.entities;

public class Raca extends BasePojo{
	
	private String nome;
	private int comportamento_id;
	private int porte_id;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getComportamento_id() {
		return comportamento_id;
	}
	public void setComportamento_id(int comportamento_id) {
		this.comportamento_id = comportamento_id;
	}
	public int getPorte_id() {
		return porte_id;
	}
	public void setPorte_id(int porte_id) {
		this.porte_id = porte_id;
	}
	@Override
	public String toString() {
		return "Raca [nome=" + nome + ", comportamento_id=" + comportamento_id + ", porte_id=" + porte_id + "]";
	}
	

}
