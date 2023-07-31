package com.innovation.restaurante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innovation.restaurante.entities.Entrega;

public interface EntregaRepository extends JpaRepository<Entrega, Long> {

	@Query("SELECT e FROM Entrega e WHERE e.endereco LIKE %:endereco%")
	List<Entrega> findByEndereco(String endereco);

}
