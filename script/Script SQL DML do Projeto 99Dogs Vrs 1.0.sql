-- Ínicio da Transação 
begin;

-- Inserção de Comportamento
insert into comportamento(id,descricao) values (1,'Agressivo');
insert into comportamento(id,descricao) values (2,'Medroso');
insert into comportamento(id,descricao) values (3,'Agitado');
insert into comportamento(id,descricao) values (4,'Calmo');

-- Inserção do porte
insert into porte(id,descricao) values(1,'Grande');
insert into porte(id,descricao) values(2,'Medio');
insert into porte(id,descricao) values(3,'Pequeno');

-- Inserção de raca
insert into raca(id,nome, comportamento_id, porte_id) values (1,'Pastor Alemão',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (2,'Border Collie',3,2);
insert into raca(id,nome, comportamento_id, porte_id) values (3,'Pinscher',2,3);
insert into raca(id,nome, comportamento_id, porte_id) values (4,'Poodle',3,2);
insert into raca(id,nome, comportamento_id, porte_id) values (5,'Labrador',3,1);
insert into raca(id,nome, comportamento_id, porte_id) values (6,'Golden Retriever',3,1);
insert into raca(id,nome, comportamento_id, porte_id) values (7,'Yorkshire',2,3);
insert into raca(id,nome, comportamento_id, porte_id) values (8,'Shih Tzu',4,3);
insert into raca(id,nome, comportamento_id, porte_id) values (9,'Pug',4,3);
insert into raca(id,nome, comportamento_id, porte_id) values (10,'Bulldog',4,2);
insert into raca(id,nome, comportamento_id, porte_id) values (11,'Rottweiler',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (12,'Pit Bull',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (13,'Salsicha',3,3);
insert into raca(id,nome, comportamento_id, porte_id) values (14,'Basset',4,3);
insert into raca(id,nome, comportamento_id, porte_id) values (15,'Schnauzer',3,2);
insert into raca(id,nome, comportamento_id, porte_id) values (16,'Beagle',3,2);
insert into raca(id,nome, comportamento_id, porte_id) values (17,'Fila Brasileiro',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (18,'Husky Siberiano',3,1);
insert into raca(id,nome, comportamento_id, porte_id) values (19,'Akita',3,1);
insert into raca(id,nome, comportamento_id, porte_id) values (20,'Vira-Lata',2,2);
insert into raca(id,nome, comportamento_id, porte_id) values (21,'Boxer',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (22,'São Bernardo',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (23,'Dalmata',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (24,'Doberman',1,1);
insert into raca(id,nome, comportamento_id, porte_id) values (25,'Bernese Mountain',3,1);
insert into raca(id,nome, comportamento_id, porte_id) values (26,'Chow Chow',1,2);
insert into raca(id,nome, comportamento_id, porte_id) values (27,'Cocker',4,2);
insert into raca(id,nome, comportamento_id, porte_id) values (28,'Bull Terrier',1,2);
insert into raca(id,nome, comportamento_id, porte_id) values (29,'Chihuahua',3,3);
insert into raca(id,nome, comportamento_id, porte_id) values (30,'Jack Russell',3,3);
insert into raca(id,nome, comportamento_id, porte_id) values (31,'Boiadeiro Australiano',1,2);
insert into raca(id,nome, comportamento_id, porte_id) values (32,'Outro',4,2);


-- Inserção Pessoa
insert into pessoa(id,nome, telefone, email, senha, rua, bairro, cidade, estado, numero, pais, foto, tipo) 
	values(1,'Gabriel', '998939914', 'gabriel@gmail.com','123','Rua Do Amaral','Casqueta','Pedralva','MG',153,'Brasil','https://img.icons8.com/cotton/2x/dog--v1.png','CLIENTE');
insert into pessoa(id,nome, telefone, email, senha, rua, bairro, cidade, estado, numero, pais, foto, tipo) 
	values(2,'Luis Felipe', '998939912', 'cliente@hotmail.com','123','Rua Rodolfo Brusamolin','Anchieta','Santa Rita do Sapucai','MG',55,'Brasil','https://img.icons8.com/cotton/2x/dog--v1.png','CLIENTE');
insert into pessoa(id,nome, telefone, email, senha, rua, bairro, cidade, estado, numero, pais, foto, tipo) 
	values(3,'Marco Antonio', '998939913', 'marco@gmail.com','123','Rua da Vila','Novo Horizonte','Santa Rita do Sapucai','MG',77,'Brasil','https://img.icons8.com/cotton/2x/dog--v1.png','CLIENTE');
insert into pessoa(id,nome, telefone, email, senha, rua, bairro, cidade, estado, numero, pais, foto, tipo) 
	values(4,'DogWalker', '998939913', 'dogwalker@gmail.com','123','Rua das Alfazemas','Nova Cidade','Santa Rita do Sapucai','MG',25,'Brasil','https://img.icons8.com/cotton/2x/dog--v1.png','PROFISSIONAL');

-- Inserção Cliente 
insert into cliente(id,pessoa_id) values(1,1);
insert into cliente(id,pessoa_id) values(2,2);
insert into cliente(id,pessoa_id) values(3,3);

-- Inserção Profissional
insert into profissional(id,pessoa_id) values(1,4);

-- Inserção Cachorro
insert into cachorro(id,nome, data_nascimento, raca_id, cliente_id) values(1,'Thor','2018-02-15',1,1);
insert into cachorro(id,nome, data_nascimento, raca_id, cliente_id) values(2,'Luke','2018-03-15',2,2);
insert into cachorro(id,nome, data_nascimento, raca_id, cliente_id) values(3,'Leléo','2018-04-15',3,3);

-- Inserção Qualificacao
insert into qualificacao(id,titulo, modalidade, descricao, profissional_id) values(1,'DogWalker','Graduacao','Experiência em passeio de com caes de porte grande',4);

-- Inserção forma de pagamento
insert into forma_de_pagamento(id,tipo) values(1,'Boleto');
insert into forma_de_pagamento(id,tipo) values(2,'Transferencia');
insert into forma_de_pagamento(id,tipo) values(3,'PicPay');
insert into forma_de_pagamento(id,tipo) values(4,'Cartao de Credito');
insert into forma_de_pagamento(id,tipo) values(5,'Cartao de Debito');

-- Inserção de Reclamacao e Sugestao
insert into reclamacao_sugestao(id,nome,email,assunto,mensagem,pessoa_id) values(1,'Guilherme','guilherme@hotmail.com','Profissional','Sou um DogWalker nas horas vagas e gostaria de cadastrar-me na plataforma.',null);
insert into reclamacao_sugestao(id,nome,email,assunto,mensagem,pessoa_id) values(2,'Joyce','joyce@outlook.com','Cliente','Gostei muito da plataforma.',null);
insert into reclamacao_sugestao(id,nome,email,assunto,mensagem,pessoa_id) values(3,'José','jose@yahoo.com.br','Aplicacao','A aplicação encontra-se fora do ar deste sábado.',null);
insert into reclamacao_sugestao(id,nome,email,assunto,mensagem,pessoa_id) values(4,'Otávio','otavio@hotmail.com','Falhas','Estou com problemas para finalizar o pagamento utilizando o PicPay, o erro ao processar: <QRCode não está válido para a sessão ativa.>',null);

-- Inserção do Passeio 
insert into passeio(id,datahora,status,valor,profissional_id, cliente_id, forma_de_pagamento_id) values(1,'2020-06-15 09:30','Finalizado',25.00,4,3,2);
insert into passeio(id,datahora,status,valor,profissional_id, cliente_id, forma_de_pagamento_id) values(2,'2020-06-16 14:30','Espera',25.00,4,3,3);
insert into passeio(id,datahora,status,valor,profissional_id, cliente_id, forma_de_pagamento_id) values(3,'2020-06-16 15:30','Espera',25.00,4,2,3);
insert into passeio(id,datahora,status,valor,profissional_id, cliente_id, forma_de_pagamento_id) values(4,'2020-06-17 13:00','Espera',30.00,4,1,4);
insert into passeio(id,datahora,status,valor,profissional_id, cliente_id, forma_de_pagamento_id) values(5,'2020-06-18 17:30','Espera',15.00,4,3,4);

-- Inserção Passeio do cachorro
insert into passeio_cachorro(passeio_id, cachorro_id) values(1,3);
insert into passeio_cachorro(passeio_id, cachorro_id) values(2,3);
insert into passeio_cachorro(passeio_id, cachorro_id) values(3,2);
insert into passeio_cachorro(passeio_id, cachorro_id) values(4,1);
insert into passeio_cachorro(passeio_id, cachorro_id) values(5,3);

alter sequence comportamento_id_seq restart with 5;
alter sequence porte_id_seq restart with 4;
alter sequence raca_id_seq restart with 33;
alter sequence pessoa_id_seq restart with 5;
alter sequence cliente_id_seq restart with 4;
alter sequence profissional_id_seq restart with 2;
alter sequence cachorro_id_seq restart with 4;
alter sequence qualificacao_id_seq restart with 2;
alter sequence forma_de_pagamento_id_seq restart with 6;
alter sequence reclamacao_sugestao_id_seq restart with 5;
alter sequence passeio_id_seq restart with 6;

commit;