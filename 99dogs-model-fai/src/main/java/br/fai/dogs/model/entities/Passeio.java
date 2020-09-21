package br.fai.dogs.model.entities;

import java.sql.Date;
import java.sql.Time;

public class Passeio extends BasePojo{

	private Date data;
	private Time hora;
	private String status;
	private double valor;
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
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
	@Override
	public String toString() {
		return "Passeio [data=" + data + ", hora=" + hora + ", status=" + status + ", valor=" + valor + "]";
	}
	
	
}
