package br.fai.dogs.api.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.PasseioService;
import br.fai.dogs.model.entities.Passeio;

@RestController
@RequestMapping("/api/v1/passeio")
@CrossOrigin(origins="*")
public class PasseioRestController {

	@Autowired
	private PasseioService passeioService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Passeio>> readAll(){
		
		List<Passeio> passeio = passeioService.readAll();
		
		if(passeio == null || passeio.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(passeio);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Passeio entity){
		boolean response = passeioService.create(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Passeio> readById(@PathVariable("id") Long id){
		
		Passeio passeio = (Passeio) passeioService.readById(id);
		
		if(passeio == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(passeio);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Passeio entity){
		
		boolean response = passeioService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = passeioService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/passeiosPorCliente/{id}")
	public HttpEntity<List<Passeio>> passeiosPorCliente(@PathVariable("id") Long id){
		
		List<Passeio> response = passeioService.passeiosPorCliente(id);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/passeiosPorProfissional/{id}")
	public HttpEntity<List<Passeio>> passeiosPorProfissional(@PathVariable("id") Long id){
		
		List<Passeio> response = passeioService.passeiosPorProfissional(id);
		return ResponseEntity.ok(response);
		
	}
	
}
