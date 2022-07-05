package com.desafio.topico.services;


import com.desafio.topico.resources.dtos.ResultadoVotoDTO;
import com.desafio.topico.resources.dtos.VotoDTO;

public interface IVotoService {

    void votar(VotoDTO voto);

    ResultadoVotoDTO resultadoVoto(Long idtopico);
}
