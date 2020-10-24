create or replace function calcula_media_avaliacao() returns trigger as $$
begin
	
	update profissional set media_avaliacao = (
		select ROUND(AVG(AV.nota)) from avaliacao AV where AV.profissional_id = NEW.profissional_id
	) where pessoa_id = NEW.profissional_id;
	
	return NEW;
	
end;
$$
language plpgsql;

create trigger tg_calcula_media_avaliacao
after insert
on avaliacao
for each row
execute function calcula_media_avaliacao();


create or replace function calcula_qtde_avaliacao() returns trigger as $$
begin
	
	update profissional set qtde_avaliacao = (
		select count(AV.id) from avaliacao AV where AV.profissional_id = NEW.profissional_id
	) where pessoa_id = NEW.profissional_id;
	
	return NEW;
	
end;
$$
language plpgsql;

create trigger tg_calcula_qtde_avaliacao
after insert
on avaliacao
for each row
execute function calcula_qtde_avaliacao();