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
import br.fai.dogs.model.entities.ReclamacaoSugestao;

@RestController
@RequestMapping("/api/v1/reclamacao-sugestao")
@CrossOrigin(origins="*")
public class ReclamacaoSugestaoRestController {

	@Autowired
	private BaseService reclamacaoSugestaoService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<ReclamacaoSugestao>> readAll(){
		
		List<ReclamacaoSugestao> reclamacaoSugestaos = reclamacaoSugestaoService.readAll();
		
		if(reclamacaoSugestaos == null || reclamacaoSugestaos.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(reclamacaoSugestaos);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody ReclamacaoSugestao entity){
		boolean response = reclamacaoSugestaoService.create(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<ReclamacaoSugestao> readById(@PathVariable("id") Long id){
		
		ReclamacaoSugestao reclamacaoSugestao = (ReclamacaoSugestao) reclamacaoSugestaoService.readById(id);
		
		if(reclamacaoSugestao == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(reclamacaoSugestao);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody ReclamacaoSugestao entity){
		
		boolean response = reclamacaoSugestaoService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = reclamacaoSugestaoService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
	
}
