package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.fai.dogs.api.service.CachorroService;
import br.fai.dogs.model.entities.Cachorro;

@RestController
@RequestMapping("/api/v1/cachorro")
@CrossOrigin(origins="*")
public class CachorroRestController {

	@Autowired
	private CachorroService cachorroService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Cachorro>> readAll(){
		
		List<Cachorro> cachorro = cachorroService.readAll();

		if(cachorro == null || cachorro.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(cachorro);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Cachorro entity){
		boolean response = cachorroService.create(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Cachorro> readById(@PathVariable("id") Long id){
		
		Cachorro cachorro = (Cachorro) cachorroService.readById(id);
		
		if(cachorro == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cachorro);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Cachorro entity){
		
		boolean response = cachorroService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = cachorroService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/cachorrosPorCliente/{id}")
	public ResponseEntity<List<Cachorro>> cachorrosPorCliente(@PathVariable("id") Long id){
		
		List<Cachorro> response = cachorroService.cachorrosPorCliente(id);
		return ResponseEntity.ok(response);
		
	}
	
}
