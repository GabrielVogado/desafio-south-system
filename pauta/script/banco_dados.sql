CREATE SEQUENCE sq_voto start with 1 increment by 1 NO MINVALUE NO MAXVALUE CACHE 1;
create table voto(
  id 		 bigint not null DEFAULT nextval('sq_voto'::regclass) constraint voto_pkey primary key,
  voto  	 boolean,
  topico  	 bigint constraint topico_id references topico(id),
  cooperado  varchar constraint cooperado_id references cooperado(cpf)
);

create table cooperado(
  cpf 	varchar(11)  not null constraint cpf_pkey primary key,
  nome  varchar(80)
);

CREATE SEQUENCE sq_topico start with 1 increment by 1 NO MINVALUE NO MAXVALUE CACHE 1;
create table topico(
  id 			bigint not null DEFAULT  nextval('sq_topico'::regclass) constraint avaliacao_pkey primary key,
  nome 			varchar(80),
  descricao     varchar(200),
  dataInicio	timestamp
);
