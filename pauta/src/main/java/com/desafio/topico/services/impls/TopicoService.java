package com.desafio.topico.services.impls;


import com.desafio.topico.entities.Topico;
import com.desafio.topico.repositories.TopicoRepository;
import com.desafio.topico.resources.dtos.TopicoDTO;
import com.desafio.topico.resources.dtos.ResponseDTO;
import com.desafio.topico.services.ITopicoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;

import static com.desafio.topico.utils.TopicoUtils.validaData;

@Service
public class TopicoService implements ITopicoService {

	@Autowired
	private TopicoRepository repository;

	@Override
	public Topico create(TopicoDTO topicoDTO) {
		Topico topico = Topico.builder()
				.nome(topicoDTO.getNome())
				.descricao(topicoDTO.getDescricao())
				.build();
		return repository.save(topico);
	}

	@Override
	public ResponseDTO start(Long idtopico, Date dataInicio) {

		Topico topico = getIfExist(idtopico);

		topico.setData(validaData(dataInicio));

		repository.save(topico);

		return new ResponseDTO().responseCreatetopico(topico.getNome(), topico.getData());
	}

	@Override
	public Page<Topico> findAll(Pageable page) {

		return repository.findAll(page);
	}


	public Topico getIfExist(Long idtopico){
		return repository.findById(idtopico)
				.orElseThrow(()-> new ServiceException("Não existe uma topico com o ID " + idtopico));

	}

}
