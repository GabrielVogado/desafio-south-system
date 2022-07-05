package com.desafio.topico.utils;

import org.hibernate.service.spi.ServiceException;

import java.util.Calendar;
import java.util.Date;

public class TopicoUtils {


    public static Date validaData(Date data) {

        if (data == null) {
            return adicionaTempoPadrao();
        }

        if (data.before(new Date())) {
            throw new ServiceException("Data inválida, informe uma data futura, para iniciar uma votação");
        }

        return data;
    }

    private static Date adicionaTempoPadrao() {
        Calendar novaData = Calendar.getInstance();
        novaData.add(Calendar.MINUTE, 1);
        return novaData.getTime();
    }
}
