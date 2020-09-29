package br.fai.dogs.model.entities;

import java.sql.Timestamp;

public class Passeio extends BasePojo{

	private Timestamp datahora;
	private String status;
	private double valor;
	private Long profissionalId;
	private Long clienteId;
	
	public Timestamp getDatahora() {
		return datahora;
	}
	public void setDatahora(Timestamp datahora) {
		this.datahora = datahora;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public Long getProfissionalId() {
		return profissionalId;
	}
	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	
	@Override
	public String toString() {
		return "Passeio [datahora=" + datahora + ", status=" + status + ", valor=" + valor + ", profissionalId="
				+ profissionalId + ", clienteId=" + clienteId + "]";
	}
	
}
