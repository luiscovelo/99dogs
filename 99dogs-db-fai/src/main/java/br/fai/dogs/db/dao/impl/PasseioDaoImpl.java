package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PasseioDao;
import br.fai.dogs.model.entities.Passeio;

@Repository
public class PasseioDaoImpl implements PasseioDao {

	@Override
	public List<Passeio> readAll() {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM passeio;";
			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Passeio passeio = new Passeio();
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));

				passeios.add(passeio);

			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios {99-dogs-fai}: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}
		
		return null;
		
	}

	@Override
	public Long create(Passeio entity) {
		
		Long idPasseio = null;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = "INSERT INTO passeio (status, datahora, valor, cliente_id, profissional_id, forma_de_pagamento_id)";
		sql += " VALUES (?, ?, ?, ?, ?, ?); ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
	        preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	        
	        preparedStatement.setString(1, entity.getStatus());
	        preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getDatahora()));
	        preparedStatement.setDouble(3, entity.getValor());
	        preparedStatement.setLong(4, entity.getClienteId());
	        preparedStatement.setLong(5, entity.getProfissionalId());
	        preparedStatement.setLong(6, entity.getFormaDePagamentoId());
	        	        
			preparedStatement.execute();
			
			connection.commit();
			
			resultSet = preparedStatement.getGeneratedKeys();
						
			if(resultSet.next()) {
				idPasseio = resultSet.getLong(1);
			}
			
		} catch (Exception e) {
			
			System.out.println("Falha ao criar o passeio {99-dogs-db-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de criar o passeio {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(resultSet,preparedStatement, connection);
		}

		return idPasseio;
	}

	@Override
	public Passeio readById(Long id) {
		
		Passeio passeio = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			String sql = "SELECT * FROM passeio WHERE id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				
				passeio = new Passeio();
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setProfissionalId(resultSet.getLong("profissional_id"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));

			}
			
			return passeio;
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao ler o passeio {99-dogs-fai}: " + e.getMessage());
			
		} finally {
						
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return passeio;
	}

	@Override
	public boolean update(Passeio entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "UPDATE passeio SET status = ?, data = ?, hora = ?, valor = ? WHERE id = ?;";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getStatus());
			preparedStatement.setTimestamp(2, Timestamp.valueOf(entity.getDatahora()));
			preparedStatement.setDouble(4, entity.getValor());
			preparedStatement.setLong(5, entity.getId());

			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao atualizar o passeio {99-dogs-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Ocorreu um problema no query de atualizar o passeio {99-dogs-fai}: " + e1.getMessage());
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

		String sql = "DELETE FROM passeio WHERE id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			preparedStatement.execute();
			connection.commit();
			
			return true;

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao atualizar o passeio {99-dogs-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Ocorreu no query do atualizar passeio {99-dogs-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return false;
		
	}
	
	@Override
	public List<Passeio> passeiosPorCliente(Long cliente_id) {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM passeio where cliente_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, cliente_id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Passeio passeio = new Passeio();
				
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setProfissionalId(resultSet.getLong("profissional_id"));

				passeios.add(passeio);

			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por cliente: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
		
	}

	@Override
	public List<Passeio> passeiosPorProfissional(Long profissional_id) {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
			
			String sql = "SELECT * FROM passeio where profissional_id = ?";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, profissional_id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {

				Passeio passeio = new Passeio();
				
				passeio.setId(resultSet.getLong("id"));
				passeio.setStatus(resultSet.getString("status"));
				passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
				passeio.setValor(resultSet.getDouble("valor"));
				passeio.setClienteId(resultSet.getLong("cliente_id"));
				passeio.setProfissionalId(resultSet.getLong("profissional_id"));

				passeios.add(passeio);

			}
			
			return passeios;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por profissional: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return null;
		
	}

	@Override
	public JSONObject detalhes(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		JSONObject response = new JSONObject();
		JSONObject passeio = new JSONObject();
		JSONObject cliente = new JSONObject();
		JSONObject formaPagamento = new JSONObject();
		
		String sql = "";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			sql = " select \r\n" + 
					"	PA.id as passeio_id,\r\n" + 
					"	PA.datahora as datahora,\r\n" + 
					"	PA.status as status,\r\n" + 
					"	PA.valor as valor,\r\n" + 
					"	PE.nome as cliente_nome,\r\n" + 
					"	PE.telefone as cliente_telefone,\r\n" + 
					"	PE.email as cliente_email,\r\n" + 
					"	PE.rua as cliente_endereco,\r\n" + 
					"	PE.bairro as cliente_bairro,\r\n" + 
					"	PE.cidade||'/'||PE.estado as cliente_cidade_estado,\r\n" + 
					"	PE.numero as cliente_numero,\r\n" + 
					"	PE.foto as cliente_foto,\r\n" + 
					"	FP.tipo as forma_de_pagamento\r\n" + 
					"from passeio PA\r\n" + 
					"inner join cliente CLI on CLI.id = PA.cliente_id\r\n" + 
					"inner join pessoa PE on PE.id = CLI.pessoa_id\r\n" + 
					"inner join forma_de_pagamento FP on FP.id = PA.forma_de_pagamento_id\r\n" + 
					"where PA.id = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				passeio.put("id", resultSet.getString("passeio_id"));
				passeio.put("datahora", resultSet.getString("datahora"));
				passeio.put("status", resultSet.getString("status"));
				passeio.put("valor", resultSet.getString("valor"));
				
				cliente.put("nome", resultSet.getString("cliente_nome"));
				cliente.put("telefone", resultSet.getString("cliente_telefone"));
				cliente.put("email", resultSet.getString("cliente_email"));
				cliente.put("endereco", resultSet.getString("cliente_endereco"));
				cliente.put("bairro", resultSet.getString("cliente_bairro"));
				cliente.put("cidade", resultSet.getString("cliente_cidade_estado"));
				cliente.put("numero", resultSet.getString("cliente_numero"));
				cliente.put("foto", resultSet.getString("cliente_foto"));
				
				formaPagamento.put("nome", resultSet.getString("forma_de_pagamento"));
				
			}
			
			response.put("passeio", passeio);
			response.put("cliente", cliente);
			response.put("formaDePagamento", formaPagamento);
			
			/* ----- query para listar os cachorros do passeio */
			
			sql = " select \r\n" + 
					"	CA.id as id,\r\n" + 
					"	CA.nome as nome,\r\n" + 
					"	CA.data_nascimento as datanascimento,\r\n" + 
					"	POR.descricao as porte,\r\n" + 
					"	R.nome as raca,\r\n" + 
					"	COMP.descricao as comportamento\r\n" + 
					"from passeio_cachorro PC\r\n" + 
					"inner join cachorro CA on CA.id = PC.cachorro_id\r\n" + 
					"inner join raca R on R.id = CA.raca_id\r\n" + 
					"inner join porte POR on POR.id = R.porte_id\r\n" + 
					"inner join comportamento COMP on COMP.id = R.comportamento_id\r\n" + 
					"where PC.passeio_id = ? ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			JSONObject cachorros = new JSONObject();
			
			while (resultSet.next()) {
				
				JSONObject cachorro = new JSONObject();
				
				cachorro.put("id", resultSet.getString("id"));
				cachorro.put("datanascimento", resultSet.getString("datanascimento"));
				cachorro.put("porte", resultSet.getString("porte"));
				cachorro.put("raca", resultSet.getString("raca"));
				cachorro.put("comportamento", resultSet.getString("comportamento"));
				
				cachorros.append("cachorro", cachorro);
				
			}
			
			response.put("cachorros", cachorros);
			
			return response;
			
		} catch (Exception e) {
			e.getMessage();
		}
		
		return new JSONObject();
		
	}
	
}
