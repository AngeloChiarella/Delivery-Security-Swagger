package com.innovation.restaurante.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.restaurante.entities.Produto;
import com.innovation.restaurante.entities.dtos.ClienteDto;
import com.innovation.restaurante.entities.dtos.ProdutoDto;
import com.innovation.restaurante.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ConverterMapper converter;

	public List<ProdutoDto> findAll() {
		return produtoRepository.findAll()
				.stream().map(converter::produtoToDto)
				.sorted(Comparator.comparing(ProdutoDto::getNome))
				.collect(Collectors.toList());
	}

	public List<ProdutoDto> findByNome(String nome) {
		return produtoRepository.findByNome(nome)
				.stream().map(converter::produtoToDto)
				.sorted(Comparator.comparing(ProdutoDto::getNome))
				.collect(Collectors.toList());
	}

	public void saveOrUpdate(ProdutoDto dto) {
		produtoRepository.save(converter.dtoToProduto(dto));
	}

	public void deleteById(Long id) {

	}

	public Optional<Produto> findById(Long id) {
		return produtoRepository.findById(id);
	}

}
