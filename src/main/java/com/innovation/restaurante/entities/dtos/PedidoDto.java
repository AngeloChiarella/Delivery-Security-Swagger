package com.innovation.restaurante.entities.dtos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PedidoDto {

	@JsonIgnore
	private Long id;
	private String cliente;
	private List<String> produtos;
	private String endereco;
	private Double total;
	private Double entrega = 7.0;
	private Double totalComEntrega;
	private String horaPedidoFeito;
	private String horaPedidoEntregue;

}
