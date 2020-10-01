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

import br.fai.dogs.api.service.ClienteService;
import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.Pessoa;

@RestController
@RequestMapping("/api/v1/cliente")
@CrossOrigin(origins="*")
public class ClienteRestController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Cliente>> readAll(){
		
		List<Cliente> cliente = clienteService.readAll();
		
		if(cliente == null || cliente.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(cliente);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Boolean> create(@RequestBody Cliente entity){
		boolean response = clienteService.create(entity);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Pessoa> readById(@PathVariable("id") Long id){
		
		Pessoa cliente = clienteService.readById(id);
		
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(cliente);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Cliente entity){
		
		boolean response = clienteService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = clienteService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
}
