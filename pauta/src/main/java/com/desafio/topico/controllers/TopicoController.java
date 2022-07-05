package com.desafio.topico.controllers;

import com.desafio.topico.entities.Topico;
import com.desafio.topico.resources.dtos.InicioDTO;
import com.desafio.topico.resources.dtos.TopicoDTO;
import com.desafio.topico.resources.dtos.ResponseDTO;
import com.desafio.topico.services.ITopicoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/topico")
public class TopicoController {

	@Autowired
	private ITopicoService topicoService;

	@PostMapping("/v1")
	@ApiOperation(value = "Serviço responsável por criar uma topico")
	@ApiResponses({ @ApiResponse(code = 201, message = "Created", response = Topico.class)})
	public ResponseEntity create(@Valid @RequestBody TopicoDTO dto){
		return ResponseEntity.status(HttpStatus.CREATED).body(topicoService.create(dto));
	}

	@PutMapping({"/v1/inicia/{idtopico}"})
	@ApiOperation(value = "Serviço responsável por dar início a votação da topico")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = ResponseDTO.class)})
	public ResponseEntity start(@PathVariable Long idtopico ,@RequestBody InicioDTO dto ){
		return ResponseEntity.status(HttpStatus.OK).body(topicoService.start(idtopico, dto.getDataInicio()));
	}

	@GetMapping("/v1")
	@ApiOperation(value = "Serviço responsável por busca paginada de todas as topicos")
	@ApiResponses({ @ApiResponse(code = 200, message = "OK", response = Page.class)})
	public ResponseEntity findAll(@PageableDefault Pageable page){
		return ResponseEntity.status(HttpStatus.OK).body(topicoService.findAll(page));
	}
}
