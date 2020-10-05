package br.fai.dogs.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.fai.dogs.db.dao.ConfiguracaoDaAgendaDao;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;

@Controller
@RequestMapping("/api/v1/configuracao-da-agenda")
@CrossOrigin(origins="*")
public class ConfiguracaoDaAgendaRestController {
	
	@Autowired
	private ConfiguracaoDaAgendaDao configuracaoDaAgendaDao;
	
	@PostMapping("/create")
	public HttpEntity<Boolean> create(@RequestBody ConfiguracaoDaAgenda configuracaoDaAgenda){
		
		boolean response = configuracaoDaAgendaDao.create(configuracaoDaAgenda);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-profissional-id/{id}")
	public HttpEntity<List<ConfiguracaoDaAgenda>> readByProfissionalId(@PathVariable("id") Long id){
		
		List<ConfiguracaoDaAgenda> response = configuracaoDaAgendaDao.readByProfissionalId(id);
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/read-by-id/{id}")
	public HttpEntity<ConfiguracaoDaAgenda> readById(@PathVariable("id") Long id){
		
		ConfiguracaoDaAgenda response = configuracaoDaAgendaDao.readById(id);
		return ResponseEntity.ok(response);
		
	}
	
	@PutMapping("/update")
	public HttpEntity<Boolean> update(@RequestBody ConfiguracaoDaAgenda configuracaoDaAgenda){
		
		boolean response = configuracaoDaAgendaDao.update(configuracaoDaAgenda);
		return ResponseEntity.ok(response);
		
	}
	
	@DeleteMapping("/delete/{id}")
	public HttpEntity<Boolean> update(@PathVariable("id") Long id){
		
		boolean response = configuracaoDaAgendaDao.delete(id);
		return ResponseEntity.ok(response);
		
	}
	
}
