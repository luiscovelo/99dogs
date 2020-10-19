package br.fai.dogs.model.dto.picpay;

public class PicpayResponse {

	private String referenceId;
	private String expiresAt;
	private String paymentUrl;
	private PicpayQrcode picpayQrcode;
	private Long passeioId;
	private int httpStatus;
	private String messageError;

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public PicpayQrcode getPicpayQrcode() {
		return picpayQrcode;
	}

	public void setPicpayQrcode(PicpayQrcode picpayQrcode) {
		this.picpayQrcode = picpayQrcode;
	}

	public Long getPasseioId() {
		return passeioId;
	}

	public void setPasseioId(Long passeioId) {
		this.passeioId = passeioId;
	}

	public int getHttpStatus() {
		return httpStatus;
	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public String getMessageError() {
		return messageError;
	}

	public void setMessageError(String messageError) {
		this.messageError = messageError;
	}

	@Override
	public String toString() {
		return "PicpayResponse [referenceId=" + referenceId + ", expiresAt=" + expiresAt + ", paymentUrl=" + paymentUrl
				+ ", picpayQrcode=" + picpayQrcode + ", passeioId=" + passeioId + ", httpStatus=" + httpStatus
				+ ", messageError=" + messageError + "]";
	}

}
