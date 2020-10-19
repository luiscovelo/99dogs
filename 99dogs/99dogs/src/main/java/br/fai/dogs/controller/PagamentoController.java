package br.fai.dogs.controller;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.fai.dogs.model.dto.picpay.PicpayBuyer;
import br.fai.dogs.model.dto.picpay.PicpayRequest;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.payment.picpay.PaymentPicpayService;
import br.fai.dogs.service.PasseioService;

@Controller
@RequestMapping("/pagamento")
public class PagamentoController {
	
	@Autowired
	private PasseioService passeioService;
	
	@Autowired
	private PaymentPicpayService paymentPicpayService;
	
	@GetMapping("/cliente/dados-do-pagamento/{id}")
	public String getPageDadosDoPagamento(@PathVariable("id") Long id, Model model) {
		
		try {
			
			Passeio passeio = passeioService.readById(id);
			
			model.addAttribute("passeio", passeio);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "/cliente/pagamento/dados-do-pagamento";
		
	}
	
	@PostMapping("/picpay/create/{id}")
	public String picpayCreate(@PathVariable("id") Long id, PicpayBuyer picpayBuyer, RedirectAttributes redirect) {
		
		try {
			
			Calendar cal = Calendar.getInstance();
			
			cal.setTimeInMillis( System.currentTimeMillis() );
			cal.add(Calendar.YEAR, 1);
			
			Instant instant = Instant.ofEpochMilli(cal.getTimeInMillis());
			DateTimeFormatter outFormatter = DateTimeFormatter
			        .ofPattern("yyyy-MM-dd'T'HH:mm:ss") 
			        .withZone(ZoneId.of("UTC"));

			String basicIso = outFormatter.format(instant);
						
			Passeio passeio = passeioService.readById(id);
			
			PicpayRequest picpayRequest = new PicpayRequest();
			
			picpayRequest.setPicpayBuyer(picpayBuyer);
			picpayRequest.setPasseioId(id);
			picpayRequest.setReferenId(id.toString());
			picpayRequest.setCallbackUrl("http://localhost:8081");
			picpayRequest.setReturnUrl("http://localhost:8081/passeio/cliente/detalhes/" + id + "?paymentSuccess");
			picpayRequest.setValue(passeio.getValor());
			picpayRequest.setExpiresAt( basicIso + "-03:00" );
			picpayRequest.setPicpayBuyer(picpayBuyer);
						
			PicpayResponse response = paymentPicpayService.create(picpayRequest);
			
			if(response.getHttpStatus() == 200 && response.getMessageError() == null) {
				redirect.addFlashAttribute("message", "Transação criada com sucesso.");
				return "redirect:/passeio/cliente/detalhes/" + id;
			}else {
				throw new Exception(response.getMessageError());
			}
			
		} catch (Exception e) {
			redirect.addFlashAttribute("message", "Ocorreu um problema na criação da transação: " + e.getMessage());
		}
		
		return "redirect:/pagamento/cliente/dados-do-pagamento/" + id;
		
	}
	
}
