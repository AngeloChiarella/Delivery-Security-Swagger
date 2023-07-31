package com.innovation.restaurante.configs;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Configuration;

import com.innovation.restaurante.entities.Cliente;
import com.innovation.restaurante.entities.Produto;
import com.innovation.restaurante.repositories.ClienteRepository;
import com.innovation.restaurante.repositories.ProdutoRepository;

@Configuration
@SpringBootConfiguration
public class PopulateDatabase implements CommandLineRunner {

	@Autowired
	private ClienteRepository cliente;

	@Autowired
	private ProdutoRepository produto;

	@Override
	public void run(String... args) throws Exception {

		Cliente c1 = new Cliente("Angelo",	 "R. XPTO", "email1@email.com");
		Cliente c2 = new Cliente("Carol",	 "R. XPTO", "email2@email.com");
		Cliente c3 = new Cliente("João",	 "R. XPTO", "email3@email.com");
		Cliente c4 = new Cliente("Pedro",	 "R. XPTO", "email4@email.com");
		Cliente c5 = new Cliente("André",	 "R. XPTO", "email5@email.com");
		cliente.saveAll(Arrays.asList(
				c1, c2, c3, c4, c5));

		Produto pr1 	= new Produto("Coca-Cola 600ml", 			8.50);
		Produto pr2 	= new Produto("Suco 300ml", 				9.99);
		Produto pr3 	= new Produto("Strogonof de Frango", 		33.90);
		Produto pr4 	= new Produto("Strogonof de Carne", 		35.90);
		Produto pr5 	= new Produto("Feijoada", 					35.50);
		Produto pr6 	= new Produto("Macarrão Bolonhesa", 		25.90);
		Produto pr7 	= new Produto("Queijo ralado 100g", 		1.99);
		Produto pr8		= new Produto("Pudim", 						8.99);
		Produto pr9 	= new Produto("Bolo Chocolate (Pedaco)",	15.90);
		Produto pr10 	= new Produto("Batata Frita Porção",		14.99);
		produto.saveAll(Arrays.asList(
				pr1, pr2, pr3, pr4, pr5, pr6, pr7, pr8, pr9, pr10));

	}
}
