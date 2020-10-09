package br.fai.dogs.model.entities;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Recebimento extends BasePojo {
	
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private Date dataRecebimento;	
	private double valor;
	private Long formaDePagamentoId;
	private Long profissionalId;
	private Long passeioId;
	private FormaDePagamento formaDePagamento;

	public Date getDataRecebimento() {
		return dataRecebimento;
	}

	public void setDataRecebimento(Date dataRecebimento) {
		this.dataRecebimento = dataRecebimento;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public Long getFormaDePagamentoId() {
		return formaDePagamentoId;
	}

	public void setFormaDePagamentoId(Long formaDePagamentoId) {
		this.formaDePagamentoId = formaDePagamentoId;
	}

	public Long getProfissionalId() {
		return profissionalId;
	}

	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}

	public Long getPasseioId() {
		return passeioId;
	}

	public void setPasseioId(Long passeioId) {
		this.passeioId = passeioId;
	}

	public FormaDePagamento getFormaDePagamento() {
		return formaDePagamento;
	}

	public void setFormaDePagamento(FormaDePagamento formaDePagamento) {
		this.formaDePagamento = formaDePagamento;
	}

	@Override
	public String toString() {
		return "Recebimento [dataRecebimento=" + dataRecebimento + ", valor=" + valor + ", formaDePagamentoId="
				+ formaDePagamentoId + ", profissionalId=" + profissionalId + ", passeioId=" + passeioId
				+ ", formaDePagamento=" + formaDePagamento + "]";
	}

}
