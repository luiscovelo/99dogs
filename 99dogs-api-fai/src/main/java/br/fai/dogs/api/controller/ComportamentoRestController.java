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
import br.fai.dogs.model.entities.Comportamento;

@RestController
@RequestMapping("/api/v1/comportamento")
@CrossOrigin(origins="*")
public class ComportamentoRestController {

	@Autowired
	private BaseService comportamentoService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Comportamento>> readAll(){
		
		List<Comportamento> comportamento = comportamentoService.readAll();
		
		if(comportamento == null || comportamento.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(comportamento);
		}
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Comportamento> readById(@PathVariable("id") Long id){
		
		Comportamento comportamento = (Comportamento) comportamentoService.readById(id);
		
		if(comportamento == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(comportamento);
	}
}
