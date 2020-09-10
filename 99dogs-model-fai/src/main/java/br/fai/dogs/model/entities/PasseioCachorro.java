package br.fai.dogs.model.entities;

public class PasseioCachorro extends BasePojo{

	private int passeio_id;
	private int cachorro_id;
	public int getPasseio_id() {
		return passeio_id;
	}
	public void setPasseio_id(int passeio_id) {
		this.passeio_id = passeio_id;
	}
	public int getCachorro_id() {
		return cachorro_id;
	}
	public void setCachorro_id(int cachorro_id) {
		this.cachorro_id = cachorro_id;
	}
	@Override
	public String toString() {
		return "PasseioCachorro [passeio_id=" + passeio_id + ", cachorro_id=" + cachorro_id + "]";
	}
	
	
}
