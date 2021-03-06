package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.QualificacaoDao;
import br.fai.dogs.model.entities.Qualificacao;

@Repository
public class QualificacaoDaoImpl implements QualificacaoDao {

	@Override
	public List<Qualificacao> readAll() {
		List<Qualificacao> qualificacoes = new ArrayList<Qualificacao>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM qualificacao;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Qualificacao qualificacao = new Qualificacao();
				qualificacao.setId(resultSet.getLong("id"));
				qualificacao.setTitulo(resultSet.getString("titulo"));
				qualificacao.setModalidade(resultSet.getString("modalidade"));
				qualificacao.setDescricao(resultSet.getString("descricao"));
				qualificacao.setProfissionalId(resultSet.getLong("profissional_id"));

				qualificacoes.add(qualificacao);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(Qualificacao entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO qualificacao (titulo, modalidade, descricao, profissional_id) ";
		sql += " VALUES (?, ?, ?, ?); ";
				
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, entity.getTitulo());
			preparedStatement.setString(2, entity.getModalidade());
			preparedStatement.setString(3, entity.getDescricao());
			preparedStatement.setLong(4, entity.getProfissionalId());
			
			preparedStatement.execute();

			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
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
	public Qualificacao readById(Long id) {
		
		Qualificacao qualificacao = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM qualificacao WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				qualificacao = new Qualificacao();
				qualificacao.setId(resultSet.getLong("id"));
				qualificacao.setTitulo(resultSet.getString("titulo"));
				qualificacao.setModalidade(resultSet.getString("modalidade"));
				qualificacao.setDescricao(resultSet.getString("descricao"));
				qualificacao.setProfissionalId(resultSet.getLong("profissional_id"));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return qualificacao;
	}

	@Override
	public boolean update(Qualificacao entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE qualificacao SET titulo = ?, modalidade = ?, descricao = ?, profissional_id = ? WHERE id = ?;";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setString(1, entity.getTitulo());
			preparedStatement.setString(2, entity.getModalidade());
			preparedStatement.setString(3, entity.getDescricao());
			preparedStatement.setLong(4, entity.getProfissionalId());
			preparedStatement.setLong(5, entity.getId());

			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
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
	public boolean deleteById(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM qualificacao WHERE id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
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
	public List<Qualificacao> readByProfissionalId(Long id) {
		
		List<Qualificacao> qualificacoes = new ArrayList<Qualificacao>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM qualificacao where profissional_id = ?;";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Qualificacao qualificacao = new Qualificacao();
				
				qualificacao.setId(resultSet.getLong("id"));
				qualificacao.setTitulo(resultSet.getString("titulo"));
				qualificacao.setModalidade(resultSet.getString("modalidade"));
				qualificacao.setDescricao(resultSet.getString("descricao"));
				qualificacao.setProfissionalId(resultSet.getLong("profissional_id"));
				
				qualificacoes.add(qualificacao);

			}
						
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return qualificacoes;
		
	}

}
