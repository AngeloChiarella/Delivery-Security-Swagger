package com.innovation.restaurante.services;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.innovation.restaurante.entities.Cliente;
import com.innovation.restaurante.entities.Entrega;
import com.innovation.restaurante.entities.Pedido;
import com.innovation.restaurante.entities.Produto;
import com.innovation.restaurante.entities.dtos.PedidoDto;
import com.innovation.restaurante.repositories.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private EntregaService entregaService;

	@Autowired
	private ConverterMapper converter;

	@Autowired
	private ClienteService cliente;

	public List<PedidoDto> findAll() {
		return pedidoRepository.findAll()
				.stream()
				.map(converter::pedidoToDto)
				.sorted(Comparator.comparing(PedidoDto::getHoraPedidoFeito))
				.collect(Collectors.toList());
	}

	public Optional<Pedido> findById(Long id) {
		return  pedidoRepository.findById(id);
	}

	public PedidoDto findByIdDto(Long id) {
		Optional<Pedido> findById = pedidoRepository.findById(id);
		return  converter.pedidoToDto(findById);
	}

	public List<PedidoDto> findByNome(String nome) {
		return pedidoRepository.findByNome(nome)
				.stream().map(converter::pedidoToDto)
				.sorted(Comparator.comparing(PedidoDto::getCliente))
				.collect(Collectors.toList());
	}

	public void saveOrUpdate(PedidoDto dto) {
		Cliente newCliente = cliente.findByNome(dto.getCliente());
		List<Produto> mostrarProdutos = converter.stringToProduto(dto.getProdutos());
		Pedido pedido = new Pedido(newCliente, mostrarProdutos);
		Entrega entrega = new Entrega(pedido);

		if (dto.getId() != null) {
			if (pedido.getEntrega().getHoraPedidoEntregue().isAfter(LocalDateTime.now()))
				pedido.setId(dto.getId());
		}
		
		pedidoRepository.save(pedido);
		entregaService.save(entrega);
	}

	
	public void deleteById(Long id) {
		pedidoRepository.deleteById(id);
	}
	
	public Float calculaEntrega(Double pedido) {
		Double taxa = pedido * 0.1;
		return taxa.floatValue() ;
	}

}
