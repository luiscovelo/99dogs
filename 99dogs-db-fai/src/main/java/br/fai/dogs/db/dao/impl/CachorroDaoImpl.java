package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.CachorroDao;
import br.fai.dogs.model.entities.Cachorro;
import br.fai.dogs.model.entities.Raca;

@Repository
public class CachorroDaoImpl implements CachorroDao {

	@Override
	public List<Cachorro> readAll() {
		
		List<Cachorro> cachorros = new ArrayList<Cachorro>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = " select " + 
					"	C.*, " + 
					"	R.id as raca_id, " + 
					"	R.nome as raca_nome, " + 
					"	R.comportamento_id as raca_comportamento_id, " + 
					"	R.porte_id as raca_porte_id " + 
					"from cachorro C " + 
					"inner join raca R on R.id = C.raca_id ";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cachorro cachorro = new Cachorro();
				Raca raca = new Raca();
				
				cachorro.setId(resultSet.getLong("id"));
				cachorro.setNome(resultSet.getString("nome"));
				cachorro.setDataNascimento(resultSet.getDate("data_nascimento"));
				cachorro.setRacaId(resultSet.getLong("raca_id"));
				cachorro.setClienteId(resultSet.getLong("cliente_id"));
				
				raca.setId(resultSet.getLong("raca_id"));
				raca.setNome(resultSet.getString("raca_nome"));
				raca.setComportamentoId(resultSet.getInt("raca_comportamento_id"));
				raca.setPorteId(resultSet.getInt("raca_porte_id"));
				
				cachorro.setRaca(raca);
				
				cachorros.add(cachorro);

			}

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao requisitar os cachorros {99dogs-db-fai}: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return cachorros;
	}

	@Override
	public boolean create(Cachorro entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO cachorro (id, nome, data_nascimento, raca_id, cliente_id)";
		sql += " VALUES (default,?, ?, ?, ?); ";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setDate(2, entity.getDataNascimento());
			preparedStatement.setLong(3, entity.getRacaId());
			preparedStatement.setLong(4, entity.getClienteId());
						
			preparedStatement.execute();
			
			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			
			System.out.println("caiu aqui" + e.getMessage());
			
			try {
				System.out.println("Problema ao conectar ou preparar o sql de create cachorro: " + e.getMessage());
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Problema no sql do create cachorro" + e1.getMessage());
			}
			
			return false;
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}

	}

	@Override
	public Cachorro readById(Long id) {
		
		Cachorro cachorro = new Cachorro();
		Raca raca = new Raca();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = " select " + 
					"	C.*, " + 
					"	R.id as raca_id, " + 
					"	R.nome as raca_nome, " + 
					"	R.comportamento_id as raca_comportamento_id, " + 
					"	R.porte_id as raca_porte_id " + 
					"from cachorro C " + 
					"inner join raca R on R.id = C.raca_id "
					+ " where C.id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				
				cachorro.setId(resultSet.getLong("id"));
				cachorro.setNome(resultSet.getString("nome"));
				cachorro.setDataNascimento(resultSet.getDate("data_nascimento"));
				cachorro.setRacaId(resultSet.getLong("raca_id"));
				cachorro.setClienteId(resultSet.getLong("cliente_id"));
				
				raca.setId(resultSet.getLong("raca_id"));
				raca.setNome(resultSet.getString("raca_nome"));
				raca.setComportamentoId(resultSet.getInt("raca_comportamento_id"));
				raca.setPorteId(resultSet.getInt("raca_porte_id"));
				
				cachorro.setRaca(raca);
				
			}

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao requisitar o cachorro por id {99dogs-db-fai}: " + e.getMessage());
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return cachorro;
	}

	@Override
	public boolean update(Cachorro entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE cachorro SET nome = ?, data_nascimento = ?, raca_id = ? WHERE id = ?;";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setDate(2, entity.getDataNascimento());
			preparedStatement.setLong(3, entity.getRacaId());
			preparedStatement.setLong(4, entity.getId());

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

	@Override
	public Map<String, Object> cachorrosPorCliente(Long cliente_id) {
		
		Map<String, Object> responseRequest = new HashMap<>();
		List<Cachorro> cachorros = new ArrayList<Cachorro>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = " select " + 
					"	C.*, " + 
					"	R.id as raca_id, " + 
					"	R.nome as raca_nome, " + 
					"	R.comportamento_id as raca_comportamento_id, " + 
					"	R.porte_id as raca_porte_id " + 
					"from cachorro C " + 
					"inner join raca R on R.id = C.raca_id "
					+ " where C.cliente_id = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, cliente_id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
								
				Cachorro cachorro = new Cachorro();
				Raca raca = new Raca();
				
				cachorro.setId(resultSet.getLong("id"));
				cachorro.setNome(resultSet.getString("nome"));
				cachorro.setDataNascimento(resultSet.getDate("data_nascimento"));
				cachorro.setRacaId(resultSet.getLong("raca_id"));
				
				raca.setId(resultSet.getLong("raca_id"));
				raca.setNome(resultSet.getString("raca_nome"));
				raca.setComportamentoId(resultSet.getInt("raca_comportamento_id"));
				raca.setPorteId(resultSet.getInt("raca_porte_id"));
				
				cachorro.setRaca(raca);
				
				cachorros.add(cachorro);

			}
			
			responseRequest.put("hasError", false);
			responseRequest.put("response", cachorros);
			
		} catch (SQLException e) {
			
			responseRequest.put("hasError", true);
			responseRequest.put("message", "Ocorreu um problema ao executar a busca no banco" + e.getMessage());
						
		} catch (Exception e) {
		
			responseRequest.put("hasError", true);
			responseRequest.put("message", "Falha ao obter lista de cachorros por cliente: " + e.getMessage());
			
		}finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}
		
		return responseRequest;
		
	}

}
