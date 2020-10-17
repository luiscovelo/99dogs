package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.AvaliacaoDao;
import br.fai.dogs.model.entities.Avaliacao;
import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.Pessoa;

@Repository
public class AvaliacaoDaoImpl implements AvaliacaoDao {

	@Override
	public List<Avaliacao> readByProfissionalId(Long id) {
		
		List<Avaliacao> avaliacoes = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = " select " + 
					"	AV.*, " + 
					"	CLIENTE.nome as cliente_nome, " + 
					"	CLIENTE.foto as cliente_foto " + 
					"from avaliacao AV " + 
					"inner join pessoa CLIENTE on CLIENTE.id = AV.cliente_id " + 
					"inner join pessoa PROFISSIONAL on PROFISSIONAL.id = AV.profissional_id " + 
					"where AV.profissional_id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				Avaliacao avaliacao = new Avaliacao();
				Cliente cliente = new Cliente();
				Pessoa pCliente = new Pessoa();
				
				avaliacao.setCreated(resultSet.getTimestamp("created"));
				avaliacao.setNota(resultSet.getInt("nota"));
				avaliacao.setObservacao(resultSet.getString("observacao"));
				avaliacao.setClienteId(resultSet.getLong("cliente_id"));
				avaliacao.setProfissionalId(resultSet.getLong("profissional_id"));
				
				pCliente.setNome(resultSet.getString("cliente_nome"));
				pCliente.setFoto(resultSet.getBytes("cliente_foto"));
				
				cliente.setPessoa(pCliente);
				
				avaliacao.setCliente(cliente);
				
				avaliacoes.add(avaliacao);

			}

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao requisitar as avaliações {99dogs-db-fai}: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return avaliacoes;
		
	}

	@Override
	public List<Avaliacao> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Avaliacao entity) {
		
		boolean response = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO avaliacao (created, nota, observacao, profissional_id, cliente_id)";
		sql += " VALUES (?,?,?,?,?); ";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setInt(2, entity.getNota());
			preparedStatement.setString(3, entity.getObservacao());
			preparedStatement.setLong(4, entity.getProfissionalId());
			preparedStatement.setLong(5, entity.getClienteId());
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
			
			try {
				System.out.println("Problema ao conectar ou preparar o sql de create avaliação: " + e.getMessage());
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Problema no sql do create avaliação" + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public Avaliacao readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Avaliacao entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String rating(Long id) {
		
		String rating = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = " select ROUND(AVG(nota)) as rating from avaliacao where profissional_id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				rating = resultSet.getString("rating");

			}

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao buscar o rating do profissional {99dogs-db-fai}: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return rating;
		
	}
	
}
