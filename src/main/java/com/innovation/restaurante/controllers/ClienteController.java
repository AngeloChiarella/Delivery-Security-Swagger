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

import com.innovation.restaurante.entities.Cliente;
import com.innovation.restaurante.entities.dtos.ClienteDto;
import com.innovation.restaurante.services.ClienteService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/cliente")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<List<ClienteDto>> findAll() {
		List<ClienteDto> list = clienteService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<ClienteDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ClienteDto>>(list, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<Cliente>> findById(@PathVariable Long id) {
		Optional<Cliente> cliente = clienteService.findById(id);
		if (cliente.isPresent())
			return new ResponseEntity<Optional<Cliente>>(cliente, HttpStatus.OK);
		return new ResponseEntity<Optional<Cliente>>(HttpStatus.NOT_FOUND);

	}

	@GetMapping("/{nome}")
	public ResponseEntity<List<ClienteDto>> findByNome(@PathVariable String nome) {
		List<ClienteDto> list = clienteService.findByNomeDto(nome);
		if (list.isEmpty())
			return new ResponseEntity<List<ClienteDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<ClienteDto>>(list, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody ClienteDto cliente) {
		clienteService.saveOrUpdate(cliente);
		return new ResponseEntity<String>("Criado com sucesso!", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody ClienteDto dto, @PathVariable Long id) {
		if (findById(id).getBody().isPresent())
			dto.setId(id);
		clienteService.saveOrUpdate(dto);
		return new ResponseEntity<String>("Alterado com sucesso!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		clienteService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
