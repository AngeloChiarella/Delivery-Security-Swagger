package com.innovation.restaurante.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.innovation.restaurante.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

	@Query("SELECT p FROM Pedido p WHERE p.cliente.nome LIKE %:nome%")
	List<Pedido> findByNome(String nome);


}
