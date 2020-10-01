package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.ClienteDao;
import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.Pessoa;

@Repository
public class ClienteDaoImpl implements ClienteDao {

	@Override
	public List<Cliente> readAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM cliente;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getLong("id"));
				cliente.setPessoaId(resultSet.getInt("pessoa_id"));
				
				clientes.add(cliente);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(Cliente entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO cliente (pessoa_id)";
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
	public Pessoa readById(Long id) {
		
		Pessoa cliente = new Pessoa();

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "select pe.* from cliente cli " + 
					"inner join pessoa pe on pe.id = cli.pessoa_id " + 
					"where cli.pessoa_id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				
				cliente.setId(resultSet.getLong("id"));
				cliente.setNome(resultSet.getString("nome"));
				cliente.setTelefone(resultSet.getString("telefone"));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setRua(resultSet.getString("rua"));
				cliente.setBairro(resultSet.getString("bairro"));
				cliente.setCidade(resultSet.getString("cidade"));
				cliente.setEstado(resultSet.getString("estado"));
				cliente.setPais(resultSet.getString("pais"));
				cliente.setNumero(resultSet.getInt("numero"));
				cliente.setFoto(resultSet.getString("foto"));
				cliente.setTipo(resultSet.getString("tipo"));

			}
			
			return cliente;
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao obter o cliente do banco de dados: " + e.getMessage());
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}

		return cliente;
	}

	@Override
	public boolean update(Cliente entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE cliente SET pessoa_id = ? WHERE id = ?;";

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

		String sql = "DELETE FROM cliente WHERE id = ?;";

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
