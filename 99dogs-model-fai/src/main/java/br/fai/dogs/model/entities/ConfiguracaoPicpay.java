package br.fai.dogs.model.entities;

public class ConfiguracaoPicpay extends BasePojo {

	private boolean ativo;
	private String picpayToken;
	private String picpaySeller;
	private Long profissionalId;

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public String getPicpayToken() {
		return picpayToken;
	}

	public void setPicpayToken(String picpayToken) {
		this.picpayToken = picpayToken;
	}

	public String getPicpaySeller() {
		return picpaySeller;
	}

	public void setPicpaySeller(String picpaySeller) {
		this.picpaySeller = picpaySeller;
	}

	public Long getProfissionalId() {
		return profissionalId;
	}

	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}

	@Override
	public String toString() {
		return "ConfiguracaoPicpay [ativo=" + ativo + ", picpayToken=" + picpayToken + ", picpaySeller=" + picpaySeller
				+ ", profissionalId=" + profissionalId + "]";
	}

}
