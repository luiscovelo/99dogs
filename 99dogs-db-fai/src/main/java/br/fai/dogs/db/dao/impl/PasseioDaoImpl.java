package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.PasseioDao;
import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;

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
						
			String sql = " select " + 
					"	PA.*, " + 
					"	FP.tipo as fp_tipo, " + 
					"   CLI.id as cliente_id, " +
					"	PE_CLI.id as cliente_pessoa_id, " + 
					"	PE_CLI.nome as cliente_nome, " + 
					"	PE_CLI.email as cliente_email, " + 
					"	PE_CLI.telefone as cliente_telefone, " + 
					"	PE_CLI.rua as cliente_rua, " + 
					"	PE_CLI.bairro as cliente_bairro, " + 
					"	PE_CLI.cidade as cliente_cidade, " + 
					"	PE_CLI.estado as cliente_estado, " + 
					"	PE_CLI.pais as cliente_pais, " + 
					"	PE_CLI.numero as cliente_numero, " + 
					"	PE_CLI.foto as cliente_foto, " + 
					"	PE_CLI.tipo as cliente_tipo, " + 
					"	PRO.id as profissional_id, " + 
					"	PE_PRO.id as profissional_pessoa_id, " + 
					"	PE_PRO.nome as profissional_nome, " + 
					"	PE_PRO.email as profissional_email, " + 
					"	PE_PRO.telefone as profissional_telefone, " + 
					"	PE_PRO.rua as profissional_rua, " + 
					"	PE_PRO.bairro as profissional_bairro, " + 
					"	PE_PRO.cidade as profissional_cidade, " + 
					"	PE_PRO.estado as profissional_estado, " + 
					"	PE_PRO.pais as profissional_pais, " + 
					"	PE_PRO.numero as profissional_numero, " + 
					"	PE_PRO.foto as profissional_foto, " + 
					"	PE_PRO.tipo as profissional_tipo " + 
					"from passeio PA " + 
					"inner join forma_de_pagamento FP on FP.id = PA.forma_de_pagamento_id " + 
					"inner join cliente CLI on CLI.pessoa_id = PA.cliente_id " + 
					"inner join profissional PRO on PRO.pessoa_id = PA.profissional_id " + 
					"inner join pessoa PE_CLI on PE_CLI.id = CLI.pessoa_id " + 
					"inner join pessoa PE_PRO on PE_PRO.id = PRO.pessoa_id ";
								
				preparedStatement = connection.prepareStatement(sql);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					
					Passeio passeio 					= new Passeio();
					Cliente cliente 					= new Cliente();
					Profissional profissional 			= new Profissional();
					Pessoa pessoa_cliente 				= new Pessoa();
					Pessoa pessoa_profissional 			= new Pessoa();
					FormaDePagamento formaDePagamento 	= new FormaDePagamento();
					
					passeio.setId(resultSet.getLong("id"));
					passeio.setStatus(resultSet.getString("status"));
					passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
					passeio.setValor(resultSet.getDouble("valor"));
					passeio.setProfissionalId(resultSet.getLong("profissional_id"));
					passeio.setClienteId(resultSet.getLong("cliente_id"));
					passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));
					
					if(resultSet.getTimestamp("datahorafinalizacao") != null){
						passeio.setDatahorafinalizacao(resultSet.getTimestamp("datahorafinalizacao").toLocalDateTime());
					}
					
					pessoa_cliente.setId(resultSet.getLong("cliente_pessoa_id"));
					pessoa_cliente.setNome(resultSet.getString("cliente_nome"));
					pessoa_cliente.setEmail(resultSet.getString("cliente_email"));
					pessoa_cliente.setTelefone(resultSet.getString("cliente_telefone"));
					pessoa_cliente.setRua(resultSet.getString("cliente_rua"));
					pessoa_cliente.setBairro(resultSet.getString("cliente_bairro"));
					pessoa_cliente.setCidade(resultSet.getString("cliente_cidade"));
					pessoa_cliente.setEstado(resultSet.getString("cliente_estado"));
					pessoa_cliente.setPais(resultSet.getString("cliente_pais"));
					pessoa_cliente.setNumero(resultSet.getInt("cliente_numero"));
					pessoa_cliente.setFoto(resultSet.getString("cliente_foto"));
					pessoa_cliente.setTipo(resultSet.getString("cliente_tipo"));
					
					cliente.setId(resultSet.getLong("cliente_id"));
					cliente.setPessoaId(resultSet.getInt("cliente_pessoa_id"));
					cliente.setPessoa(pessoa_cliente);
					
					pessoa_profissional.setId(resultSet.getLong("profissional_pessoa_id"));
					pessoa_profissional.setNome(resultSet.getString("profissional_nome"));
					pessoa_profissional.setEmail(resultSet.getString("profissional_email"));
					pessoa_profissional.setTelefone(resultSet.getString("profissional_telefone"));
					pessoa_profissional.setRua(resultSet.getString("profissional_rua"));
					pessoa_profissional.setBairro(resultSet.getString("profissional_bairro"));
					pessoa_profissional.setCidade(resultSet.getString("profissional_cidade"));
					pessoa_profissional.setEstado(resultSet.getString("profissional_estado"));
					pessoa_profissional.setPais(resultSet.getString("profissional_pais"));
					pessoa_profissional.setNumero(resultSet.getInt("profissional_numero"));
					pessoa_profissional.setFoto(resultSet.getString("profissional_foto"));
					pessoa_profissional.setTipo(resultSet.getString("profissional_tipo"));
					
					profissional.setId(resultSet.getLong("profissional_id"));
					profissional.setPessoaId(resultSet.getInt("profissional_pessoa_id"));
					profissional.setPessoa(pessoa_profissional);
					
					formaDePagamento.setId(resultSet.getLong("forma_de_pagamento_id"));
					formaDePagamento.setTipo(resultSet.getString("fp_tipo"));
					
					passeio.setCliente(cliente);
					passeio.setProfissional(profissional);
					passeio.setFormaDePagamento(formaDePagamento);
					
					passeios.add(passeio);
					
				}
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por cliente: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return passeios;
		
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
		
		Passeio passeio = new Passeio();
		Cliente cliente = new Cliente();
		Profissional profissional = new Profissional();
		Pessoa pessoa_cliente = new Pessoa();
		Pessoa pessoa_profissional = new Pessoa();
		FormaDePagamento formaDePagamento = new FormaDePagamento();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select " + 
				"	PA.*, " + 
				"	FP.tipo as fp_tipo, " + 
				"   CLI.id as cliente_id, " +
				"	PE_CLI.id as cliente_pessoa_id, " + 
				"	PE_CLI.nome as cliente_nome, " + 
				"	PE_CLI.email as cliente_email, " + 
				"	PE_CLI.telefone as cliente_telefone, " + 
				"	PE_CLI.rua as cliente_rua, " + 
				"	PE_CLI.bairro as cliente_bairro, " + 
				"	PE_CLI.cidade as cliente_cidade, " + 
				"	PE_CLI.estado as cliente_estado, " + 
				"	PE_CLI.pais as cliente_pais, " + 
				"	PE_CLI.numero as cliente_numero, " + 
				"	PE_CLI.foto as cliente_foto, " + 
				"	PE_CLI.tipo as cliente_tipo, " + 
				"	PRO.id as profissional_id, " + 
				"	PE_PRO.id as profissional_pessoa_id, " + 
				"	PE_PRO.nome as profissional_nome, " + 
				"	PE_PRO.email as profissional_email, " + 
				"	PE_PRO.telefone as profissional_telefone, " + 
				"	PE_PRO.rua as profissional_rua, " + 
				"	PE_PRO.bairro as profissional_bairro, " + 
				"	PE_PRO.cidade as profissional_cidade, " + 
				"	PE_PRO.estado as profissional_estado, " + 
				"	PE_PRO.pais as profissional_pais, " + 
				"	PE_PRO.numero as profissional_numero, " + 
				"	PE_PRO.foto as profissional_foto, " + 
				"	PE_PRO.tipo as profissional_tipo " + 
				"from passeio PA " + 
				"inner join forma_de_pagamento FP on FP.id = PA.forma_de_pagamento_id " + 
				"inner join cliente CLI on CLI.pessoa_id = PA.cliente_id " + 
				"inner join profissional PRO on PRO.pessoa_id = PA.profissional_id " + 
				"inner join pessoa PE_CLI on PE_CLI.id = CLI.pessoa_id " + 
				"inner join pessoa PE_PRO on PE_PRO.id = PRO.pessoa_id " + 
				"where PA.id = ? ";
				
		try {
			
			connection = ConnectionFactory.getConnection();
			
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
				
				if(resultSet.getTimestamp("datahorafinalizacao") != null){
					passeio.setDatahorafinalizacao(resultSet.getTimestamp("datahorafinalizacao").toLocalDateTime());
				}
				
				pessoa_cliente.setId(resultSet.getLong("cliente_pessoa_id"));
				pessoa_cliente.setNome(resultSet.getString("cliente_nome"));
				pessoa_cliente.setEmail(resultSet.getString("cliente_email"));
				pessoa_cliente.setTelefone(resultSet.getString("cliente_telefone"));
				pessoa_cliente.setRua(resultSet.getString("cliente_rua"));
				pessoa_cliente.setBairro(resultSet.getString("cliente_bairro"));
				pessoa_cliente.setCidade(resultSet.getString("cliente_cidade"));
				pessoa_cliente.setEstado(resultSet.getString("cliente_estado"));
				pessoa_cliente.setPais(resultSet.getString("cliente_pais"));
				pessoa_cliente.setNumero(resultSet.getInt("cliente_numero"));
				pessoa_cliente.setFoto(resultSet.getString("cliente_foto"));
				pessoa_cliente.setTipo(resultSet.getString("cliente_tipo"));
				
				cliente.setId(resultSet.getLong("cliente_id"));
				cliente.setPessoaId(resultSet.getInt("cliente_pessoa_id"));
				cliente.setPessoa(pessoa_cliente);
				
				pessoa_profissional.setId(resultSet.getLong("profissional_pessoa_id"));
				pessoa_profissional.setNome(resultSet.getString("profissional_nome"));
				pessoa_profissional.setEmail(resultSet.getString("profissional_email"));
				pessoa_profissional.setTelefone(resultSet.getString("profissional_telefone"));
				pessoa_profissional.setRua(resultSet.getString("profissional_rua"));
				pessoa_profissional.setBairro(resultSet.getString("profissional_bairro"));
				pessoa_profissional.setCidade(resultSet.getString("profissional_cidade"));
				pessoa_profissional.setEstado(resultSet.getString("profissional_estado"));
				pessoa_profissional.setPais(resultSet.getString("profissional_pais"));
				pessoa_profissional.setNumero(resultSet.getInt("profissional_numero"));
				pessoa_profissional.setFoto(resultSet.getString("profissional_foto"));
				pessoa_profissional.setTipo(resultSet.getString("profissional_tipo"));
				
				profissional.setId(resultSet.getLong("profissional_id"));
				profissional.setPessoaId(resultSet.getInt("profissional_pessoa_id"));
				profissional.setPessoa(pessoa_profissional);
				
				formaDePagamento.setId(resultSet.getLong("forma_de_pagamento_id"));
				formaDePagamento.setTipo(resultSet.getString("fp_tipo"));
				
				passeio.setCliente(cliente);
				passeio.setProfissional(profissional);
				passeio.setFormaDePagamento(formaDePagamento);
				
			}
			
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
						
			String sql = " select " + 
					"	PA.*, " + 
					"	FP.tipo as fp_tipo, " + 
					"   CLI.id as cliente_id, " +
					"	PE_CLI.id as cliente_pessoa_id, " + 
					"	PE_CLI.nome as cliente_nome, " + 
					"	PE_CLI.email as cliente_email, " + 
					"	PE_CLI.telefone as cliente_telefone, " + 
					"	PE_CLI.rua as cliente_rua, " + 
					"	PE_CLI.bairro as cliente_bairro, " + 
					"	PE_CLI.cidade as cliente_cidade, " + 
					"	PE_CLI.estado as cliente_estado, " + 
					"	PE_CLI.pais as cliente_pais, " + 
					"	PE_CLI.numero as cliente_numero, " + 
					"	PE_CLI.foto as cliente_foto, " + 
					"	PE_CLI.tipo as cliente_tipo, " + 
					"	PRO.id as profissional_id, " + 
					"	PE_PRO.id as profissional_pessoa_id, " + 
					"	PE_PRO.nome as profissional_nome, " + 
					"	PE_PRO.email as profissional_email, " + 
					"	PE_PRO.telefone as profissional_telefone, " + 
					"	PE_PRO.rua as profissional_rua, " + 
					"	PE_PRO.bairro as profissional_bairro, " + 
					"	PE_PRO.cidade as profissional_cidade, " + 
					"	PE_PRO.estado as profissional_estado, " + 
					"	PE_PRO.pais as profissional_pais, " + 
					"	PE_PRO.numero as profissional_numero, " + 
					"	PE_PRO.foto as profissional_foto, " + 
					"	PE_PRO.tipo as profissional_tipo " + 
					"from passeio PA " + 
					"inner join forma_de_pagamento FP on FP.id = PA.forma_de_pagamento_id " + 
					"inner join cliente CLI on CLI.pessoa_id = PA.cliente_id " + 
					"inner join profissional PRO on PRO.pessoa_id = PA.profissional_id " + 
					"inner join pessoa PE_CLI on PE_CLI.id = CLI.pessoa_id " + 
					"inner join pessoa PE_PRO on PE_PRO.id = PRO.pessoa_id " + 
					"where PA.cliente_id = ? ";
								
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, cliente_id);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					
					Passeio passeio 					= new Passeio();
					Cliente cliente 					= new Cliente();
					Profissional profissional 			= new Profissional();
					Pessoa pessoa_cliente 				= new Pessoa();
					Pessoa pessoa_profissional 			= new Pessoa();
					FormaDePagamento formaDePagamento 	= new FormaDePagamento();
					
					passeio.setId(resultSet.getLong("id"));
					passeio.setStatus(resultSet.getString("status"));
					passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
					passeio.setValor(resultSet.getDouble("valor"));
					passeio.setProfissionalId(resultSet.getLong("profissional_id"));
					passeio.setClienteId(resultSet.getLong("cliente_id"));
					passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));
					
					if(resultSet.getTimestamp("datahorafinalizacao") != null){
						passeio.setDatahorafinalizacao(resultSet.getTimestamp("datahorafinalizacao").toLocalDateTime());
					}
					
					pessoa_cliente.setId(resultSet.getLong("cliente_pessoa_id"));
					pessoa_cliente.setNome(resultSet.getString("cliente_nome"));
					pessoa_cliente.setEmail(resultSet.getString("cliente_email"));
					pessoa_cliente.setTelefone(resultSet.getString("cliente_telefone"));
					pessoa_cliente.setRua(resultSet.getString("cliente_rua"));
					pessoa_cliente.setBairro(resultSet.getString("cliente_bairro"));
					pessoa_cliente.setCidade(resultSet.getString("cliente_cidade"));
					pessoa_cliente.setEstado(resultSet.getString("cliente_estado"));
					pessoa_cliente.setPais(resultSet.getString("cliente_pais"));
					pessoa_cliente.setNumero(resultSet.getInt("cliente_numero"));
					pessoa_cliente.setFoto(resultSet.getString("cliente_foto"));
					pessoa_cliente.setTipo(resultSet.getString("cliente_tipo"));
					
					cliente.setId(resultSet.getLong("cliente_id"));
					cliente.setPessoaId(resultSet.getInt("cliente_pessoa_id"));
					cliente.setPessoa(pessoa_cliente);
					
					pessoa_profissional.setId(resultSet.getLong("profissional_pessoa_id"));
					pessoa_profissional.setNome(resultSet.getString("profissional_nome"));
					pessoa_profissional.setEmail(resultSet.getString("profissional_email"));
					pessoa_profissional.setTelefone(resultSet.getString("profissional_telefone"));
					pessoa_profissional.setRua(resultSet.getString("profissional_rua"));
					pessoa_profissional.setBairro(resultSet.getString("profissional_bairro"));
					pessoa_profissional.setCidade(resultSet.getString("profissional_cidade"));
					pessoa_profissional.setEstado(resultSet.getString("profissional_estado"));
					pessoa_profissional.setPais(resultSet.getString("profissional_pais"));
					pessoa_profissional.setNumero(resultSet.getInt("profissional_numero"));
					pessoa_profissional.setFoto(resultSet.getString("profissional_foto"));
					pessoa_profissional.setTipo(resultSet.getString("profissional_tipo"));
					
					profissional.setId(resultSet.getLong("profissional_id"));
					profissional.setPessoaId(resultSet.getInt("profissional_pessoa_id"));
					profissional.setPessoa(pessoa_profissional);
					
					formaDePagamento.setId(resultSet.getLong("forma_de_pagamento_id"));
					formaDePagamento.setTipo(resultSet.getString("fp_tipo"));
					
					passeio.setCliente(cliente);
					passeio.setProfissional(profissional);
					passeio.setFormaDePagamento(formaDePagamento);
					
					passeios.add(passeio);
					
				}
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por cliente: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return passeios;
		
	}

	@Override
	public List<Passeio> passeiosPorProfissional(Long profissional_id) {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
						
			String sql = " select " + 
					"	PA.*, " + 
					"	FP.tipo as fp_tipo, " + 
					"   CLI.id as cliente_id, " +
					"	PE_CLI.id as cliente_pessoa_id, " + 
					"	PE_CLI.nome as cliente_nome, " + 
					"	PE_CLI.email as cliente_email, " + 
					"	PE_CLI.telefone as cliente_telefone, " + 
					"	PE_CLI.rua as cliente_rua, " + 
					"	PE_CLI.bairro as cliente_bairro, " + 
					"	PE_CLI.cidade as cliente_cidade, " + 
					"	PE_CLI.estado as cliente_estado, " + 
					"	PE_CLI.pais as cliente_pais, " + 
					"	PE_CLI.numero as cliente_numero, " + 
					"	PE_CLI.foto as cliente_foto, " + 
					"	PE_CLI.tipo as cliente_tipo, " + 
					"	PRO.id as profissional_id, " + 
					"	PE_PRO.id as profissional_pessoa_id, " + 
					"	PE_PRO.nome as profissional_nome, " + 
					"	PE_PRO.email as profissional_email, " + 
					"	PE_PRO.telefone as profissional_telefone, " + 
					"	PE_PRO.rua as profissional_rua, " + 
					"	PE_PRO.bairro as profissional_bairro, " + 
					"	PE_PRO.cidade as profissional_cidade, " + 
					"	PE_PRO.estado as profissional_estado, " + 
					"	PE_PRO.pais as profissional_pais, " + 
					"	PE_PRO.numero as profissional_numero, " + 
					"	PE_PRO.foto as profissional_foto, " + 
					"	PE_PRO.tipo as profissional_tipo " + 
					"from passeio PA " + 
					"inner join forma_de_pagamento FP on FP.id = PA.forma_de_pagamento_id " + 
					"inner join cliente CLI on CLI.pessoa_id = PA.cliente_id " + 
					"inner join profissional PRO on PRO.pessoa_id = PA.profissional_id " + 
					"inner join pessoa PE_CLI on PE_CLI.id = CLI.pessoa_id " + 
					"inner join pessoa PE_PRO on PE_PRO.id = PRO.pessoa_id " + 
					"where PA.profissional_id = ? ";

				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setLong(1, profissional_id);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					
					Passeio passeio 					= new Passeio();
					Cliente cliente 					= new Cliente();
					Profissional profissional 			= new Profissional();
					Pessoa pessoa_cliente 				= new Pessoa();
					Pessoa pessoa_profissional 			= new Pessoa();
					FormaDePagamento formaDePagamento 	= new FormaDePagamento();
					
					passeio.setId(resultSet.getLong("id"));
					passeio.setStatus(resultSet.getString("status"));
					passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
					passeio.setValor(resultSet.getDouble("valor"));
					passeio.setProfissionalId(resultSet.getLong("profissional_id"));
					passeio.setClienteId(resultSet.getLong("cliente_id"));
					passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));
					
					if(resultSet.getTimestamp("datahorafinalizacao") != null){
						passeio.setDatahorafinalizacao(resultSet.getTimestamp("datahorafinalizacao").toLocalDateTime());
					}
					
					pessoa_cliente.setId(resultSet.getLong("cliente_pessoa_id"));
					pessoa_cliente.setNome(resultSet.getString("cliente_nome"));
					pessoa_cliente.setEmail(resultSet.getString("cliente_email"));
					pessoa_cliente.setTelefone(resultSet.getString("cliente_telefone"));
					pessoa_cliente.setRua(resultSet.getString("cliente_rua"));
					pessoa_cliente.setBairro(resultSet.getString("cliente_bairro"));
					pessoa_cliente.setCidade(resultSet.getString("cliente_cidade"));
					pessoa_cliente.setEstado(resultSet.getString("cliente_estado"));
					pessoa_cliente.setPais(resultSet.getString("cliente_pais"));
					pessoa_cliente.setNumero(resultSet.getInt("cliente_numero"));
					pessoa_cliente.setFoto(resultSet.getString("cliente_foto"));
					pessoa_cliente.setTipo(resultSet.getString("cliente_tipo"));
					
					cliente.setId(resultSet.getLong("cliente_id"));
					cliente.setPessoaId(resultSet.getInt("cliente_pessoa_id"));
					cliente.setPessoa(pessoa_cliente);
					
					pessoa_profissional.setId(resultSet.getLong("profissional_pessoa_id"));
					pessoa_profissional.setNome(resultSet.getString("profissional_nome"));
					pessoa_profissional.setEmail(resultSet.getString("profissional_email"));
					pessoa_profissional.setTelefone(resultSet.getString("profissional_telefone"));
					pessoa_profissional.setRua(resultSet.getString("profissional_rua"));
					pessoa_profissional.setBairro(resultSet.getString("profissional_bairro"));
					pessoa_profissional.setCidade(resultSet.getString("profissional_cidade"));
					pessoa_profissional.setEstado(resultSet.getString("profissional_estado"));
					pessoa_profissional.setPais(resultSet.getString("profissional_pais"));
					pessoa_profissional.setNumero(resultSet.getInt("profissional_numero"));
					pessoa_profissional.setFoto(resultSet.getString("profissional_foto"));
					pessoa_profissional.setTipo(resultSet.getString("profissional_tipo"));
					
					profissional.setId(resultSet.getLong("profissional_id"));
					profissional.setPessoaId(resultSet.getInt("profissional_pessoa_id"));
					profissional.setPessoa(pessoa_profissional);
					
					formaDePagamento.setId(resultSet.getLong("forma_de_pagamento_id"));
					formaDePagamento.setTipo(resultSet.getString("fp_tipo"));
					
					passeio.setCliente(cliente);
					passeio.setProfissional(profissional);
					passeio.setFormaDePagamento(formaDePagamento);
					
					passeios.add(passeio);
					
				}
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por profissional: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return passeios;
		
	}

	@Override
	public boolean verificarDisponibilidade(String datahora, Long id) {
		
		Boolean disponivel = false;
		Long numeroDePasseios = Long.valueOf(0);
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			
			datahora = datahora.replace("T", " ");
						
			connection = ConnectionFactory.getConnection();
						
			String sql = "select " + 
					"	count(id) as numero_de_passeios " + 
					"from passeio " + 
					"where to_char(datahora,'YYYY-MM-DD HH24:MI') = '" + datahora + "' " + 
					"and profissional_id = ? and status <> 'Finalizado' ";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();
			
			if(resultSet.next()) {
				
				numeroDePasseios = resultSet.getLong("numero_de_passeios");

				if(numeroDePasseios == 0) {
					disponivel = true;
				}
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao executar a disponibilidade de horario: " + e.getMessage());
			
		} finally {
			
			ConnectionFactory.close(resultSet, preparedStatement, connection);
			
		}
		
		return disponivel;
		
	}

	@Override
	public boolean alterarStatus(Passeio entity) {
		
		boolean response = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = "";
		
		if(entity.getStatus().equals("Finalizado")) {			
			sql = " update passeio set status = ?, datahorafinalizacao = ? where id = ? ";
		}else {
			sql = " update passeio set status = ? where id = ? ";
		}
		
		System.out.println(entity.getStatus());
		try {
			
			Date dataAtual = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
	        preparedStatement = connection.prepareStatement(sql);
	        
	        if(entity.getStatus().equals("Finalizado")) {	
	        	
	        	preparedStatement.setString(1, entity.getStatus());
	        	preparedStatement.setTimestamp(2, Timestamp.valueOf(formatter.format(dataAtual)));
	        	preparedStatement.setLong(3, entity.getId());
	        	
	        }else {
	        	
	        	preparedStatement.setString(1, entity.getStatus());
	        	preparedStatement.setLong(2, entity.getId());
	        	
	        }
	        		        	        
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			
			System.out.println("Falha ao aprovar o passeio {99-dogs-db-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de aprovar o passeio {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public List<Passeio> readAllByProfissionalIdAndStatus(Long id, String status) {
		
		List<Passeio> passeios = new ArrayList<Passeio>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();
						
			String sql = " select " + 
					"	PA.*, " + 
					"	FP.tipo as fp_tipo, " + 
					"   CLI.id as cliente_id, " +
					"	PE_CLI.id as cliente_pessoa_id, " + 
					"	PE_CLI.nome as cliente_nome, " + 
					"	PE_CLI.email as cliente_email, " + 
					"	PE_CLI.telefone as cliente_telefone, " + 
					"	PE_CLI.rua as cliente_rua, " + 
					"	PE_CLI.bairro as cliente_bairro, " + 
					"	PE_CLI.cidade as cliente_cidade, " + 
					"	PE_CLI.estado as cliente_estado, " + 
					"	PE_CLI.pais as cliente_pais, " + 
					"	PE_CLI.numero as cliente_numero, " + 
					"	PE_CLI.foto as cliente_foto, " + 
					"	PE_CLI.tipo as cliente_tipo, " + 
					"	PRO.id as profissional_id, " + 
					"	PE_PRO.id as profissional_pessoa_id, " + 
					"	PE_PRO.nome as profissional_nome, " + 
					"	PE_PRO.email as profissional_email, " + 
					"	PE_PRO.telefone as profissional_telefone, " + 
					"	PE_PRO.rua as profissional_rua, " + 
					"	PE_PRO.bairro as profissional_bairro, " + 
					"	PE_PRO.cidade as profissional_cidade, " + 
					"	PE_PRO.estado as profissional_estado, " + 
					"	PE_PRO.pais as profissional_pais, " + 
					"	PE_PRO.numero as profissional_numero, " + 
					"	PE_PRO.foto as profissional_foto, " + 
					"	PE_PRO.tipo as profissional_tipo " + 
					"from passeio PA " + 
					"inner join forma_de_pagamento FP on FP.id = PA.forma_de_pagamento_id " + 
					"inner join cliente CLI on CLI.pessoa_id = PA.cliente_id " + 
					"inner join profissional PRO on PRO.pessoa_id = PA.profissional_id " + 
					"inner join pessoa PE_CLI on PE_CLI.id = CLI.pessoa_id " + 
					"inner join pessoa PE_PRO on PE_PRO.id = PRO.pessoa_id " + 
					"where PA.profissional_id = ? and PA.status = ? ";

				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setLong(1, id);
				preparedStatement.setString(2, status);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					
					Passeio passeio 					= new Passeio();
					Cliente cliente 					= new Cliente();
					Profissional profissional 			= new Profissional();
					Pessoa pessoa_cliente 				= new Pessoa();
					Pessoa pessoa_profissional 			= new Pessoa();
					FormaDePagamento formaDePagamento 	= new FormaDePagamento();
					
					passeio.setId(resultSet.getLong("id"));
					passeio.setStatus(resultSet.getString("status"));
					passeio.setDatahora(resultSet.getTimestamp("datahora").toLocalDateTime());
					passeio.setValor(resultSet.getDouble("valor"));
					passeio.setProfissionalId(resultSet.getLong("profissional_id"));
					passeio.setClienteId(resultSet.getLong("cliente_id"));
					passeio.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));
					
					if(resultSet.getTimestamp("datahorafinalizacao") != null){
						passeio.setDatahorafinalizacao(resultSet.getTimestamp("datahorafinalizacao").toLocalDateTime());
					}
					
					pessoa_cliente.setId(resultSet.getLong("cliente_pessoa_id"));
					pessoa_cliente.setNome(resultSet.getString("cliente_nome"));
					pessoa_cliente.setEmail(resultSet.getString("cliente_email"));
					pessoa_cliente.setTelefone(resultSet.getString("cliente_telefone"));
					pessoa_cliente.setRua(resultSet.getString("cliente_rua"));
					pessoa_cliente.setBairro(resultSet.getString("cliente_bairro"));
					pessoa_cliente.setCidade(resultSet.getString("cliente_cidade"));
					pessoa_cliente.setEstado(resultSet.getString("cliente_estado"));
					pessoa_cliente.setPais(resultSet.getString("cliente_pais"));
					pessoa_cliente.setNumero(resultSet.getInt("cliente_numero"));
					pessoa_cliente.setFoto(resultSet.getString("cliente_foto"));
					pessoa_cliente.setTipo(resultSet.getString("cliente_tipo"));
					
					cliente.setId(resultSet.getLong("cliente_id"));
					cliente.setPessoaId(resultSet.getInt("cliente_pessoa_id"));
					cliente.setPessoa(pessoa_cliente);
					
					pessoa_profissional.setId(resultSet.getLong("profissional_pessoa_id"));
					pessoa_profissional.setNome(resultSet.getString("profissional_nome"));
					pessoa_profissional.setEmail(resultSet.getString("profissional_email"));
					pessoa_profissional.setTelefone(resultSet.getString("profissional_telefone"));
					pessoa_profissional.setRua(resultSet.getString("profissional_rua"));
					pessoa_profissional.setBairro(resultSet.getString("profissional_bairro"));
					pessoa_profissional.setCidade(resultSet.getString("profissional_cidade"));
					pessoa_profissional.setEstado(resultSet.getString("profissional_estado"));
					pessoa_profissional.setPais(resultSet.getString("profissional_pais"));
					pessoa_profissional.setNumero(resultSet.getInt("profissional_numero"));
					pessoa_profissional.setFoto(resultSet.getString("profissional_foto"));
					pessoa_profissional.setTipo(resultSet.getString("profissional_tipo"));
					
					profissional.setId(resultSet.getLong("profissional_id"));
					profissional.setPessoaId(resultSet.getInt("profissional_pessoa_id"));
					profissional.setPessoa(pessoa_profissional);
					
					formaDePagamento.setId(resultSet.getLong("forma_de_pagamento_id"));
					formaDePagamento.setTipo(resultSet.getString("fp_tipo"));
					
					passeio.setCliente(cliente);
					passeio.setProfissional(profissional);
					passeio.setFormaDePagamento(formaDePagamento);
					
					passeios.add(passeio);
					
				}
			
		} catch (Exception e) {
			
			System.out.println("Falha ao obter lista de passeios por profissional e por status: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return passeios;
		
	}
	
}
