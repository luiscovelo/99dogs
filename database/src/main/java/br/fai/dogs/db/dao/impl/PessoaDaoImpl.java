package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PessoaDao;
import br.fai.dogs.model.entities.Pessoa;

@Repository
public class PessoaDaoImpl implements PessoaDao{

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

}
