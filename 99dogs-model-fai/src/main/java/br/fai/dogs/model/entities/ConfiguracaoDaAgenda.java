package br.fai.dogs.model.entities;

import java.time.LocalTime;

import org.springframework.format.annotation.DateTimeFormat;

public class ConfiguracaoDaAgenda extends BasePojo {

	private Long diaSemana;
	
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaInicio;
	
	@DateTimeFormat(pattern = "HH:mm")
	private LocalTime horaFinal;
	
	private Long tempoDePasseio;
	
	private Long profissionalId;
	
	private Double valorPasseio;
	
	public Long getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(Long diaSemana) {
		this.diaSemana = diaSemana;
	}

	public LocalTime getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(LocalTime horaInicio) {
		this.horaInicio = horaInicio;
	}

	public LocalTime getHoraFinal() {
		return horaFinal;
	}

	public void setHoraFinal(LocalTime horaFinal) {
		this.horaFinal = horaFinal;
	}

	public Long getTempoDePasseio() {
		return tempoDePasseio;
	}

	public void setTempoDePasseio(Long tempoDePasseio) {
		this.tempoDePasseio = tempoDePasseio;
	}

	public Long getProfissionalId() {
		return profissionalId;
	}

	public void setProfissionalId(Long profissionalId) {
		this.profissionalId = profissionalId;
	}

	public Double getValorPasseio() {
		return valorPasseio;
	}

	public void setValorPasseio(Double valorPasseio) {
		this.valorPasseio = valorPasseio;
	}

	@Override
	public String toString() {
		return "ConfiguracaoDaAgenda [diaSemana=" + diaSemana + ", horaInicio=" + horaInicio + ", horaFinal="
				+ horaFinal + ", tempoDePasseio=" + tempoDePasseio + ", profissionalId=" + profissionalId
				+ ", valorPasseio=" + valorPasseio + "]";
	}
	
}
