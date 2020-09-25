package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.model.entities.FormaDePagamento;

@RestController
@RequestMapping("/api/v1/forma-de-pagamento")
@CrossOrigin(origins="*")
public class FormaDePagamentoRestController {

	@Autowired
	private BaseService formaDePagamentoService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<FormaDePagamento>> readAll(){
		
		List<FormaDePagamento> formaDePagamento = formaDePagamentoService.readAll();
		
		if(formaDePagamento == null || formaDePagamento.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(formaDePagamento);
		}
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<FormaDePagamento> readById(@PathVariable("id") Long id){
		
		FormaDePagamento formaDePagamento = (FormaDePagamento) formaDePagamentoService.readById(id);
		
		if(formaDePagamento == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(formaDePagamento);
	}
}
