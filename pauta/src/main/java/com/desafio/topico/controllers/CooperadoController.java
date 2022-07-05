package com.desafio.topico.controllers;

import com.desafio.topico.entities.Cooperado;
import com.desafio.topico.resources.dtos.CooperadoDTO;
import com.desafio.topico.services.ICooperadoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cooperado")
public class CooperadoController {

    @Autowired
    private ICooperadoService cooperadoService;

    @PostMapping("/v1")
    @ApiOperation(value = "Serviço responsável cadastrar um cooperado")
    @ApiResponses({ @ApiResponse(code = 201, message = "Created", response = Cooperado.class)})
    public ResponseEntity create(@Valid @RequestBody CooperadoDTO dto){
        return ResponseEntity.status(HttpStatus.CREATED).body(cooperadoService.create(dto));
    }
}
