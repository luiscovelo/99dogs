package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.BaseDao;
import br.fai.dogs.model.entities.Raca;

public class RacaDaoImpl implements BaseDao<Raca>{

	@Override
	public List<Raca> readAll() {
		List<Raca> racas = new ArrayList<Raca>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM raca;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Raca raca = new Raca();
				raca.setId(resultSet.getLong("id"));
				raca.setNome(resultSet.getString("nome"));
				raca.setPorteId(resultSet.getInt("porte_id"));
				raca.setComportamentoId(resultSet.getInt("comportamento_id"));

				racas.add(raca);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public Raca readById(Long id) {
		Raca raca = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM raca WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				raca = new Raca();
				raca.setId(resultSet.getLong("id"));
				raca.setNome(resultSet.getString("nome"));
				raca.setPorteId(resultSet.getInt("porte_id"));
				raca.setComportamentoId(resultSet.getInt("comportamento_id"));
				
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return raca;
	}
	
	@Override
	public boolean create(Raca entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Raca entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	

}