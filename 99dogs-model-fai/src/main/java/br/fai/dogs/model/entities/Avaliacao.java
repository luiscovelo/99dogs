package br.fai.dogs.model.entities;

import java.sql.Timestamp;

public class Avaliacao {

	private Timestamp created;
	private int nota;
	private String observacao;
	private Long profissionalId;
	private Long clienteId;
	private Cliente cliente;

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getNota() {
		return nota;
	}

	public void setNota(int nota) {
		this.nota = nota;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
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

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "Avaliacao [created=" + created + ", nota=" + nota + ", observacao=" + observacao + ", profissionalId="
				+ profissionalId + ", clienteId=" + clienteId + ", cliente=" + cliente + "]";
	}

}
