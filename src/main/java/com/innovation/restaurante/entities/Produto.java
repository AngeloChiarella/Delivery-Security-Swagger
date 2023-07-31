package com.innovation.restaurante.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_produto")
public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private Double preco;
	private Integer quantidade;

	@ManyToMany
	private List<Pedido> pedido;

	public Produto(String nome, Double preco) {
		this.nome = nome;
		this.preco = preco;
	}

	public Produto(String nome, Double preco, List<Pedido> pedido) {
		this.nome = nome;
		this.preco = preco;
		this.pedido = pedido;
	}
}
