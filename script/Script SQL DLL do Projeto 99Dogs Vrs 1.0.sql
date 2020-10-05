drop database if exists db_99dogs;
create database db_99dogs with encoding 'utf8';

ALTER DATABASE db_99dogs OWNER TO postgres;

\connect db_99dogs;

create table if not exists comportamento(
	id serial primary key,
	descricao character varying(50) not null check(descricao in('Agressivo', 'Medroso', 'Agitado', 'Calmo'))
);

create table if not exists porte(
	id serial primary key,
	descricao character varying(50) not null check(descricao in('Grande', 'Medio', 'Pequeno'))
);

create table if not exists raca(
	id serial primary key,
	nome character varying(50) not null,
	comportamento_id integer not null references comportamento(id) on update cascade,
	porte_id integer not null references porte(id) on update cascade
);

create table if not exists pessoa(
	id serial primary key,
	nome character varying(100) not null,
	telefone character varying(15) not null,
	email character varying(100) not null unique,
	senha text not null,
	rua character varying(100) not null,
	bairro character varying(100) not null,
	cidade character varying(100) not null,
	estado character varying(100) not null,
	pais character varying(100) not null,
	numero integer not null,
	foto character varying(100) not null,
	tipo character varying(50) not null check(tipo in('ADMIN', 'PROFISSIONAL', 'CLIENTE'))
);

create table if not exists cliente(
	id serial unique,
	pessoa_id integer references pessoa(id) on update cascade primary key
);

create table if not exists profissional(
	id serial unique,
	pessoa_id integer references pessoa(id) on update cascade primary key
);

create table if not exists cachorro(
	id serial primary key,
	nome character varying(50) not null,
	data_nascimento date,
	raca_id integer not null references raca(id) on update cascade,
	cliente_id integer not null references cliente(pessoa_id) on update cascade
);

create table if not exists qualificacao(
	id serial primary key,
	titulo character varying(50) not null,
	modalidade character varying(50) not null check(modalidade in('Graduacao', 'Tecnico', 'Pos-Graduacao', 'Mestrado')),
	descricao character varying(100),
	profissional_id integer not null references profissional(pessoa_id) on update cascade
);

create table if not exists forma_de_pagamento(
	id serial primary key,
	tipo character varying(30) not null check(tipo in('Boleto', 'Transferencia', 'PicPay', 'Cartao de Credito', 'Cartao de Debito'))	
);

create table if not exists reclamacao_sugestao(
	id serial primary key,
	nome character varying(100) not null,
	email character varying(100) not null,
	assunto character varying(30) not null check(assunto in('Profissional', 'Cliente', 'Aplicacao', 'Falhas')),
	mensagem text not null,
	pessoa_id integer references pessoa(id) on update cascade
);

create table if not exists passeio(
	id serial primary key,
	datahora timestamp without time zone not null,
	status character varying(30) not null check(status in('Recusado', 'Aprovado', 'Finalizado', 'Espera')),
	valor double precision default 0 not null,
	profissional_id integer not null references profissional(pessoa_id) on update cascade,
	cliente_id integer not null references cliente(pessoa_id) on update cascade,
	forma_de_pagamento_id integer not null references forma_de_pagamento(id) on update cascade
);

create table if not exists passeio_cachorro(
	passeio_id integer references passeio(id) on update cascade,
	cachorro_id integer references cachorro(id) on update cascade,
	primary key(passeio_id, cachorro_id)
);

create table configuracao_agenda(
	id serial primary key,
	dia_semana integer not null,
	hora_inicio time without time zone not null,
	hora_final time without time zone not null,
	tempo_de_passeio integer default 1 not null,
	valor_passeio double precision default 0 not null,
	profissional_id integer not null references profissional(pessoa_id) on update cascade
);