package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.ConfiguracaoPicpayDao;
import br.fai.dogs.model.entities.ConfiguracaoPicpay;

@Repository
public class ConfiguracaoPicpayDaoImpl implements ConfiguracaoPicpayDao {

	@Override
	public ConfiguracaoPicpay readByProfissionalId(Long id) {
		
		ConfiguracaoPicpay config = new ConfiguracaoPicpay();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select * from configuracao_picpay where profissional_id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				config.setId(resultSet.getLong("id"));
				config.setAtivo(resultSet.getBoolean("ativo"));
				config.setPicpaySeller(resultSet.getString("picpay_token"));
				config.setPicpayToken(resultSet.getString("picpay_token"));
				config.setProfissionalId(resultSet.getLong("profissional_id"));
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao obter a configuração do picpay do profissional: " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return config;
		
	}

	@Override
	public List<ConfiguracaoPicpay> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(ConfiguracaoPicpay entity) {

		boolean response = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = " insert into configuracao_picpay (ativo,picpay_token,picpay_seller,profissional_id) "
				+ " values (?,?,?,?) ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setBoolean(1, entity.isAtivo());
			preparedStatement.setString(2, entity.getPicpayToken());
			preparedStatement.setString(3, entity.getPicpaySeller());
			preparedStatement.setLong(4, entity.getProfissionalId());
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao salvar a configuracao do picpay: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de criar a configuracao do picpay {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public ConfiguracaoPicpay readById(Long id) {
		
		ConfiguracaoPicpay config = new ConfiguracaoPicpay();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select * from configuracao_picpay where id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				config.setId(resultSet.getLong("id"));
				config.setAtivo(resultSet.getBoolean("ativo"));
				config.setPicpaySeller(resultSet.getString("picpay_token"));
				config.setPicpayToken(resultSet.getString("picpay_token"));
				config.setProfissionalId(resultSet.getLong("profissional_id"));
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao obter a configuração do picpay por id: " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return config;
		
	}

	@Override
	public boolean update(ConfiguracaoPicpay entity) {
		
		boolean response = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = " update configuracao_picpay set ativo = ?, picpay_token = ?, picpay_seller = ? where id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setBoolean(1, entity.isAtivo());
			preparedStatement.setString(2, entity.getPicpayToken());
			preparedStatement.setString(3, entity.getPicpaySeller());
			preparedStatement.setLong(4, entity.getId());
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao alterar a configuracao do picpay: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de alterar a configuracao do picpay {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public boolean deleteById(Long id) {
		
		boolean response = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = " delete from configuracao_picpay where id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, id);
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao deletar a configuracao do picpay: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de deletar a configuracao do picpay {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

}
