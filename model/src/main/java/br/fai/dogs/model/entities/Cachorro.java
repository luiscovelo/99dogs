package br.fai.dogs.model.entities;

import java.sql.Date;

public class Cachorro extends BasePojo{
	
	private String nome;
	private Date data_nascimento;
	private int raca_id;
	private int cliente_id;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getData_nascimento() {
		return data_nascimento;
	}
	public void setData_nascimento(Date data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
	public int getRaca_id() {
		return raca_id;
	}
	public void setRaca_id(int raca_id) {
		this.raca_id = raca_id;
	}
	public int getCliente_id() {
		return cliente_id;
	}
	public void setCliente_id(int cliente_id) {
		this.cliente_id = cliente_id;
	}
	@Override
	public String toString() {
		return "Cachorro [nome=" + nome + ", data_nascimento=" + data_nascimento + ", raca_id=" + raca_id
				+ ", cliente_id=" + cliente_id + "]";
	}
	
	

}
