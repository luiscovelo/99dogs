package br.fai.dogs.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import br.fai.dogs.api.service.ProfissionalService;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;

@RestController
@RequestMapping("/api/v1/profissional")
@CrossOrigin(origins="*")
public class ProfissionalRestController {

	@Autowired
	private ProfissionalService profissionalService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Pessoa>> readAll(){
		
		List<Pessoa> profissional = profissionalService.readAll();
		
		if(profissional == null || profissional.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(profissional);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Profissional entity){
		boolean response = profissionalService.create(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Profissional> readById(@PathVariable("id") Long id){
		
		Profissional profissional = (Profissional) profissionalService.readById(id);
		
		if(profissional == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(profissional);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Profissional entity){
		
		boolean response = profissionalService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = profissionalService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/passeios-agrupado-por-mes/{id}")
	public ResponseEntity<Map<String,String>> passeiosAgrupadoPorMes(@PathVariable("id") Long id){
				
		Map<String,String> passeios = profissionalService.passeiosAgrupadoPorMes(id);
		
		return ResponseEntity.ok(passeios);
		
	}
	
	@GetMapping("/ticket-medio-agrupado-por-mes/{id}")
	public ResponseEntity<Map<String,String>> ticketMedioAgrupadoPorMes(@PathVariable("id") Long id){
				
		Map<String,String> passeios = profissionalService.ticketMedioAgrupadoPorMes(id);
		
		return ResponseEntity.ok(passeios);
		
	}
	
	@GetMapping("/recebimento-agrupado-por-mes/{id}")
	public ResponseEntity<Map<String,String>> recebimentoAgrupadoPorMes(@PathVariable("id") Long id){
				
		Map<String,String> passeios = profissionalService.recebimentoAgrupadoPorMes(id);
		
		return ResponseEntity.ok(passeios);
		
	}
	
}
