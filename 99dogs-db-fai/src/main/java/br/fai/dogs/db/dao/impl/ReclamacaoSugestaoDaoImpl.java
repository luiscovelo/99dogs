package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.ReclamacaoSugestaoDao;
import br.fai.dogs.model.entities.ReclamacaoSugestao;

@Repository
public class ReclamacaoSugestaoDaoImpl implements ReclamacaoSugestaoDao {

	@Override
	public List<ReclamacaoSugestao> readAll() {
		List<ReclamacaoSugestao> reclamacoesSugestoes = new ArrayList<ReclamacaoSugestao>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM reclamacao_sugestao;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				ReclamacaoSugestao reclamacaoSugestao = new ReclamacaoSugestao();
				reclamacaoSugestao.setId(resultSet.getLong("id"));
				reclamacaoSugestao.setNome(resultSet.getString("nome"));
				reclamacaoSugestao.setEmail(resultSet.getString("email"));
				reclamacaoSugestao.setAssunto(resultSet.getString("assunto"));
				reclamacaoSugestao.setMensagem(resultSet.getString("mensagem"));

				reclamacoesSugestoes.add(reclamacaoSugestao);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(ReclamacaoSugestao entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO reclamacao_sugestao (nome, email, assunto, mensagem)";
		sql += " VALUES (?, ?, ?, ?); ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setString(3, entity.getAssunto());
			preparedStatement.setString(4, entity.getMensagem());

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
	public ReclamacaoSugestao readById(Long id) {
		ReclamacaoSugestao reclamacaoSugestao = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM reclamacao_sugestao WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				reclamacaoSugestao = new ReclamacaoSugestao();
				reclamacaoSugestao.setId(resultSet.getLong("id"));
				reclamacaoSugestao.setNome(resultSet.getString("nome"));
				reclamacaoSugestao.setEmail(resultSet.getString("email"));
				reclamacaoSugestao.setAssunto(resultSet.getString("assunto"));
				reclamacaoSugestao.setMensagem(resultSet.getString("mensagem"));

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return reclamacaoSugestao;
	}

	@Override
	public boolean update(ReclamacaoSugestao entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE reclamacao_sugestao SET nome = ?, email = ?, assunto = ?, mensagem = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getEmail());
			preparedStatement.setString(3, entity.getAssunto());
			preparedStatement.setString(4, entity.getMensagem());
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

		String sql = "DELETE FROM reclamacao_sugestao WHERE id = ?;";

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
