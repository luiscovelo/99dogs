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
import br.fai.dogs.model.entities.Qualificacao;

@RestController
@RequestMapping("/api/v1/qualificacao")
@CrossOrigin(origins="*")
public class QualificacaoRestController {

	@Autowired
	private BaseService qualificacaoService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Qualificacao>> readAll(){
		
		List<Qualificacao> qualificacao = qualificacaoService.readAll();
		
		if(qualificacao == null || qualificacao.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(qualificacao);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Qualificacao entity){
		boolean response = qualificacaoService.create(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Qualificacao> readById(@PathVariable("id") Long id){
		
		Qualificacao qualificacao = (Qualificacao) qualificacaoService.readById(id);
		
		if(qualificacao == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(qualificacao);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Qualificacao entity){
		
		boolean response = qualificacaoService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = qualificacaoService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
}
