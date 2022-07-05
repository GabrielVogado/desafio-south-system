package com.desafio.topico.services.impls;

import com.desafio.topico.entities.Cooperado;
import com.desafio.topico.repositories.CooperadoRepository;
import com.desafio.topico.resources.dtos.CooperadoDTO;
import com.desafio.topico.services.ICooperadoService;
import com.desafio.topico.services.ICPFService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CooperadoService implements ICooperadoService {
	
	@Autowired
	private CooperadoRepository cooperadoRepository;

	@Autowired
	private ICPFService cpfService;

	@Override
	public Cooperado create(CooperadoDTO cooperadoDTO) {

        Optional<Cooperado> cooperado = cooperadoRepository.findById(cooperadoDTO.getCpf());

		if(cooperado.isEmpty()){
			cpfService.validar(cooperadoDTO.getCpf());
		    return cooperadoRepository.save(new Cooperado(cooperadoDTO.getCpf(), cooperadoDTO.getNome()));
        }
		throw new ServiceException("CPF " + cooperadoDTO.getCpf() + " já cadastrado na base" );
	}

	@Override
	public Cooperado getIfExist(String cpf) {
		return cooperadoRepository.findById(cpf)
				.orElseThrow(()-> new ServiceException("Cooperado não cadastrado, favor cadastrar associado"));
	}


}
