package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Comportamento;

public class ComportamentoDaoImpl implements BaseDao<Comportamento>{

	@Override
	public List<Comportamento> readAll() {
		List<Comportamento> comportamentos = new ArrayList<Comportamento>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM comportamento;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Comportamento comportamento = new Comportamento();
				comportamento.setId(resultSet.getLong("id"));
				comportamento.setDescricao(resultSet.getString("descricao"));

				comportamentos.add(comportamento);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}


	@Override
	public Comportamento readById(Long id) {
		Comportamento comportamento = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM comportamento WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				comportamento = new Comportamento();
				comportamento.setId(resultSet.getLong("id"));
				comportamento.setDescricao(resultSet.getString("descricao"));
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return comportamento;
	}

	@Override
	public boolean create(Comportamento entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean update(Comportamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
