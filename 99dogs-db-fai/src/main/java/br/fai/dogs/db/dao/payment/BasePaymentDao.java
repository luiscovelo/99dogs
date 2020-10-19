package br.fai.dogs.db.dao.payment;

public interface BasePaymentDao<T> {
	
	boolean create(T entity);
	
}
