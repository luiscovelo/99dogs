package br.fai.dogs.email;

import java.io.File;
import java.nio.file.Files;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import br.fai.dogs.helper.Helper;
import br.fai.dogs.model.entities.Passeio;

@Service
public class Mailtrap implements MailStrategy {

	private HtmlEmail mail;

	private static String HOST      = "";
	private static int PORT         = 0;
	private static String USERNAME  = "";
	private static String PASSWORD  = "";
	
	@Value("${MAILTRAP_HOST}")
	private void setHost(String host) {
		Mailtrap.HOST = host;
	}
	
	@Value("${MAILTRAP_PORT}")
	private void setPort(int port) {
		Mailtrap.PORT = port;
	}
	
	@Value("${MAILTRAP_USERNAME}")
	private void setUsername(String username) {
		Mailtrap.USERNAME = username;
	}
	
	@Value("${MAILTRAP_PASSWORD}")
	private void setPassword(String password) {
		Mailtrap.PASSWORD = password;
	}
	
	public HtmlEmail conexao() {
		
		HtmlEmail htmlMail = new HtmlEmail();
		
		htmlMail.setHostName(HOST);
		htmlMail.setSmtpPort(PORT);
		htmlMail.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
		
		return htmlMail;
		
	}
	
	@Override
	public void passeioSolicitado(Passeio entity) {

		try {
			
			mail = conexao();

			File fileTemplatePasseioSolicitado = new ClassPathResource("templates/email/passeio-solicitado.html")
					.getFile();
			String content = new String(Files.readAllBytes(fileTemplatePasseioSolicitado.toPath()));

			content = content.replace("${profissional}", entity.getProfissional().getPessoa().getNome());
			content = content.replace("${nome_cliente}", entity.getCliente().getPessoa().getNome());
			content = content.replace("${passeio_id}", "#" + entity.getId());
			content = content.replace("${data_passeio}", Helper.tratarDataHora(entity.getDatahora()));
			content = content.replace("${passeio_link}",
					"http://localhost:8081/passeio/profissional/detalhes/" + entity.getId());

			mail.setFrom("noreply@99dogs.com.br", "99Dogs");
			mail.addTo(entity.getProfissional().getPessoa().getEmail(), entity.getProfissional().getPessoa().getNome());
			mail.setSubject("Novo passeio solicitado.");
			mail.setHtmlMsg(content);
			mail.send();
			
		} catch (Exception e) {
			System.err.println("Ocorreu um problema ao enviar o email de passeio solicitado: " + e.getMessage());
		}

	}

	@Override
	public void passeioAprovado(Passeio entity) {
				
		try {
			
			mail = conexao();
			
			File fileTemplatePasseioSolicitado = new ClassPathResource("templates/email/passeio-aprovado.html")
					.getFile();
			String content = new String(Files.readAllBytes(fileTemplatePasseioSolicitado.toPath()));

			content = content.replace("${profissional}", entity.getProfissional().getPessoa().getNome());
			content = content.replace("${nome_cliente}", entity.getCliente().getPessoa().getNome());
			content = content.replace("${passeio_id}", "#" + entity.getId());
			content = content.replace("${data_passeio}", Helper.tratarDataHora(entity.getDatahora()));
			content = content.replace("${passeio_link}",
					"http://localhost:8081/passeio/cliente/detalhes/" + entity.getId());

			mail.setFrom("noreply@99dogs.com.br", "99Dogs");
			mail.addTo(entity.getCliente().getPessoa().getEmail(), entity.getCliente().getPessoa().getNome());
			mail.setSubject("Seu passeio #" + entity.getId() + " foi aprovado.");
			mail.setHtmlMsg(content);
			mail.send();
			
			
		} catch (Exception e) {
			System.err.println("Ocorreu um problema ao enviar o email de passeio aprovado: " + e.getMessage());
		}

	}

	@Override
	public void passeioRecusado(Passeio entity) {
		
		try {

			mail = conexao();

			File fileTemplatePasseioSolicitado = new ClassPathResource("templates/email/passeio-recusado.html")
					.getFile();
			String content = new String(Files.readAllBytes(fileTemplatePasseioSolicitado.toPath()));

			content = content.replace("${profissional}", entity.getProfissional().getPessoa().getNome());
			content = content.replace("${nome_cliente}", entity.getCliente().getPessoa().getNome());
			content = content.replace("${passeio_id}", "#" + entity.getId());
			content = content.replace("${data_passeio}", Helper.tratarDataHora(entity.getDatahora()));
			content = content.replace("${passeio_link}",
					"http://localhost:8081/passeio/cliente/detalhes/" + entity.getId());

			mail.setFrom("noreply@99dogs.com.br", "99Dogs");
			mail.addTo(entity.getCliente().getPessoa().getEmail(), entity.getCliente().getPessoa().getNome());
			mail.setSubject("Seu passeio #" + entity.getId() + " foi recusado.");
			mail.setHtmlMsg(content);
			mail.send();

		} catch (Exception e) {
			System.err.println("Ocorreu um problema ao enviar o email de passeio recusado: " + e.getMessage());
		}

	}

	@Override
	public void passeioFinalizado(Passeio entity) {
		
		try {

			mail = conexao();

			File fileTemplatePasseioSolicitado = new ClassPathResource("templates/email/passeio-finalizado.html")
					.getFile();
			String content = new String(Files.readAllBytes(fileTemplatePasseioSolicitado.toPath()));

			content = content.replace("${profissional}", entity.getProfissional().getPessoa().getNome());
			content = content.replace("${nome_cliente}", entity.getCliente().getPessoa().getNome());
			content = content.replace("${passeio_id}", "#" + entity.getId());
			content = content.replace("${data_passeio}", Helper.tratarDataHora(entity.getDatahora()));
			content = content.replace("${data_finalizacao}", Helper.tratarDataHora(entity.getDatahorafinalizacao()));
			content = content.replace("${passeio_link}",
					"http://localhost:8081/passeio/cliente/detalhes/" + entity.getId());

			mail.setFrom("noreply@99dogs.com.br", "99Dogs");
			mail.addTo(entity.getCliente().getPessoa().getEmail(), entity.getCliente().getPessoa().getNome());
			mail.setSubject("Seu passeio #" + entity.getId() + " foi finalizado com sucesso.");
			mail.setHtmlMsg(content);
			mail.send();

		} catch (Exception e) {
			System.err.println("Ocorreu um problema ao enviar o email de passeio finalizado: " + e.getMessage());
		}

	}
	
}
