package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.model.entities.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao {

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
				pessoa.setTipo(resultSet.getString("tipo"));

				pessoas.add(pessoa);

			}

		} catch (Exception e) {

		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public Long create(Pessoa entity) {
		
		Long pessoa_id = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "";
		
		sql  = "INSERT INTO pessoa (nome, telefone, email, senha, rua, bairro, cidade, estado, pais, foto, numero, tipo)  ";
		sql += " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?); ";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			
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
			preparedStatement.setString(12, entity.getTipo());
			
			preparedStatement.execute();

			connection.commit();
			
			resultSet = preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				pessoa_id = resultSet.getLong(1);
			}
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao criar a pessoa na tabela pessoa: " + e.getMessage());
			
			try {
				System.out.println("aqui nesse try");
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		if(pessoa_id != null) {
			
			sql = "insert into cliente (pessoa_id) values (?)";
			
			try {
				
				connection = ConnectionFactory.getConnection();
				connection.setAutoCommit(false);
				
				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setLong(1, pessoa_id);
				
				preparedStatement.execute();

				connection.commit();
				
			} catch (Exception e) {
				
				try {
					connection.rollback();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
				
			} finally {
				ConnectionFactory.close(preparedStatement, connection);
			}
			
		}
		
		return pessoa_id;
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
				pessoa.setTipo(resultSet.getString("tipo"));
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
		sql += "pais = ?, numero = ? WHERE id = ?;";

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
			preparedStatement.setLong(10, entity.getId());
			
			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao atualizar a pessoa {99dogs-db-fai}: ");
			e.printStackTrace();
			
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

	@Override
	public Pessoa validarLogin(Pessoa entity) {
		
		Pessoa pessoa = null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM pessoa WHERE email = ? and senha = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getEmail());
			preparedStatement.setString(2, entity.getSenha());

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				pessoa = new Pessoa();
				pessoa.setId(resultSet.getLong("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setRua(resultSet.getString("rua"));
				pessoa.setBairro(resultSet.getString("bairro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setPais(resultSet.getString("pais"));
				pessoa.setFoto(resultSet.getString("foto"));
				pessoa.setNumero(resultSet.getInt("numero"));
				pessoa.setTipo(resultSet.getString("tipo"));
			}

		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return pessoa;
		
	}

	@Override
	public List<Pessoa> readAllProfissional() {
		
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM pessoa WHERE tipo = 'PROFISSIONAL';";

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
			
			return pessoas;
			
		} catch (Exception e) {
			
			return null;
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}
		
	}

	@Override
	public Pessoa readByEmail(String email) {

		Pessoa pessoa = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM pessoa WHERE email = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, email);

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
			System.out.println("ocorreu um problema ao obter a pessoa por email: {99-db-fai} " + e.getMessage());
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return pessoa;
		
	}
}
