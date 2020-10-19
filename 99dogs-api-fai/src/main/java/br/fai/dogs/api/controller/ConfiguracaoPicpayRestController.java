package br.fai.dogs.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.ConfiguracaoPicpayService;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;

@RestController
@RequestMapping("/api/v1/configuracao-picpay")
@CrossOrigin(origins="*")
public class ConfiguracaoPicpayRestController {
	
	@Autowired
	private ConfiguracaoPicpayService configuracaoPicpayService;
	
	@GetMapping("/read-by-profissional-id/{id}")
	public HttpEntity<ConfiguracaoPicpay> readByProfissionalId(@PathVariable("id") Long id){
		
		ConfiguracaoPicpay response = configuracaoPicpayService.readByProfissionalId(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/create")
	public HttpEntity<Boolean> create(@RequestBody ConfiguracaoPicpay configuracaoPicpay){
		
		boolean response = configuracaoPicpayService.create(configuracaoPicpay);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public HttpEntity<ConfiguracaoPicpay> readById(@PathVariable("id") Long id){
		
		ConfiguracaoPicpay response = configuracaoPicpayService.readById(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/update")
	public HttpEntity<Boolean> update(@RequestBody ConfiguracaoPicpay configuracaoPicpay){
		
		boolean response = configuracaoPicpayService.update(configuracaoPicpay);
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("/delete-by-id/{id}")
	public HttpEntity<Boolean> deleteById(@PathVariable("id") Long id){
		
		boolean response = configuracaoPicpayService.deleteById(id);
		return ResponseEntity.ok(response);
		
	}
	
}
