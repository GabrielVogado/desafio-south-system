package com.desafio.topico.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cooperado", schema = "public")
public class Cooperado {

	@Id
	@Column(name = "cpf")
	private String cpf;

	@Column(name = "nome")
	private String nome;


}
