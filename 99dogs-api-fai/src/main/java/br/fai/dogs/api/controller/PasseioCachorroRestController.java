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

import br.fai.dogs.api.service.PasseioCachorroService;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;

@RestController
@RequestMapping("/api/v1/passeio-cachorro")
@CrossOrigin(origins="*")
public class PasseioCachorroRestController {

	@Autowired
	private PasseioCachorroService passeioCachorroService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<PasseioCachorro>> readAll(){
		
		List<PasseioCachorro> passeioCachorro = passeioCachorroService.readAll();
		
		if(passeioCachorro == null || passeioCachorro.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(passeioCachorro);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody PasseioCachorro entity){
		
		boolean response = passeioCachorroService.create(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-passeio-id/{id}")
	public ResponseEntity<List<Cachorro>> readById(@PathVariable("id") Long id){
		
		List<Cachorro> cachorros = passeioCachorroService.readByPasseioId(id);
		
		if(cachorros == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cachorros);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody PasseioCachorro entity){
		
		boolean response = passeioCachorroService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
}
