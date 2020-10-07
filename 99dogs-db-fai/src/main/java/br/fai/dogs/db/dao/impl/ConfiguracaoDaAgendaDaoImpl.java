package br.fai.dogs.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.ConfiguracaoDaAgendaDao;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;

@Repository
public class ConfiguracaoDaAgendaDaoImpl implements ConfiguracaoDaAgendaDao {

	@Override
	public boolean create(ConfiguracaoDaAgenda entity) {
		
		boolean response = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = " insert into configuracao_agenda (dia_semana,hora_inicio,hora_final,tempo_de_passeio,valor_passeio,profissional_id) "
				+ " values (?,?,?,?,?,?) ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, entity.getDiaSemana());
			preparedStatement.setTime(2, Time.valueOf(entity.getHoraInicio()));
			preparedStatement.setTime(3, Time.valueOf(entity.getHoraFinal()));
			preparedStatement.setLong(4, entity.getTempoDePasseio());
			preparedStatement.setDouble(5, entity.getValorPasseio());
			preparedStatement.setLong(6, entity.getProfissionalId());
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao salvar a configuracao da agenda: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de criar a configuracao da agenda {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public List<ConfiguracaoDaAgenda> readByProfissionalId(Long id) {
		
		List<ConfiguracaoDaAgenda> response = new ArrayList<ConfiguracaoDaAgenda>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select * from configuracao_agenda where profissional_id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				
				ConfiguracaoDaAgenda config = new ConfiguracaoDaAgenda();
				
				config.setId(resultSet.getLong("id"));
				config.setDiaSemana(resultSet.getLong("dia_semana"));
				config.setHoraInicio(resultSet.getTime("hora_inicio").toLocalTime());
				config.setHoraFinal(resultSet.getTime("hora_final").toLocalTime());
				config.setTempoDePasseio(resultSet.getLong("tempo_de_passeio"));
				config.setProfissionalId(resultSet.getLong("profissional_id"));
				config.setValorPasseio(resultSet.getDouble("valor_passeio"));
				
				response.add(config);
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao obter a lista de configuracao por profissional: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public ConfiguracaoDaAgenda readById(Long id) {
		
		ConfiguracaoDaAgenda response = new ConfiguracaoDaAgenda();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		String sql = " select * from configuracao_agenda where id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				
				response.setId(resultSet.getLong("id"));
				response.setDiaSemana(resultSet.getLong("dia_semana"));
				response.setHoraInicio(resultSet.getTime("hora_inicio").toLocalTime());
				response.setHoraFinal(resultSet.getTime("hora_final").toLocalTime());
				response.setTempoDePasseio(resultSet.getLong("tempo_de_passeio"));
				response.setProfissionalId(resultSet.getLong("profissional_id"));
				response.setValorPasseio(resultSet.getDouble("valor_passeio"));
				
			}
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao obter a lista de configuracao por profissional: " + e.getMessage());
		}
		
		return response;
		
	}

	@Override
	public boolean update(ConfiguracaoDaAgenda entity) {
		
		boolean response = false;
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String sql = " update configuracao_agenda set dia_semana = ?, hora_inicio = ?, hora_final = ?, tempo_de_passeio = ?, valor_passeio = ? where id = ? ";
		
		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.setLong(1, entity.getDiaSemana());
			preparedStatement.setTime(2, Time.valueOf(entity.getHoraInicio()));
			preparedStatement.setTime(3, Time.valueOf(entity.getHoraFinal()));
			preparedStatement.setLong(4, entity.getTempoDePasseio());
			preparedStatement.setDouble(5, entity.getValorPasseio());
			preparedStatement.setLong(6, entity.getId());
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			System.out.println("Ocorreu um problema ao atualizar a configuracao da agenda: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Falha no query no momento de atualizar a configuracao da agenda {99-dogs-db-fai}: " + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public boolean delete(Long id) {
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = "DELETE FROM configuracao_agenda WHERE id = ?;";

		try {

			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();
			return true;

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao atualizar a configuracao da agenda: " + e.getMessage());
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				System.out.println("Falha no query no momento de deletar a configuracao da agenda {99-dogs-db-fai}");
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return false;
		
	}
	
}
