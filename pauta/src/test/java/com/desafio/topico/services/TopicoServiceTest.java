package com.desafio.topico.services;


import com.desafio.topico.entities.Topico;
import com.desafio.topico.repositories.TopicoRepository;
import com.desafio.topico.resources.dtos.TopicoDTO;
import com.desafio.topico.resources.dtos.ResponseDTO;
import com.desafio.topico.services.impls.TopicoService;
import org.hibernate.service.spi.ServiceException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
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
public class TopicoServiceTest {

	@MockBean
	private TopicoRepository repository;

	@Autowired
	private ITopicoService service;

	@TestConfiguration
	static class PautaServiceTestConfiguration{
		@Bean
		public TopicoService pautaService(){
			return new TopicoService();
		}
	}


	@Test
	@DisplayName("Cria uma Topico")
	public void createTest(){
		TopicoDTO dto = new TopicoDTO("Teste cria topico", "teses unitários");

		Topico topico = Mockito.mock(Topico.class);

		Mockito.when(repository.save(ArgumentMatchers.any(Topico.class))).thenReturn(topico);

		Topico topicoService = service.create(dto);

		Assertions.assertNotNull(topico);
		Assertions.assertNotNull(topicoService);

		Assertions.assertEquals(topicoService, topico);
	}

	@Test
	@DisplayName("Da inicio a uma pauta")
	public void startTest() {

		Optional<Topico> pauta = Optional.ofNullable(Mockito.mock(Topico.class));

		Mockito.when(repository.findById(ArgumentMatchers.eq(1L))).thenReturn(pauta);

		ResponseDTO responseDTO = service.start(1L, null);

		Assertions.assertNotNull(pauta);
		Assertions.assertNotNull(responseDTO);

	}

	@Test
	@DisplayName("Busca pauta por id")
	public void findByIdTest() {

		Optional<Topico> pauta = Optional.ofNullable(Mockito.mock(Topico.class));

		Mockito.when(repository.findById(ArgumentMatchers.eq(1L))).thenReturn(pauta);

		Topico topicoService = service.getIfExist(1L);

		Assertions.assertNotNull(pauta);
		Assertions.assertNotNull(topicoService);

		Assertions.assertEquals(topicoService, pauta.get());

	}

	@Test
	@DisplayName("Busca pauta por id trazendo a exception")
	public void findByIdErroTest() {

		Mockito.when(repository.findById(ArgumentMatchers.eq(1L))).thenReturn(Optional.empty());

		ServiceException exception = Assertions.assertThrows(ServiceException.class, () -> service.getIfExist(1L));

		Assertions.assertEquals("Não existe uma pauta com o ID " + 1L, exception.getMessage());

	}

}
