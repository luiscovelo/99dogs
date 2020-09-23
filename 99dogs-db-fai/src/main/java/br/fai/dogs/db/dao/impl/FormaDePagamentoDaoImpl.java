package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.FormaDePagamento;

public class FormaDePagamentoDaoImpl implements BaseDao<FormaDePagamento>{

	@Override
	public List<FormaDePagamento> readAll() {
		List<FormaDePagamento> formasDePagamento = new ArrayList<FormaDePagamento>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM forma_de_pagamento;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				FormaDePagamento formaDePagamento = new FormaDePagamento();
				formaDePagamento.setId(resultSet.getLong("id"));
				formaDePagamento.setTipo(resultSet.getString("tipo"));
				

				formasDePagamento.add(formaDePagamento);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public FormaDePagamento readById(Long id) {
		FormaDePagamento formaDePagamento = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM forma_de_pagamento WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				formaDePagamento = new FormaDePagamento();
				formaDePagamento.setId(resultSet.getLong("id"));
				formaDePagamento.setTipo(resultSet.getString("tipo"));
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return formaDePagamento;
	}
	
	@Override
	public boolean create(FormaDePagamento entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(FormaDePagamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	
}
