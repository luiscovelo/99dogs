package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.payment.picpay.PaymentPicpayService;
import br.fai.dogs.model.dto.picpay.PicpayRequest;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.TransacaoPicpay;

@RestController
@RequestMapping("/api/v1/picpay")
@CrossOrigin(origins="*")
public class PicpayRestController {
	
	@Autowired
	private PaymentPicpayService paymentPicpayService;
	
	@PostMapping("/create")
	public HttpEntity<PicpayResponse> create(@RequestBody PicpayRequest picpayRequest){
		
		PicpayResponse response = paymentPicpayService.create(picpayRequest);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-passeio-id/{id}")
	public HttpEntity<List<TransacaoPicpay>> readByPasseioId(@PathVariable("id") Long id){
		
		List<TransacaoPicpay> response = paymentPicpayService.readByPasseioId(id);
		return ResponseEntity.ok(response);
		
	}
	
}
