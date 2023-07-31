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

import com.innovation.restaurante.entities.Produto;
import com.innovation.restaurante.entities.dtos.ProdutoDto;
import com.innovation.restaurante.services.ProdutoService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/produto")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public List<ProdutoDto> findAll() {
		return produtoService.findAll();
	}

	@GetMapping("/id/{id}")
	public ResponseEntity<Optional<Produto>> findById(@PathVariable Long id) {
		return new ResponseEntity<Optional<Produto>>(produtoService.findById(id), HttpStatus.OK);
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<ProdutoDto>> findByNome(@PathVariable String nome) {
		return new ResponseEntity<List<ProdutoDto>>(produtoService.findByNome(nome), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody ProdutoDto dto) {
		produtoService.saveOrUpdate(dto);
		return new ResponseEntity<String>("Criado com sucesso!", HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> update(@RequestBody ProdutoDto dto, @PathVariable Long id) {
		if (findById(id).getBody().isPresent()) {
			dto.setId(id);
			produtoService.saveOrUpdate(dto);
			return new ResponseEntity<String>("Alterado com sucesso!", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Não encontrado!", HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		produtoService.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
