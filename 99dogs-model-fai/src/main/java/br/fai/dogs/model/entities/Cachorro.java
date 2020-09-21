package br.fai.dogs.model.entities;

import java.sql.Date;

public class Cachorro extends BasePojo{
	
	private String nome;
	private Date dataNascimento;
	private int racaId;
	private int clienteId;
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	public int getRacaId() {
		return racaId;
	}
	public void setRacaId(int racaId) {
		this.racaId = racaId;
	}
	public int getClienteId() {
		return clienteId;
	}
	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}
	@Override
	public String toString() {
		return "Cachorro [nome=" + nome + ", dataNascimento=" + dataNascimento + ", racaId=" + racaId + ", clienteId="
				+ clienteId + "]";
	}
	
	

}
