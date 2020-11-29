package br.fai.dogs.api.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.fai.dogs.api.service.PasseioService;
import br.fai.dogs.model.entities.Localizacao;
import br.fai.dogs.model.entities.Passeio;

@RestController
@RequestMapping("/api/v1/passeio")
@CrossOrigin(origins="*")
public class PasseioRestController {

	@Autowired
	private PasseioService passeioService;
	
	@GetMapping("/read-all")
	public ResponseEntity<List<Passeio>> readAll(){
		
		List<Passeio> passeio = passeioService.readAll();
		
		if(passeio == null || passeio.size()==0) {
			return ResponseEntity.ok(null);
			
		}else {
			return ResponseEntity.ok(passeio);
		}
	}
	
	@PostMapping("/create")
	public ResponseEntity<Long> create(@RequestBody Passeio entity){
		
		Long response = passeioService.create(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public ResponseEntity<Passeio> readById(@PathVariable("id") Long id){
		
		Passeio passeio = passeioService.readById(id);
		
		if(passeio == null) {
			return ResponseEntity.notFound().build();
		}
		
		return ResponseEntity.ok(passeio);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Boolean> update(@RequestBody Passeio entity){
		
		boolean response = passeioService.update(entity);
		
		return ResponseEntity.ok(response);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Boolean> delete(@PathVariable("id") Long id){
		
		boolean response = passeioService.deleteById(id);
		
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("/passeiosPorCliente/{id}")
	public HttpEntity<List<Passeio>> passeiosPorCliente(@PathVariable("id") Long id){
		
		List<Passeio> response = passeioService.passeiosPorCliente(id);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/passeiosPorProfissional/{id}")
	public HttpEntity<List<Passeio>> passeiosPorProfissional(@PathVariable("id") Long id){
		
		List<Passeio> response = passeioService.passeiosPorProfissional(id);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/verificar-disponibilidade/{datahora}/{id}")
	public HttpEntity<Boolean> verificarDisponibilidade(@PathVariable("datahora") String datahora, @PathVariable("id") Long id){
				
		boolean response = passeioService.verificarDisponibilidade(datahora, id);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/alterar-status")
	public HttpEntity<Boolean> alterarStatus(@RequestBody Passeio entity){
		
		boolean response = passeioService.alterarStatus(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-all-by-profissional-id-and-status/{id}/{status}")
	public HttpEntity<List<Passeio>> readAllByProfissionalIdAndStatus(@PathVariable("id") Long id, @PathVariable("status") String status){
		
		List<Passeio> response = passeioService.readAllByProfissionalIdAndStatus(id,status);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/localizacao/create")
	public ResponseEntity<Boolean> createLocalization(@RequestBody Localizacao entity){
		
		boolean response = passeioService.createLocalization(entity);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/localizacao/{id}")
	public ResponseEntity<Map<Double, Double>> localizacao(@PathVariable("id") Long id){
		
		Map<Double, Double> response = passeioService.localizacao(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PostMapping("/localizacao/create/{id}")
	public ResponseEntity<Boolean> createObjLocalization(@RequestBody Map<Double, Double> localizacoes, @PathVariable("id") Long id){
		
		boolean response = passeioService.createLocalizationObj(id,localizacoes);
		return ResponseEntity.ok(response);
		
	}
	
}
