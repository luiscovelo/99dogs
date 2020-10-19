package br.fai.dogs.api.payment.picpay;

import java.util.List;

import br.fai.dogs.api.payment.BasePaymentService;
import br.fai.dogs.model.dto.picpay.PicpayRequest;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.TransacaoPicpay;

public interface PaymentPicpayService extends BasePaymentService<PicpayRequest,PicpayResponse> {
	
	List<TransacaoPicpay> readByPasseioId(Long id);
	
}
