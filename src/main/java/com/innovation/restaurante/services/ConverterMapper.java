package com.innovation.restaurante.services;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.restaurante.entities.Cliente;
import com.innovation.restaurante.entities.Pedido;
import com.innovation.restaurante.entities.Produto;
import com.innovation.restaurante.entities.dtos.ClienteDto;
import com.innovation.restaurante.entities.dtos.PedidoDto;
import com.innovation.restaurante.entities.dtos.ProdutoDto;
import com.innovation.restaurante.repositories.ProdutoRepository;

@Service
public class ConverterMapper {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper mapper;

//	*********************	PEDIDO
	public Pedido dtoToPedido(PedidoDto dto) {
		return mapper.map(dto, Pedido.class);
	}

	public PedidoDto pedidoToDto(Optional<Pedido> pedido) {
		PedidoDto dto = new PedidoDto();
		List<String> produtosToString = produtosToString(pedido.get().getProdutos());
		DateTimeFormatter formatoHoraMinuto = DateTimeFormatter.ofPattern("HH:mm");

		dto.setCliente(pedido.get().getCliente().getNome());
		dto.setEndereco(pedido.get().getEntrega().getEndereco());
		dto.setTotal(pedido.get().getTotal());
		dto.setTotalComEntrega(pedido.get().getTotal() + 7);
		dto.setProdutos(produtosToString);
		dto.setHoraPedidoFeito(pedido.get().getHoraPedidoFeito().format(formatoHoraMinuto));
		dto.setHoraPedidoEntregue(pedido.get().getEntrega().getHoraPedidoEntregue().format(formatoHoraMinuto));
		return dto;
	}

	public PedidoDto pedidoToDto(Pedido pedido) {
		PedidoDto dto = new PedidoDto();
		List<String> produtosToString = produtosToString(pedido.getProdutos());
		DateTimeFormatter formatoHoraMinuto = DateTimeFormatter.ofPattern("HH:mm");

		dto.setCliente(pedido.getCliente().getNome());
		dto.setEndereco(pedido.getEntrega().getEndereco());
		dto.setTotal(pedido.getTotal());
		dto.setTotalComEntrega(pedido.getTotal() + 7);
		dto.setProdutos(produtosToString);
		dto.setHoraPedidoFeito(pedido.getHoraPedidoFeito().format(formatoHoraMinuto));
		dto.setHoraPedidoEntregue(pedido.getEntrega().getHoraPedidoEntregue().format(formatoHoraMinuto));
		return dto;
	}

//	*********************	PRODUTO
	public List<String> produtosToString(List<Produto> produtos) {
		List<String> list = new ArrayList<>();
		for (Produto produto : produtos) {
			list.add(produto.getNome());
		}
		return list;
	}

	public List<Produto> stringToProduto(List<String> list) {
		List<Produto> produtos = new ArrayList<>();
		for (String produto : list) {
			produtos.addAll(produtoRepository.findByNome(produto));
		}
		return produtos;
	}

	public ProdutoDto produtoToDto(Produto produto) {
		return mapper.map(produto, ProdutoDto.class);
	}

	public Produto dtoToProduto(ProdutoDto dto) {
		return mapper.map(dto, Produto.class);
	}

//	*********************	CLIENTE

	public Cliente dtoToCliente(ClienteDto dto) {
		return mapper.map(dto, Cliente.class);
	}

	public ClienteDto clienteToDto(Cliente cliente) {
		return mapper.map(cliente, ClienteDto.class);
	}

}
