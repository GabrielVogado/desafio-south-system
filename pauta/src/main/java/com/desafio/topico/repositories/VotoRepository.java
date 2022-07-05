package com.desafio.topico.repositories;

import com.desafio.topico.entities.Topico;
import com.desafio.topico.entities.Voto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Long> {

	Optional<Voto> findByAssociadoCpf(String cpf);

	@Query("select count (voto) from Voto where topico = ?1 and voto = ?2")
	Long totalVotos(Topico topico, boolean voto);

}
