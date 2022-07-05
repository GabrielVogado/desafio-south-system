package com.desafio.topico.services;


import com.desafio.topico.entities.Topico;
import com.desafio.topico.resources.dtos.TopicoDTO;
import com.desafio.topico.resources.dtos.ResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface ITopicoService {

    Topico create(TopicoDTO topicoDTO);

    ResponseDTO start(Long idtopico, Date dataInicio);

    Page<Topico> findAll(Pageable page);

    Topico getIfExist(Long idtopico);

}
