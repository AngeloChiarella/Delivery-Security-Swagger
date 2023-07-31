package com.innovation.restaurante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innovation.restaurante.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	Cliente findByNome(String nome);
	
	@Query("SELECT c FROM Cliente c WHERE c.nome LIKE %:nome%")
	List<Cliente> findByNomeDto(String nome);

}
