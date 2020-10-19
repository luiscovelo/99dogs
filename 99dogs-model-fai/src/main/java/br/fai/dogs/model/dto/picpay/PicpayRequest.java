package br.fai.dogs.model.dto.picpay;

public class PicpayRequest {

	private String referenId;
	private String callbackUrl;
	private String returnUrl;
	private double value;
	private String expiresAt;
	private PicpayBuyer picpayBuyer;
	private Long passeioId;

	public String getReferenId() {
		return referenId;
	}

	public void setReferenId(String referenId) {
		this.referenId = referenId;
	}

	public String getCallbackUrl() {
		return callbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public PicpayBuyer getPicpayBuyer() {
		return picpayBuyer;
	}

	public void setPicpayBuyer(PicpayBuyer picpayBuyer) {
		this.picpayBuyer = picpayBuyer;
	}

	public Long getPasseioId() {
		return passeioId;
	}

	public void setPasseioId(Long passeioId) {
		this.passeioId = passeioId;
	}

	@Override
	public String toString() {
		return "PicpayRequest [referenId=" + referenId + ", callbackUrl=" + callbackUrl + ", returnUrl=" + returnUrl
				+ ", value=" + value + ", expiresAt=" + expiresAt + ", picpayBuyer=" + picpayBuyer + ", passeioId="
				+ passeioId + "]";
	}

}
