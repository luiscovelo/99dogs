package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.RacaService;
import br.fai.dogs.model.entities.Raca;

@RestController
@RequestMapping("/api/v1/raca")
@CrossOrigin(origins="*")
public class RacaRestController {

	@Autowired
	private RacaService racaService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Raca>> readAll(){
		
		List<Raca> raca = racaService.readAll();
		
		if(raca == null || raca.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(raca);
		}
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Raca> readById(@PathVariable("id") Long id){
		
		Raca raca = (Raca) racaService.readById(id);
		
		if(raca == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(raca);
	}
}
