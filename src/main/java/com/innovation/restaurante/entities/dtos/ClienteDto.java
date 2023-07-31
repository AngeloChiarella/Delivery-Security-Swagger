package com.innovation.restaurante.entities.dtos;

import javax.validation.constraints.Email;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

	@JsonIgnore
	private Long id;
	private String nome;
	
	@Email
	private String email;
	private String endereco;


}
