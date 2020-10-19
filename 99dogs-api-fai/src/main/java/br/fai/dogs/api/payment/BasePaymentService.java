package br.fai.dogs.api.payment;

public interface BasePaymentService<ModelRequest, ModelResponse> {
	
	ModelResponse create(ModelRequest entity);
	
}
