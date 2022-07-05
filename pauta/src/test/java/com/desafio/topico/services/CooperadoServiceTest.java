package com.desafio.topico.services;


import com.desafio.topico.entities.Cooperado;
import com.desafio.topico.repositories.CooperadoRepository;
import com.desafio.topico.resources.dtos.CooperadoDTO;
import com.desafio.topico.services.impls.CooperadoService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
public class CooperadoServiceTest {

	@MockBean
	private CooperadoRepository cooperadoRepository;

	@Autowired
	private ICooperadoService cooperadoService;

	@MockBean
	private ICPFService cpfService;

	@Rule
	public ExpectedException expectedException = ExpectedException.none();

	@TestConfiguration
	static class AssociadoServiceTestConfiguration{
		@Bean
		public CooperadoService service(){
			return new CooperadoService();
		}
	}


	@Test
	@DisplayName("Cria uma Topico")
	public void createTest(){
		CooperadoDTO dto = new CooperadoDTO();

		dto.setCpf("64326269006");
		dto.setNome("Fulano");

		Optional<Cooperado> cooperado = Optional.of(Mockito.mock(Cooperado.class));

		Mockito.when(cooperadoRepository.save(ArgumentMatchers.any(Cooperado.class))).thenReturn(cooperado.get());

		Cooperado cooperadoService = this.cooperadoService.create(dto);

		Assertions.assertNotNull(cooperadoService);
		Assertions.assertNotNull(cooperado.get());

		Assertions.assertEquals(cooperadoService, cooperado.get());
	}

	@Test
	@DisplayName("Busca cooperado por id")
	public void findByIdTest() {

		Optional<Cooperado> cooperado = Optional.of(Mockito.mock(Cooperado.class));

		Mockito.when(cooperadoRepository.findById(ArgumentMatchers.eq("64326269006"))).thenReturn(cooperado);

		Cooperado cooperadoService = this.cooperadoService.getIfExist("64326269006");

		Assertions.assertNotNull(cooperadoService);
		Assertions.assertNotNull(cooperado);
		Assertions.assertEquals(cooperadoService, cooperado.get());
	}

	@Test
	@DisplayName("Id não econtrado")
	public void findByIdErroTest() {

		Mockito.when(cooperadoRepository.findById(ArgumentMatchers.eq("64326269006"))).thenReturn(Optional.empty());

		ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> cooperadoService.getIfExist("64326269006"));

		Assertions.assertEquals("Cooperado não cadastrado, favor cadastrar associado", exception.getMessage());
	}


	@Test
	@DisplayName("Cooperado já cadastrado")
	public void createErroTest() {
		CooperadoDTO dto = new CooperadoDTO();

		dto.setCpf("64326269006");
		dto.setNome("Fulano");

		Optional<Cooperado> cooperado = Optional.of(Mockito.mock(Cooperado.class));

		Mockito.when(cooperadoRepository.findById(dto.getCpf())).thenReturn(cooperado);

		ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> cooperadoService.create(dto));

		Assertions.assertEquals("CPF " + dto.getCpf() + " já cadastrado na base" , exception.getMessage());
	}

}
