package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.ProfissionalDao;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;

@Repository
public class ProfissionalDaoImpl implements ProfissionalDao {

	@Override
	public List<Pessoa> readAll() {
		
		List<Pessoa> profissionais = new ArrayList<Pessoa>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "select p.* from pessoa p inner join profissional pro on pro.pessoa_id = p.id";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				Pessoa profissional = new Pessoa();
								
				profissional.setId(resultSet.getLong("id"));
				profissional.setNome(resultSet.getString("nome"));
				profissional.setTelefone(resultSet.getString("telefone"));
				profissional.setEmail(resultSet.getString("email"));
				profissional.setSenha(resultSet.getString("senha"));
				profissional.setRua(resultSet.getString("rua"));
				profissional.setBairro(resultSet.getString("bairro"));
				profissional.setCidade(resultSet.getString("cidade"));
				profissional.setEstado(resultSet.getString("estado"));
				profissional.setPais(resultSet.getString("pais"));
				profissional.setFoto(resultSet.getString("foto"));
				profissional.setNumero(resultSet.getInt("numero"));
				profissional.setTipo(resultSet.getString("tipo"));
				
				profissionais.add(profissional);

			}
			
			return profissionais;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
	}

	@Override
	public boolean create(Profissional entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO profissional (pessoa_id)";
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
	public Profissional readById(Long id) {
		Profissional profissional = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM profissional WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				profissional = new Profissional();
				profissional.setId(resultSet.getLong("id"));
				profissional.setPessoaId(resultSet.getInt("pessoa_id"));
				

			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return profissional;
	}

	@Override
	public boolean update(Profissional entity) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE profissional SET pessoa_id = ? WHERE id = ?;";

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

		String sql = "DELETE FROM profissional WHERE id = ?;";

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
	public Map<String,String> passeiosAgrupadoPorMes(Long id) {
		
		Map<String,String> passeios = new HashMap<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "select " + 
				"	count(id) as passeios, " + 
				"	case " + 
				"		when (EXTRACT(MONTH FROM datahora)= 1) then 'janeiro' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 2) then 'fevereiro' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 3) then 'marco' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 4) then 'abril' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 5) then 'maio' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 6) then 'junho' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 7) then 'julho' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 8) then 'agosto' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 9) then 'setembro' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 10) then 'outubro' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 11) then 'novembro' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 12) then 'dezembro' " + 
				"	end as mes " + 
				"from passeio " + 
				"where profissional_id = ? " + 
				"group by EXTRACT(MONTH FROM datahora) " + 
				"order by mes asc";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				passeios.put(resultSet.getString("mes"), resultSet.getString("passeios"));
				
			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return null;
		
	}

}
