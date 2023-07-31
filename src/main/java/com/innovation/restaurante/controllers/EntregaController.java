package com.innovation.restaurante.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.innovation.restaurante.entities.Entrega;
import com.innovation.restaurante.services.EntregaService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/entrega")
public class EntregaController {

	@Autowired
	private EntregaService entregaService;

	@GetMapping
	public ResponseEntity<List<Entrega>> findAll() {
		List<Entrega> list = entregaService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<Entrega>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Entrega>>(list, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<Entrega>> findById(@PathVariable Long id) {
		Optional<Entrega> cliente = entregaService.findById(id);
		if (cliente.isPresent())
			return new ResponseEntity<Optional<Entrega>>(cliente, HttpStatus.OK);
		return new ResponseEntity<Optional<Entrega>>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{endereco}")
	public ResponseEntity<List<Entrega>> findByNome(@PathVariable String nome) {
		List<Entrega> list = entregaService.findByEndereco(nome);
		if (list.isEmpty())
			return new ResponseEntity<List<Entrega>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<Entrega>>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Entrega cliente) {
		entregaService.save(cliente);
		return new ResponseEntity<String>("Criado com sucesso!", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody Entrega entrega, @PathVariable Long id) {
		if (findById(id).getBody().isPresent())
			entrega.setId(id);
		entregaService.save(entrega);
		return new ResponseEntity<String>("Alterado com sucesso!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		entregaService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
