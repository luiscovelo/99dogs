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
import br.fai.dogs.model.entities.Porte;

@RestController
@RequestMapping("/api/v1/porte")
@CrossOrigin(origins="*")
public class PorteRestController {

	@Autowired
	private BaseService porteService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Porte>> readAll(){
		
		List<Porte> porte = porteService.readAll();
		
		if(porte == null || porte.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(porte);
		}
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Porte> readById(@PathVariable("id") Long id){
		
		Porte porte = (Porte) porteService.readById(id);
		
		if(porte == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(porte);
	}
}
