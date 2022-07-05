package com.desafio.topico.repositories;

import com.desafio.topico.entities.Cooperado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
@Transactional
public interface CooperadoRepository extends JpaRepository<Cooperado, String> {

}
