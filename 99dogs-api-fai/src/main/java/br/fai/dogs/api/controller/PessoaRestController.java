package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.PessoaService;
import br.fai.dogs.model.entities.Pessoa;

@RestController
@RequestMapping("/api/v1/pessoa")
@CrossOrigin(origins="*")
public class PessoaRestController {

	@Autowired
	private PessoaService pessoaService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Pessoa>> readAll(){
		
		List<Pessoa> pessoa = pessoaService.readAll();
		
		if(pessoa == null || pessoa.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(pessoa);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Pessoa entity){
		boolean response = pessoaService.create(entity);
		
		return ResponseEntity.ok(response);
		
	}
}
