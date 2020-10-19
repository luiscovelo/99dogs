package br.fai.dogs.model.dto.picpay;

public class PicpayQrcode {

	private String content;
	private String base64;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getBase64() {
		return base64;
	}

	public void setBase64(String base64) {
		this.base64 = base64;
	}

	@Override
	public String toString() {
		return "PicpayQrcode [content=" + content + ", base64=" + base64 + "]";
	}

}
