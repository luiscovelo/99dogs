package br.fai.dogs.model.entities;

public class TransacaoPicpay extends BasePojo {

	private String referenceId;
	private String paymentUrl;
	private String expiresAt;
	private String qrcodeContent;
	private String qrcodeBase64;

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getPaymentUrl() {
		return paymentUrl;
	}

	public void setPaymentUrl(String paymentUrl) {
		this.paymentUrl = paymentUrl;
	}

	public String getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(String expiresAt) {
		this.expiresAt = expiresAt;
	}

	public String getQrcodeContent() {
		return qrcodeContent;
	}

	public void setQrcodeContent(String qrcodeContent) {
		this.qrcodeContent = qrcodeContent;
	}

	public String getQrcodeBase64() {
		return qrcodeBase64;
	}

	public void setQrcodeBase64(String qrcodeBase64) {
		this.qrcodeBase64 = qrcodeBase64;
	}

	@Override
	public String toString() {
		return "TransacaoPicpay [referenceId=" + referenceId + ", paymentUrl=" + paymentUrl + ", expiresAt=" + expiresAt
				+ ", qrcodeContent=" + qrcodeContent + ", qrcodeBase64=" + qrcodeBase64 + "]";
	}

}
