package com.desafio.topico.services;

import com.desafio.topico.entities.Cooperado;
import com.desafio.topico.resources.dtos.CooperadoDTO;

public interface ICooperadoService {

   Cooperado create(CooperadoDTO dtoo);

   Cooperado getIfExist(String cpf);
}
