package br.fai.dogs.api.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

import br.fai.dogs.api.service.PessoaService;
import br.fai.dogs.model.entities.Pessoa;

@RestController
@RequestMapping("/api/v1/pessoa")
@CrossOrigin(origins="*")
public class PessoaRestController {
	
	@Value("${jwt.secret}")
	private String SECRET_KEY_JWT;
	
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
	public ResponseEntity<Long> create(@RequestBody Pessoa entity){
		
		Long response = pessoaService.create(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Pessoa> readById(@PathVariable("id") Long id){
		
		Pessoa pessoa = (Pessoa) pessoaService.readById(id);
		
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Pessoa entity){
		
		boolean response = pessoaService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = pessoaService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/validarLogin")
	public ResponseEntity<Pessoa> validarLogin(@RequestBody Pessoa entity, HttpServletResponse httpResponse){
		
		Pessoa pessoa = pessoaService.validarLogin(entity);

		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
		
	}
	
	@GetMapping("/read-all-profissional")
	public ResponseEntity<List<Pessoa>> readAllProfissional(){
		
		List<Pessoa> pessoa = pessoaService.readAllProfissional();
		
		if(pessoa == null || pessoa.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(pessoa);
		}
	}
	
	@GetMapping("/read-by-email")
	public ResponseEntity<Pessoa> readByEmail(@PathParam("email") String email){
		
		Pessoa pessoa = pessoaService.readByEmail(email);
		
		if(pessoa == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(pessoa);
	}
	
}
