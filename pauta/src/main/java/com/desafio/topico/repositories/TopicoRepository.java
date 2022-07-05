package com.desafio.topico.repositories;

import com.desafio.topico.entities.Topico;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface TopicoRepository extends PagingAndSortingRepository<Topico, Long> {

}
