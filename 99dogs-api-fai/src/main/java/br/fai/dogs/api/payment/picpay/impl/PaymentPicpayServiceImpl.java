package br.fai.dogs.api.payment.picpay.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.api.payment.picpay.PaymentPicpayService;
import br.fai.dogs.api.service.ConfiguracaoPicpayService;
import br.fai.dogs.api.service.PasseioService;
import br.fai.dogs.db.dao.payment.picpay.PaymentPicpayDao;
import br.fai.dogs.model.dto.picpay.PicpayQrcode;
import br.fai.dogs.model.dto.picpay.PicpayRequest;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.TransacaoPicpay;

@Service
public class PaymentPicpayServiceImpl implements PaymentPicpayService {
	
	@Autowired
	private ConfiguracaoPicpayService configuracaoPicpayService;
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private PaymentPicpayDao paymentPicpayDao;
	
	@Override
	public PicpayResponse create(PicpayRequest entity) {
		
		PicpayResponse picpayResponse = new PicpayResponse();
		PicpayQrcode picpayQrcode = new PicpayQrcode();
		
		try {
			
			Passeio passeio = passeioService.readById(entity.getPasseioId());
			ConfiguracaoPicpay config = configuracaoPicpayService.readByProfissionalId(passeio.getProfissionalId());
			
			Map<String, Object> dados = new HashMap<String, Object>();
			Map<String, Object> comprador = new HashMap<String, Object>();
			
			comprador.put("firstName", entity.getPicpayBuyer().getFirstName());
			comprador.put("document", entity.getPicpayBuyer().getDocument());
			comprador.put("email", entity.getPicpayBuyer().getEmail());
			
			dados.put("referenceId", entity.getReferenId());
			dados.put("callbackUrl", entity.getCallbackUrl());
			dados.put("returnUrl", entity.getReturnUrl());
			dados.put("value", entity.getValue());
			dados.put("expiresAt", entity.getExpiresAt());
			dados.put("buyer", comprador);
						
			String endpoint = "https://appws.picpay.com/ecommerce/public/payments";
			
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("x-picpay-token", config.getPicpayToken());
			
			RestTemplate restTemplate = new RestTemplate();
			
			HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(dados,headers);
						
			ResponseEntity<String> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				String.class
			);
			
			JSONObject respPicpay = new JSONObject(requestResponse.getBody());
						
			picpayResponse.setHttpStatus(requestResponse.getStatusCodeValue());
			
			if(requestResponse.getStatusCodeValue() == 200) {
				
				JSONObject respPicpayQrcode = respPicpay.getJSONObject("qrcode");
				
				picpayResponse.setPasseioId(passeio.getId());
				picpayResponse.setReferenceId(respPicpay.getString("referenceId"));
				picpayResponse.setPaymentUrl(respPicpay.getString("paymentUrl"));
				picpayResponse.setExpiresAt(respPicpay.getString("expiresAt"));
								
				picpayQrcode.setContent(respPicpayQrcode.getString("content"));
				picpayQrcode.setBase64(respPicpayQrcode.getString("base64"));
				
				picpayResponse.setPicpayQrcode(picpayQrcode);
				
				boolean createInDataBase = paymentPicpayDao.create(picpayResponse);
				
				if(!createInDataBase) {
					picpayResponse.setMessageError("A transação foi criada porém não foi possível salvar o banco de dados, verifique se no seu aplicativo a transação foi efetivada.");
				}
				
			}else if(requestResponse.getStatusCodeValue() == 401 || requestResponse.getStatusCodeValue() == 500) {
				picpayResponse.setMessageError(respPicpay.getString("message"));
			}else {
				picpayResponse.setMessageError(respPicpay.getString("message") + respPicpay.getString("errors"));
			}
			
		} catch (Exception e) {
			picpayResponse.setHttpStatus(-1);
			picpayResponse.setMessageError(e.getMessage());
		}
		
		return picpayResponse;
		
	}

	@Override
	public List<TransacaoPicpay> readByPasseioId(Long id) {
		return paymentPicpayDao.readByPasseioId(id);
	}

}
