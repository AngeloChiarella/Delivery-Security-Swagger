package com.innovation.restaurante.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.restaurante.entities.Entrega;
import com.innovation.restaurante.repositories.EntregaRepository;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository repository;
	
	public void save(Entrega entrega) {
		repository.save(entrega);
	}

	public List<Entrega> findAll() {
		return repository.findAll();
	}

	public Optional<Entrega> findById(Long id) {
		return repository.findById(id);
	}

	public List<Entrega> findByEndereco(String endereco) {
		return repository.findByEndereco(endereco);
	}

	public void deleteById(Long id) {
		repository.deleteById(id);		
	}
	
}
