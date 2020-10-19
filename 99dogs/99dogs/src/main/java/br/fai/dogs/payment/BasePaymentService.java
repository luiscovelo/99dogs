package br.fai.dogs.payment;

public interface BasePaymentService<ModelRequest, ModelResponse> {
	
	ModelResponse create(ModelRequest entity);
	
}
