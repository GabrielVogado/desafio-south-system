package com.desafio.topico.resources.dtos;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResultadoVotoDTO {

	private TopicoDTO topico;
	private Long totalNao;
	private Long totalSim;
	private Long total;
}
