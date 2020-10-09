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

import br.fai.dogs.api.service.RecebimentoService;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Recebimento;

@RestController
@RequestMapping("/api/v1/recebimento")
@CrossOrigin(origins="*")
public class RecebimentoRestController {
	
	@Autowired
	private RecebimentoService recebimentoService;
	
	@GetMapping("/read-all-by-profissional-id/{id}")
	public HttpEntity<List<Recebimento>> readAllByProfissionalId(@PathVariable("id") Long id){
		
		List<Recebimento> response = recebimentoService.readAllByProfissionalId(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/create")
	public HttpEntity<Boolean> create(@RequestBody Recebimento entity){
		
		boolean response = recebimentoService.create(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-passeios-sem-recebimento-por-profissional/{id}")
	public HttpEntity<List<Passeio>> readPasseiosSemRecebimentoPorProfissional(@PathVariable("id") Long id){
		
		List<Passeio> response = recebimentoService.readPasseiosSemRecebimentoPorProfissional(id);
		return ResponseEntity.ok(response);
 		
	}
	
}
