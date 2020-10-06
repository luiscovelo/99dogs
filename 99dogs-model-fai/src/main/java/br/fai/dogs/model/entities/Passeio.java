package br.fai.dogs.model.entities;

import java.time.LocalDateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class Passeio extends BasePojo{
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime datahora;
	
	private String status;
	private double valor;
	private Long profissionalId;
	private Long clienteId;
	private Long formaDePagamentoId;
	
	private Cliente cliente;
	private Profissional profissional;
	private FormaDePagamento formaDePagamento;
	
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
	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}
	public Long getClienteId() {
		return clienteId;
	}
	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}
	public Long getFormaDePagamentoId() {
		return formaDePagamentoId;
	}
	public void setFormaDePagamentoId(Long formaDePagamentoId) {
		this.formaDePagamentoId = formaDePagamentoId;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Profissional getProfissional() {
		return profissional;
	}
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}
	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}
	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}
	
	@Override
	public String toString() {
		return "Passeio [datahora=" + datahora + ", status=" + status + ", valor=" + valor + ", profissionalId="
				+ profissionalId + ", clienteId=" + clienteId + ", formaDePagamentoId=" + formaDePagamentoId
				+ ", cliente=" + cliente + ", profissional=" + profissional + ", formaDePagamento=" + formaDePagamento
				+ "]";
	}
	
}
