package br.fai.dogs.payment.picpay;

import java.util.List;

import br.fai.dogs.model.dto.picpay.PicpayRequest;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.TransacaoPicpay;
import br.fai.dogs.payment.BasePaymentService;

public interface PaymentPicpayService extends BasePaymentService<PicpayRequest, PicpayResponse> {
	
	List<TransacaoPicpay> readByPasseioId(Long id);
	
}
