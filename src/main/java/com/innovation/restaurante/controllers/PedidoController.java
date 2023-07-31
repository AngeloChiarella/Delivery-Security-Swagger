package com.innovation.restaurante.controllers;

import java.util.List;

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

import com.innovation.restaurante.entities.dtos.PedidoDto;
import com.innovation.restaurante.services.PedidoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/pedido")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@GetMapping
	public ResponseEntity<List<PedidoDto>> findAll() {
		List<PedidoDto> list = pedidoService.findAll();
		if (list.isEmpty())
			return new ResponseEntity<List<PedidoDto>>(HttpStatus.NO_CONTENT);

		return new ResponseEntity<List<PedidoDto>>(list, HttpStatus.OK);
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<PedidoDto> findById(@PathVariable Long id) {
		PedidoDto pedido = pedidoService.findByIdDto(id);
		if (pedido.getCliente() != null)
			return new ResponseEntity<PedidoDto>(pedido, HttpStatus.OK);
		return new ResponseEntity<PedidoDto>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<PedidoDto>> findByNome(@PathVariable String nome) {
		List<PedidoDto> list = pedidoService.findByNome(nome);
		if (list.isEmpty())
			return new ResponseEntity<List<PedidoDto>>(HttpStatus.NOT_FOUND);

		return new ResponseEntity<List<PedidoDto>>(list, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody PedidoDto dto) {
		pedidoService.saveOrUpdate(dto);
		return new ResponseEntity<String>("Feito com Sucesso!", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody PedidoDto dto, @PathVariable Long id) {
		if (pedidoService.findById(id).isPresent())
			dto.setId(id);
		pedidoService.saveOrUpdate(dto);
		return new ResponseEntity<String>("Alterado com Sucesso!", HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteById(@PathVariable Long id) {
			pedidoService.deleteById(id);
			return new ResponseEntity<String>("Deletado com Sucesso!", HttpStatus.OK);
	}
}
