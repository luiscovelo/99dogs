package br.fai.dogs.db.dao.payment.picpay;

import java.util.List;

import br.fai.dogs.db.dao.payment.BasePaymentDao;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.TransacaoPicpay;

public interface PaymentPicpayDao extends BasePaymentDao<PicpayResponse> {
	
	List<TransacaoPicpay> readByPasseioId(Long id);
	
}
