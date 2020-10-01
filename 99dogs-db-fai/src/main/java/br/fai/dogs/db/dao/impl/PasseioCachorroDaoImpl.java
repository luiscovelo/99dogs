package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PasseioCachorroDao;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.PasseioCachorro;

@Repository
public class PasseioCachorroDaoImpl implements PasseioCachorroDao {

	@Override
	public List<PasseioCachorro> readAll() {
		List<PasseioCachorro> passeiosCachorros = new ArrayList<PasseioCachorro>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM passeio_cachorro;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				PasseioCachorro passeioCachorro = new PasseioCachorro();
				passeioCachorro.setId(resultSet.getLong("id"));
				passeioCachorro.setPasseioId(resultSet.getInt("passeio_id"));
				passeioCachorro.setCachorroId(resultSet.getInt("cachorro_id"));

				passeiosCachorros.add(passeioCachorro);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(PasseioCachorro entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO passeio_cachorro (passeio_id, cachorro_id)";
		sql += " VALUES (?, ?); ";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setInt(1, entity.getPasseioId());
			preparedStatement.setInt(2, entity.getCachorroId());

			preparedStatement.execute();

			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao criar o passeio-cachorro em {99dogs-db-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}

		return false;
		
	}

	@Override
	public PasseioCachorro readById(Long id) {
		PasseioCachorro passeioCachorro = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM passeio_cachorro WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				passeioCachorro = new PasseioCachorro();
				passeioCachorro.setId(resultSet.getLong("id"));
				passeioCachorro.setPasseioId(resultSet.getInt("passeio_id"));
				passeioCachorro.setCachorroId(resultSet.getInt("cachorro_id"));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return passeioCachorro;
	}

	@Override
	public boolean update(PasseioCachorro entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE passeio_cachorro SET passeio_id = ?, cachorro_id = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, entity.getPasseioId());
			preparedStatement.setInt(2, entity.getCachorroId());
			preparedStatement.setLong(3, entity.getId());
			

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
	public List<Cachorro> readByPasseioId(Long id) {
		
		List<Cachorro> cachorros = new ArrayList<Cachorro>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "select ca.* from passeio_cachorro pc " + 
					"inner join cachorro ca on ca.id = pc.cachorro_id " + 
					"where pc.passeio_id = ? " + 
					"group by ca.id order by ca.nome asc";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cachorro cachorro = new Cachorro();
				
				cachorro.setId(resultSet.getLong("id"));
				cachorro.setNome(resultSet.getString("nome"));
				cachorro.setDataNascimento(resultSet.getDate("data_nascimento"));
				cachorro.setRacaId(resultSet.getLong("raca_id"));

				cachorros.add(cachorro);

			}
			
			return cachorros;
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao buscar os cachorros do passeio: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return cachorros;
		
	}

	
}
