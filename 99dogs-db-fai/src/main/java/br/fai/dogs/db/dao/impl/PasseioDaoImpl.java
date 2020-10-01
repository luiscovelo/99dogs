package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PasseioDao;
import br.fai.dogs.model.entities.Passeio;

@Repository
public class PasseioDaoImpl implements PasseioDao {

	@Override
	public List<Passeio> readAll() {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM passeio;";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Passeio passeio = new Passeio();
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));

				passeios.add(passeio);

			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios {99-dogs-fai}: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}
		
		return null;
		
	}

	@Override
	public Long create(Passeio entity) {
		
		Long idPasseio = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO passeio (status, datahora, valor, cliente_id, profissional_id, forma_de_pagamento_id)";
		sql += " VALUES (?, ?, ?, ?, ?, ?); ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
	        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        preparedStatement.setString(1, entity.getStatus());
	        preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getDatahora()));
	        preparedStatement.setDouble(3, entity.getValor());
	        preparedStatement.setLong(4, entity.getClienteId());
	        preparedStatement.setLong(5, entity.getProfissionalId());
	        preparedStatement.setLong(6, entity.getFormaDePagamentoId());
	        	        
			preparedStatement.execute();
			
			connection.commit();
			
			ResultSet idGerado = preparedStatement.getGeneratedKeys();
						
			if(idGerado.next()) {
				idPasseio = idGerado.getLong(1);
			}
			
		} catch (Exception e) {
			
			System.out.println("Falha ao criar o passeio {99-dogs-db-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de criar o passeio {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}

		return idPasseio;
	}

	@Override
	public Passeio readById(Long id) {
		
		Passeio passeio = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM passeio WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				
				passeio = new Passeio();
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setProfissionalId(resultSet.getLong("profissional_id"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));

			}
			
			return passeio;
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao ler o passeio {99-dogs-fai}: " + e.getMessage());
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return passeio;
	}

	@Override
	public boolean update(Passeio entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE passeio SET status = ?, data = ?, hora = ?, valor = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getStatus());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getDatahora()));
			preparedStatement.setDouble(4, entity.getValor());
			preparedStatement.setLong(5, entity.getId());

			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao atualizar o passeio {99-dogs-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Ocorreu um problema no query de atualizar o passeio {99-dogs-fai}: " + e1.getMessage());
			}
			
		} finally {
			
			ConnectionFactory.close(preparedStatement, connection);
			
		}
		
		return false;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM passeio WHERE id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao atualizar o passeio {99-dogs-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Ocorreu no query do atualizar passeio {99-dogs-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return false;
		
	}
	
	@Override
	public List<Passeio> passeiosPorCliente(Long cliente_id) {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM passeio where cliente_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, cliente_id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Passeio passeio = new Passeio();
				
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setProfissionalId(resultSet.getLong("profissional_id"));

				passeios.add(passeio);

			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por cliente: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
		
	}

	@Override
	public List<Passeio> passeiosPorProfissional(Long profissional_id) {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM passeio where profissional_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, profissional_id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Passeio passeio = new Passeio();
				
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setProfissionalId(resultSet.getLong("profissional_id"));

				passeios.add(passeio);

			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por profissional: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
		
	}
	
}
