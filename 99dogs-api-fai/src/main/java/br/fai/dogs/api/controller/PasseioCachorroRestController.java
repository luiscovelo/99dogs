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

import br.fai.dogs.api.service.BaseService;
import br.fai.dogs.model.entities.PasseioCachorro;

@RestController
@RequestMapping("/api/v1/passeio-cachorro")
@CrossOrigin(origins="*")
public class PasseioCachorroRestController {

	@Autowired
	private BaseService passeioCachorroService;
	
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
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<PasseioCachorro> readById(@PathVariable("id") Long id){
		
		PasseioCachorro passeioCachorro = (PasseioCachorro) passeioCachorroService.readById(id);
		
		if(passeioCachorro == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(passeioCachorro);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody PasseioCachorro entity){
		
		boolean response = passeioCachorroService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = passeioCachorroService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
}
