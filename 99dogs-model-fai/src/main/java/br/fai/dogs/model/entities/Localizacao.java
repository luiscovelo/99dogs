package br.fai.dogs.model.entities;

import java.sql.Timestamp;

public class Localizacao {

	private Timestamp created;
	private Long passeioId;
	private String latitude;
	private String longitude;

	public Timestamp getCreated() {
		return created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Long getPasseioId() {
		return passeioId;
	}

	public void setPasseioId(Long passeioId) {
		this.passeioId = passeioId;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "Localizacao [created=" + created + ", passeioId=" + passeioId + ", latitude=" + latitude
				+ ", longitude=" + longitude + "]";
	}

}
