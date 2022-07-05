package com.desafio.topico.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "topico", schema = "public")
public class Topico implements Serializable {

	public Topico(Long id){
		this.id = id;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "sq_topico", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "sq_topico", sequenceName = "sq_topico", allocationSize = 1)
	private Long id;

	@Column(name = "nome")
	private String nome;

	@Column(name = "descricao")
	private String descricao;

	@Column(name = "dataInicio")
	private Date data;


}
