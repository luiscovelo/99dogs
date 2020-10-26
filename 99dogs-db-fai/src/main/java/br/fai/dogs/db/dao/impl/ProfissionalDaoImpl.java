package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
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
	public List<Profissional> readAll() {
		
		List<Profissional> profissionais = new ArrayList<Profissional>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = "select p.*, pro.id as pro_id, pro.media_avaliacao as media_avaliacao, pro.qtde_avaliacao as qtde_avaliacao from pessoa p inner join profissional pro on pro.pessoa_id = p.id";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				Profissional profissional = new Profissional();
				Pessoa pessoa = new Pessoa();
							
				pessoa.setId(resultSet.getLong("id"));
				pessoa.setNome(resultSet.getString("nome"));
				pessoa.setTelefone(resultSet.getString("telefone"));
				pessoa.setEmail(resultSet.getString("email"));
				pessoa.setRua(resultSet.getString("rua"));
				pessoa.setBairro(resultSet.getString("bairro"));
				pessoa.setCidade(resultSet.getString("cidade"));
				pessoa.setEstado(resultSet.getString("estado"));
				pessoa.setPais(resultSet.getString("pais"));
				pessoa.setNumero(resultSet.getInt("numero"));
				pessoa.setTipo(resultSet.getString("tipo"));
				
				if(resultSet.getBytes("foto") != null) {
					pessoa.setBase64Foto(Base64.getEncoder().encodeToString(resultSet.getBytes("foto")));
				}
				
				profissional.setId(resultSet.getLong("pro_id"));
				profissional.setMediaAvaliacao(resultSet.getInt("media_avaliacao"));
				profissional.setQtdeAvaliacao(resultSet.getInt("qtde_avaliacao"));
				profissional.setPessoa(pessoa);
				
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
				"		when (EXTRACT(MONTH FROM datahora)= 1) then '01/JAN' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 2) then '02/FEV' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 3) then '03/MAR' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 4) then '04/ABR' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 5) then '05/MAI' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 6) then '06/JUN' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 7) then '07/JUL' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 8) then '08/AGO' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 9) then '09/SET' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 10) then '10/OUT' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 11) then '11/NOV' " + 
				"		when (EXTRACT(MONTH FROM datahora)= 12) then '12/DEZ' " + 
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

	@Override
	public Map<String, String> ticketMedioAgrupadoPorMes(Long id) {
		
		Map<String,String> ticketMedio = new HashMap<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select (sum(R.valor)/count(PA.id)) as ticketMedio, " + 
				"		case " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 1) then '01/JAN' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 2) then '02/FEV' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 3) then '03/MAR' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 4) then '04/ABR' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 5) then '05/MAI' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 6) then '06/JUN' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 7) then '07/JUL' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 8) then '08/AGO' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 9) then '09/SET' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 10) then '10/OUT' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 11) then '11/NOV' " + 
				"		when (EXTRACT(MONTH FROM PA.datahora)= 12) then '12/DEZ' " + 
				"		end as mes " + 
				"		from passeio PA " + 
				"		inner join recebimento R on R.passeio_id = PA.id " + 
				"		where PA.profissional_id = ? " + 
				"		group by EXTRACT(MONTH FROM PA.datahora) " + 
				"		order by EXTRACT(MONTH FROM PA.datahora) asc ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				ticketMedio.put(resultSet.getString("mes"), resultSet.getString("ticketMedio"));
				
			}
			
			return ticketMedio;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return null;
		
	}

	@Override
	public Map<String, String> recebimentoAgrupadoPorMes(Long id) {
		
		Map<String,String> recebimento = new HashMap<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select " + 
				"	sum(R.valor) as total, FP.tipo " + 
				"from passeio PA\r\n" + 
				"inner join recebimento R on R.passeio_id = PA.id " + 
				"inner join forma_de_pagamento FP on FP.id = R.forma_de_pagamento_id " + 
				"where PA.profissional_id = ? " + 
				"group by FP.tipo ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				recebimento.put(resultSet.getString("tipo"), resultSet.getString("total"));
				
			}
			
			return recebimento;
			
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}
		
		return null;
		
	}

}
