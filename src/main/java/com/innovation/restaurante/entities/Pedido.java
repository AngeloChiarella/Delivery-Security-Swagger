package com.innovation.restaurante.entities;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_pedido")
public class Pedido implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToMany(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
	private List<Produto> produtos;

	private Double total;
	private Double totalComEntrega;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss - dd/MM", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private LocalDateTime horaPedidoFeito;
	
	@JsonIgnore
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Entrega entrega;

	@JsonIgnore
	@ManyToOne
	private Cliente cliente;

	public Pedido(Cliente cliente, List<Produto> produtos) {
		this.cliente = cliente;
		this.produtos = produtos;
		this.horaPedidoFeito = LocalDateTime.now();
		this.total = somarPedido(produtos);
	}

	public double somarPedido(List<Produto> produtos) {
	    return produtos.stream().mapToDouble(Produto::getPreco).sum();
	}

}
