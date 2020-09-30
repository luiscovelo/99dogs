--Visualizar meus cachorros
select C.nome as cachorro, R.nome as raca, Po.descricao as Porte, Co.descricao as Comportamento from cachorro C
inner join cliente as Cl on Cl.id = C.cliente_id
inner join raca as R on C.raca_id = R.id
inner join porte as Po on R.porte_id = Po.id
inner join comportamento as Co on R.comportamento_id = Co.id
where(
Cl.id = {id}
)

--Visualizar minhas qualificacoes
select Q.titulo, Q.modalidade, Q.descricao from qualificacao Q
inner join profissional as Pr on Pr.id = Q.profissional_id
where(
Pr.id = {id} 
)



--Informações do passeio
select P.data, P.hora, P.status, P.valor, Pe.nome as Cliente, Pes.nome as Profissional, Ca.nome as Cachorro, Fp.tipo as pagamento from passeio P
inner join passeio_cachorro as Pc on Pc.passeio_id = P.id
inner join profissional as Prof on Prof.id = P.profissional_id
inner join cliente as Cl on Cl.id = P.cliente_id
inner join pessoa as Pe on Pe.id = Cl.pessoa_id
inner join pessoa as Pes on Pes.id = Prof.pessoa_id
inner join cachorro as Ca on Ca.cliente_id = Cl.id
inner join forma_de_pagamento as Fp on Fp.id = P.formaPagamento_id
where(
Cl.id = {id}
)

--Visualizar perfil profissional
select * from pessoa P
inner join profissional as Prof on Prof.pessoa_id = P.id
where(
P.id = {id}
)

--Visualizar perfil cliente
select * from pessoa P
inner join cliente as Cl on Cl.pessoa_id = P.id
where(
P.id = {id}
)