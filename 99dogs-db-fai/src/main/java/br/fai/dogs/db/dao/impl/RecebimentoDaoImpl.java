package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.RecebimentoDao;
import br.fai.dogs.model.entities.Cliente;
import br.fai.dogs.model.entities.FormaDePagamento;
import br.fai.dogs.model.entities.Passeio;
import br.fai.dogs.model.entities.Pessoa;
import br.fai.dogs.model.entities.Profissional;
import br.fai.dogs.model.entities.Recebimento;

@Repository
public class RecebimentoDaoImpl implements RecebimentoDao {

	@Override
	public List<Recebimento> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean create(Recebimento entity) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "INSERT INTO recebimento (data_recebimento, valor, forma_de_pagamento_id, profissional_id, passeio_id) ";
		sql += " VALUES (?, ?, ?, ?, ?); ";
				
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setDate(1, new java.sql.Date(entity.getDataRecebimento().getTime()));
			preparedStatement.setDouble(2, entity.getValor());
			preparedStatement.setLong(3, entity.getFormaDePagamentoId());
			preparedStatement.setLong(4, entity.getProfissionalId());
			preparedStatement.setLong(5, entity.getPasseioId());
			
			preparedStatement.execute();

			connection.commit();
			
			return true;
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao criar o recebimento {99dogs-db-fai}: " + e.getMessage());
			
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
	public Recebimento readById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Recebimento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteById(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Recebimento> readAllByProfissionalId(Long id) {
		
		List<Recebimento> recebimentos = new ArrayList<Recebimento>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		String sql = " select R.*, FP.tipo from recebimento R " + 
				"inner join forma_de_pagamento FP on FP.id = R.forma_de_pagamento_id " + 
				"where R.profissional_id = ? ";

		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				
				Recebimento recebimento = new Recebimento();
				FormaDePagamento formaDePagamento = new FormaDePagamento();
				
				formaDePagamento.setId(resultSet.getLong("forma_de_pagamento_id"));
				formaDePagamento.setTipo(resultSet.getString("tipo"));
				
				recebimento.setId(resultSet.getLong("id"));
				recebimento.setDataRecebimento(resultSet.getDate("data_recebimento"));
				recebimento.setFormaDePagamentoId(resultSet.getLong("forma_de_pagamento_id"));
				recebimento.setPasseioId(resultSet.getLong("passeio_id"));
				recebimento.setProfissionalId(resultSet.getLong("profissional_id"));
				recebimento.setValor(resultSet.getDouble("valor"));
				
				recebimento.setFormaDePagamento(formaDePagamento);
				
				recebimentos.add(recebimento);
				
			}
			
		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao buscar os recebimento por profissional {99-db-fai}: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		} finally {
			ConnectionFactory.close(resultSet, preparedStatement, connection);
		}

		return recebimentos;
		
	}

	@Override
	public List<Passeio> readPasseiosSemRecebimentoPorProfissional(Long id) {
		
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
					"left join recebimento REC on REC.passeio_id = PA.id " + 
					"where PA.profissional_id = ? and REC.id is null and PA.status = 'Finalizado' ";

				preparedStatement = connection.prepareStatement(sql);
				
				preparedStatement.setLong(1, id);

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
