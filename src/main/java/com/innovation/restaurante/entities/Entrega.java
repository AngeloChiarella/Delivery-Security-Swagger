package com.innovation.restaurante.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_entrega")
public class Entrega implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String endereco;
	private Double taxa;

	@OneToOne
	private Pedido pedido;

	public Entrega(Pedido pedido) {
		this.endereco = pedido.getCliente().getEndereco();
		this.taxa = pedido.getTotal() / 10;
		this.horaPedidoEntregue = pedido.getHoraPedidoFeito().plusHours(1);
		this.pedido = pedido;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm:ss - dd/MM", locale = "pt-BR", timezone = "America/Sao_Paulo")
	private LocalDateTime horaPedidoEntregue;

}
