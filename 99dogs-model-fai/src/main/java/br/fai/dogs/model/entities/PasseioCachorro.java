package br.fai.dogs.model.entities;

public class PasseioCachorro extends BasePojo{

	private int passeioId;
	private int cachorroId;
	public int getPasseioId() {
		return passeioId;
	}
	public void setPasseioId(int passeioId) {
		this.passeioId = passeioId;
	}
	public int getCachorroId() {
		return cachorroId;
	}
	public void setCachorroId(int cachorroId) {
		this.cachorroId = cachorroId;
	}
	@Override
	public String toString() {
		return "PasseioCachorro [passeioId=" + passeioId + ", cachorroId=" + cachorroId + "]";
	}
	
	
}
