package br.fai.dogs.model.entities;

import java.time.LocalDateTime;

public class Passeio extends BasePojo{

	private LocalDateTime datahora;
	private String status;
	private double valor;
	private Long profissionalId;
	private Long clienteId;
	
	public LocalDateTime getDatahora() {
		return datahora;
	}
	public void setDatahora(LocalDateTime datahora) {
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
	public void setProfissionalId(Long profissional_id) {
		this.profissionalId = profissional_id;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long cliente_id) {
		this.clienteId = cliente_id;
	}
	
	@Override
	public String toString() {
		return "Passeio [datahora=" + datahora + ", status=" + status + ", valor=" + valor + ", profissional_id="
				+ profissionalId + ", cliente_id=" + clienteId + "]";
	}
	
}
