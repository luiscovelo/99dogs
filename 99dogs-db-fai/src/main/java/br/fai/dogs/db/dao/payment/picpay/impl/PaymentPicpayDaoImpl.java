package br.fai.dogs.db.dao.payment.picpay.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import br.fai.dogs.db.connection.ConnectionFactory;
import br.fai.dogs.db.dao.payment.picpay.PaymentPicpayDao;
import br.fai.dogs.model.dto.picpay.PicpayResponse;
import br.fai.dogs.model.entities.TransacaoPicpay;

@Repository
public class PaymentPicpayDaoImpl implements PaymentPicpayDao {

	@Override
	public boolean create(PicpayResponse entity) {
		
		boolean response = false;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String sql = " insert into transacao_picpay(reference_id, payment_url, expires_at, qrcode_content, qrcode_base64, passeio_id) "
				+ " values(?,?,?,?,?,?) ";

		try {
			
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, entity.getReferenceId());
			preparedStatement.setString(2, entity.getPaymentUrl());
			preparedStatement.setString(3, entity.getExpiresAt());
			preparedStatement.setString(4, entity.getPicpayQrcode().getContent());
			preparedStatement.setString(5, entity.getPicpayQrcode().getBase64());
			preparedStatement.setLong(6, entity.getPasseioId());
			
			preparedStatement.execute();
			
			connection.commit();
			
			response = true;
			
		} catch (Exception e) {
			
			System.out.println("Exception: " + e.getMessage());
			
			try {
				System.out.println("Problema ao conectar ou preparar o sql de create picpay-transacao: " + e.getMessage());
				connection.rollback();
			} catch (SQLException e1) {
				System.out.println("Problema no sql do create pic-transacao" + e1.getMessage());
			}
			
		} finally {
			ConnectionFactory.close(preparedStatement, connection);
		}
		
		return response;
		
	}

	@Override
	public List<TransacaoPicpay> readByPasseioId(Long id) {
		
		List<TransacaoPicpay> transacoes = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			String sql = " select * from transacao_picpay where passeio_id = ? ";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setLong(1, id);
			
			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				
				TransacaoPicpay transacao = new TransacaoPicpay();
				
				transacao.setId(resultSet.getLong("id"));
				transacao.setPaymentUrl(resultSet.getString("payment_url"));
				transacao.setReferenceId(resultSet.getString("reference_id"));
				transacao.setExpiresAt(resultSet.getString("expires_at"));
				transacao.setQrcodeContent(resultSet.getString("qrcode_content"));
				transacao.setQrcodeBase64(resultSet.getString("qrcode_base64"));
				
				transacoes.add(transacao);
				
			}

		} catch (Exception e) {
			
			System.out.println("Ocorreu um problema ao requisitar as transações {99dogs-db-fai}: " + e.getMessage());
			
		} finally {

			ConnectionFactory.close(resultSet, preparedStatement, connection);

		}

		return transacoes;
		
	}
	
}
