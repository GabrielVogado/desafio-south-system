# Desafio-southsystem
Desafio South System - Sistema de criação de votação de topicos

## Passo a Passo para rodar a API 
  ### Kafka
   * Baixar e rodar o Kafka
   * Criar topico com o nome de VOTO_TOPICO
   
  ### Banco de dados
  * Baixar e instalar o banco de dados postgres
  * Criar um usuario e senha para o seu banco no processo de instalação
  * Mudar a senha e usuário no application.yml para o usuário e senha que foi criado no processo de isntalação do banco (Mudar: linha 16 campo username e linha 17 campo password)
  * Criar um databese chamado agenda
  * Rodar o conteúdo do arquivo **banco_dados.sql**

  ## Lombok 
   * Será necessário instalar o Jar do Lombok dependendo da sua IDE

## Tecnologias Utilizadas para esse Sistema
 * Spring boot
 * Spring Data JPA
 * Junit ( Para teste unitários )
 * Lombok
 * PostgreSQL
 * Swagger ( para documentação de API -- Ao rodar o sistema acesse o link http://localhost:8080/swagger-ui.html#/ para ter acesso o Swagger )
 * Kafka
 * Java 11
