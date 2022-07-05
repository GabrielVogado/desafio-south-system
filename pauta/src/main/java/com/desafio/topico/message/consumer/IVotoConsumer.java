package com.desafio.topico.message.consumer;

import org.springframework.kafka.annotation.KafkaListener;

public interface IVotoConsumer {

    @KafkaListener(topics = "${topic.voto}", groupId = "group_id")
    void consumer(String mensagemVoto);

}