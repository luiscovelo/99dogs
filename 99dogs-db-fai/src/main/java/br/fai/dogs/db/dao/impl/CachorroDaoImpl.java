package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.EntityInterface;
import br.fai.dogs.model.entities.Cachorro;

public class CachorroDaoImpl implements EntityInterface<Cachorro> {

	@Override
	public List<Cachorro> readAll() {
		List<Cachorro> cachorros = new ArrayList<Cachorro>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM cachorro;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cachorro cachorro = new Cachorro();
				cachorro.setId(resultSet.getLong("id"));
				cachorro.setNome(resultSet.getString("nome"));
				cachorro.setData_nascimento(resultSet.getDate("dataNascimento"));
				cachorro.setRaca_id(resultSet.getInt("racaId"));
				cachorro.setCliente_id(resultSet.getInt("clienteId"));

				cachorros.add(cachorro);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(Cachorro entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO cachorro (nome, dataNascimento, racaId, clienteId ";
		sql += " VALUES (?, ?, ?, ?); ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setDate(2, entity.getData_nascimento());
			preparedStatement.setInt(3, entity.getRaca_id());
			preparedStatement.setInt(4, entity.getCliente_id());

			preparedStatement.execute();

			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				return false;
			}
		} finally {
			ConnectionFactory.close(null, preparedStatement, connection);
		}

		return false;
	}

	@Override
	public Cachorro readById(Long id) {
		Cachorro cachorro = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM cachorro WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				cachorro = new Cachorro();
				cachorro.setId(resultSet.getLong("id"));
				cachorro.setNome(resultSet.getString("nome"));
				cachorro.setData_nascimento(resultSet.getDate("dataNascimento"));
				cachorro.setRaca_id(resultSet.getInt("racaId"));
				cachorro.setCliente_id(resultSet.getInt("clienteId"));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return cachorro;
	}

	@Override
	public boolean update(Cachorro entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE cachorro SET nome = ?, dataNascimento = ?, racaId = ?, clienteId = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setDate(2, entity.getData_nascimento());
			preparedStatement.setInt(3, entity.getRaca_id());
			preparedStatement.setInt(4, entity.getCliente_id());
			preparedStatement.setLong(5, entity.getId());

			preparedStatement.execute();
			connection.commit();
			return true;

		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

	@Override
	public boolean deleteById(Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM cachorro WHERE id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();
			return true;

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
	}

}
