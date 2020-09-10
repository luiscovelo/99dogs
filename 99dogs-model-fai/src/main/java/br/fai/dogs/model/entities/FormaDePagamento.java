package br.fai.dogs.model.entities;

public class FormaDePagamento extends BasePojo{
	
	private String tipo;

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return "FormaDePagamento [tipo=" + tipo + "]";
	}
	
	

}
