package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.AvaliacaoService;
import br.fai.dogs.model.entities.Avaliacao;

@RestController
@RequestMapping("/api/v1/avaliacao")
@CrossOrigin(origins="*")
public class AvaliacaoRestController {
	
	@Autowired
	private AvaliacaoService avaliacaoService;
	
	@GetMapping("/read-by-profissional-id/{id}")
	public HttpEntity<List<Avaliacao>> readByProfissionalId(@PathVariable("id") Long id){
		
		List<Avaliacao> response = avaliacaoService.readByProfissionalId(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/create")
	public HttpEntity<Boolean> create(@RequestBody Avaliacao avaliacao){
		
		boolean response = avaliacaoService.create(avaliacao);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/rating-by-profissional-id/{id}")
	public HttpEntity<String> rating(@PathVariable("id") Long id){
		
		String response = avaliacaoService.rating(id);
		return ResponseEntity.ok(response);
		
	}
	
}
