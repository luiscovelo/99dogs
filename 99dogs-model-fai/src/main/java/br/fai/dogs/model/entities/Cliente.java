package br.fai.dogs.model.entities;

public class Cliente extends BasePojo{
	
	private int pessoaId;

	public int getPessoaId() {
		return pessoaId;
	}

	public void setPessoaId(int pessoaId) {
		this.pessoaId = pessoaId;
	}

	@Override
	public String toString() {
		return "Cliente [pessoaId=" + pessoaId + "]";
	}
	
	

}
