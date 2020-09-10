package br.fai.dogs.model.entities;

public class Cliente extends BasePojo{
	
	private int pessoa_id;

	public int getPessoa_id() {
		return pessoa_id;
	}

	public void setPessoa_id(int pessoa_id) {
		this.pessoa_id = pessoa_id;
	}

	@Override
	public String toString() {
		return "Cliente [pessoa_id=" + pessoa_id + "]";
	}
	
	

}
