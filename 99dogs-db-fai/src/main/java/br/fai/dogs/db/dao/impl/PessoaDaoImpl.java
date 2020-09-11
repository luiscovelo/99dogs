package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.EntityInterface;
import br.fai.dogs.model.entities.Pessoa;

@Repository
public class PessoaDaoImpl implements EntityInterface<Pessoa> {

	@Override
	public List<Pessoa> readAll() {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM pessoa;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Pessoa pessoa = new Pessoa();
				pessoa.setId(resultSet.getLong("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setSenha(resultSet.getString("senha"));
				pessoa.setRua(resultSet.getString("rua"));
				pessoa.setBairro(resultSet.getString("bairro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setPais(resultSet.getString("pais"));
				pessoa.setFoto(resultSet.getString("foto"));
				pessoa.setNumero(resultSet.getInt("numero"));

				pessoas.add(pessoa);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(Pessoa entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO usuario (nome, telefone, email, senha, ";
		sql += " rua, bairro, cidade, estado, pais, foto, numero)  ";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getTelefone());
			preparedStatement.setString(3, entity.getEmail());
			preparedStatement.setString(4, entity.getSenha());
			preparedStatement.setString(5, entity.getRua());
			preparedStatement.setString(6, entity.getBairro());
			preparedStatement.setString(7, entity.getCidade());
			preparedStatement.setString(8, entity.getEstado());
			preparedStatement.setString(9, entity.getPais());
			preparedStatement.setString(10, entity.getFoto());
			preparedStatement.setInt(11, entity.getNumero());

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
	public Pessoa readById(Long id) {
		Pessoa pessoa = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM pessoa WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pessoa = new Pessoa();
				pessoa.setId(resultSet.getLong("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setSenha(resultSet.getString("senha"));
				pessoa.setRua(resultSet.getString("rua"));
				pessoa.setBairro(resultSet.getString("bairro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setPais(resultSet.getString("pais"));
				pessoa.setFoto(resultSet.getString("foto"));
				pessoa.setNumero(resultSet.getInt("numero"));
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return pessoa;
	}

	@Override
	public boolean update(Pessoa entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE pessoa SET nome = ?, telefone = ?, email = ?, rua = ?, bairro = ?, cidade = ?, estado = ?, ";
		sql += "pais = ?, numero = ?, foto = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getNome());
			preparedStatement.setString(2, entity.getTelefone());
			preparedStatement.setString(3, entity.getEmail());
			preparedStatement.setString(4, entity.getRua());
			preparedStatement.setString(5, entity.getBairro());
			preparedStatement.setString(6, entity.getCidade());
			preparedStatement.setString(7, entity.getEstado());
			preparedStatement.setString(8, entity.getPais());
			preparedStatement.setInt(9, entity.getNumero());
			preparedStatement.setString(10, entity.getFoto());
			preparedStatement.setLong(11, entity.getId());

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

		String sql = "DELETE FROM pessoa WHERE id = ?;";

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
