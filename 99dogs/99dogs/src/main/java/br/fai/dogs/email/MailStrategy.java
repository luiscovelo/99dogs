package br.fai.dogs.email;

import br.fai.dogs.model.entities.Passeio;

public interface MailStrategy {
	
	void passeioSolicitado(Passeio entity);
	void passeioAprovado(Passeio entity);
	void passeioRecusado(Passeio entity);
	void passeioFinalizado(Passeio entity);
	
}
