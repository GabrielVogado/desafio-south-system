package com.desafio.topico.resources.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private String response;

    public ResponseDTO responseCreatetopico(String nome, Date data){
        return new ResponseDTO("A topico " + nome +
                " foi aberta com sucesso, " +
                "a votação ficará aberta até " + data);
    }

}
