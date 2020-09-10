package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.model.entities.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao{

	@Override
	public List<Pessoa> readAll() {
			
		List<Pessoa> pessoa = new ArrayList<Pessoa>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
	try {
			
			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM pessoa;";
			
			preparedStatement = connection.prepareStatement(sql);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				Pessoa pes = new Pessoa();
				pes.setId(resultSet.getLong("id"));
				pes.setNome(resultSet.getString("nome"));
				pes.setTelefone(resultSet.getString("telefone"));
				pes.setEmail(resultSet.getString("email"));
				pes.setSenha(resultSet.getString("senha"));
				pes.setRua(resultSet.getString("rua"));
				pes.setBairro(resultSet.getString("bairro"));
				pes.setCidade(resultSet.getString("cidade"));
				pes.setEstado(resultSet.getString("estado"));
				pes.setPais(resultSet.getString("pais"));
				pes.setFoto(resultSet.getString("foto"));
				pes.setNumero(resultSet.getInt("numero"));
				
				pessoa.add(pes);
				
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
		}finally {
			ConnectionFactory.close(null, preparedStatement, connection);
		}
		
		return false;
	}

}
