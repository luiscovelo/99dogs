package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Profissional;

public class ProfissionalDaoImpl implements BaseDao<Profissional>{

	@Override
	public List<Profissional> readAll() {
		List<Profissional> profissionais = new ArrayList<Profissional>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM profissional;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Profissional profissional = new Profissional();
				profissional.setId(resultSet.getLong("id"));
				profissional.setPessoaId(resultSet.getInt("pessoa_id"));
				

				profissionais.add(profissional);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(Profissional entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO profissional (pessoa_id)";
		sql += " VALUES (?); ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement.setInt(1, entity.getPessoaId());
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
	public Profissional readById(Long id) {
		Profissional profissional = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM profissional WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				profissional = new Profissional();
				profissional.setId(resultSet.getLong("id"));
				profissional.setPessoaId(resultSet.getInt("pessoa_id"));
				

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return profissional;
	}

	@Override
	public boolean update(Profissional entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE profissional SET pessoa_id = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, entity.getPessoaId());
			preparedStatement.setLong(2, entity.getId());

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

		String sql = "DELETE FROM profissional WHERE id = ?;";

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
