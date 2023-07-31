package com.innovation.restaurante.services;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.restaurante.entities.Cliente;
import com.innovation.restaurante.entities.dtos.ClienteDto;
import com.innovation.restaurante.entities.dtos.PedidoDto;
import com.innovation.restaurante.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	private ConverterMapper converter;

	@Autowired
	private ClienteRepository clienteRepository;

	public List<ClienteDto> findAll() {
		return clienteRepository.findAll()
				.stream()
				.map(converter::clienteToDto)
				.sorted(Comparator.comparing(ClienteDto::getNome))
				.collect(Collectors.toList());
	}

	public Cliente findByNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	public List<ClienteDto> findByNomeDto(String nome) {
		return clienteRepository.findByNomeDto(nome).stream().map(converter::clienteToDto).collect(Collectors.toList());
	}

	public Cliente saveOrUpdate(ClienteDto cliente) {
		return clienteRepository.save(converter.dtoToCliente(cliente));
	}

	public void deleteById(Long id) {
		clienteRepository.deleteById(id);
	}

	public Optional<Cliente> findById(Long id) {
		return clienteRepository.findById(id);
	}

}
