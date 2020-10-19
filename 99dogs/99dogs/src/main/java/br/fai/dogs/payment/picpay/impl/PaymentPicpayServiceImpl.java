package br.fai.dogs.payment.picpay.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.dto.picpay.PicpayRequest;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.TransacaoPicpay;
import br.fai.dogs.payment.picpay.PaymentPicpayService;

@Service
public class PaymentPicpayServiceImpl implements PaymentPicpayService {
	
	@Autowired
	private HttpSession session;
	
	@Override
	public PicpayResponse create(PicpayRequest entity) {
		
		PicpayResponse response = new PicpayResponse();
		String endpoint  = "http://localhost:8082/api/v1/picpay/create";

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<PicpayRequest> requestEntity = new HttpEntity<>(entity,headers);
						
			ResponseEntity<PicpayResponse> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.POST, 
				requestEntity,
				PicpayResponse.class
			);			
						
			if(requestResponse.getStatusCodeValue() == 200) {
				response = requestResponse.getBody();
			}
			
		} catch (Exception e) {
			System.out.println("Houve um problema ao requisitar a criação do pagamento do picpay: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public List<TransacaoPicpay> readByPasseioId(Long id) {
		
		List<TransacaoPicpay> transacoes = new ArrayList<>();
		String endpoint  = "http://localhost:8082/api/v1/picpay/read-by-passeio-id/" + id;

		RestTemplate restTemplate = new RestTemplate();
		
		try {
						
			HttpHeaders headers = new HttpHeaders();
			headers.add("Authorization", Helper.getUserTokenJwt(session));
			
			HttpEntity<String> requestEntity = new HttpEntity<>(headers);
						
			ResponseEntity<TransacaoPicpay[]> requestResponse = restTemplate.exchange(
				endpoint, 
				HttpMethod.GET, 
				requestEntity,
				TransacaoPicpay[].class
			);			
						
			if(requestResponse.getStatusCodeValue() == 200) {
				TransacaoPicpay[] response = requestResponse.getBody();
				transacoes = Arrays.asList(response);
			}
			
		} catch (Exception e) {
			System.out.println("Houve um problema ao requisitar as transações do pedido: " + e.getMessage());
		}
		
		return transacoes;
		
	}

}
