package com.desafio.topico.services.impls;

import com.desafio.topico.entities.Cooperado;
import com.desafio.topico.entities.Topico;
import com.desafio.topico.entities.Voto;
import com.desafio.topico.message.producer.IVotoProducer;
import com.desafio.topico.repositories.VotoRepository;
import com.desafio.topico.resources.dtos.TopicoDTO;
import com.desafio.topico.resources.dtos.ResultadoVotoDTO;
import com.desafio.topico.resources.dtos.VotoDTO;
import com.desafio.topico.services.ICooperadoService;
import com.desafio.topico.services.ITopicoService;
import com.desafio.topico.services.IVotoService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VotoService implements IVotoService {

	@Autowired
    private VotoRepository repository;
    
    @Autowired
	private ITopicoService topicoService;
    
    @Autowired
	private ICooperadoService cooperadoService;

    @Autowired
	private IVotoProducer votoProducer;

	public void votar(VotoDTO voto) {

		cooperadoService.getIfExist(voto.getCpf());

    	Topico topico = topicoService.getIfExist(voto.getIdtopico());

		validaSecaoVoto(topico.getData());

		existenteVoto(voto.getCpf());

		salvar(voto);

		votoProducer.send(voto.tranfomeJson());
    }

	@Override
	public ResultadoVotoDTO resultadoVoto(Long idTopico) {

		Topico topico = topicoService.getIfExist(idTopico);

		Long totalSim = repository.totalVotos(topico, Boolean.TRUE);
		Long totalNao = repository.totalVotos(topico, Boolean.FALSE);

		ResultadoVotoDTO resultadoVotoDTO = new ResultadoVotoDTO();

		resultadoVotoDTO.setTopico(new TopicoDTO());
		resultadoVotoDTO.getTopico().setNome(topico.getNome());
		resultadoVotoDTO.getTopico().setDescricao(topico.getDescricao());
		resultadoVotoDTO.setTotal(totalSim + totalNao);
		resultadoVotoDTO.setTotalNao(totalNao);
		resultadoVotoDTO.setTotalSim(totalSim);

		return resultadoVotoDTO;
	}

	private void salvar(VotoDTO voto) {

		Topico topico = topicoService.getIfExist(voto.getIdtopico());

		validaSecaoVoto(topico.getData());

		Cooperado cooperado = cooperadoService.getIfExist(voto.getCpf());

		repository.save(
				new Voto(null,
						validaVotoSimOuNao(voto.getVoto()),
                        cooperado,
						topico)
		);
	}

	private void validaSecaoVoto(Date data) {
		if (data==null) {
			throw new ServiceException("Votação não iniciada");
		}

		if (data.before(new Date())) {
			throw new ServiceException("Votações encerradas");
		}
	}

	private void existenteVoto(String cpf) {
		if(!repository.findByAssociadoCpf(cpf).isEmpty()){
			throw new ServiceException("Voto já foi computado");
		}
	}

	private boolean validaVotoSimOuNao(String voto) {
    	voto = voto.toLowerCase();
    	if(voto.equals("sim") || voto.equals("s")){
    		return true;
		}
		if(voto.equals("nao") || voto.equals("n") || voto.equals("não")){
			return false;
		}
		throw new ServiceException("O voto deve ser Sim(S) ou Não(N).");

	}

}