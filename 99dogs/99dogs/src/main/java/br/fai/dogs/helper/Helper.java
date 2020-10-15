package br.fai.dogs.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import javax.servlet.http.HttpSession;

public class Helper {
		
	private static final Locale BRAZIL = new Locale("pt","BR");
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);
	
	public static String getDataAtual(String mascara) {
		
		Date data = new Date(System.currentTimeMillis()); 
		SimpleDateFormat formatarDate = new SimpleDateFormat(mascara);
		
		return formatarDate.format(data);
		
	}
	
	public static String tratarMoeda(Double valor) {
		
		String valorFormatado = DINHEIRO_REAL.format(valor);
		
		return valorFormatado;
		
	}
	
	public static String tratarDataHora(LocalDateTime datahora) {
		
		String dataFormatada = "";
		
		try {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"); 
			dataFormatada = datahora.format(formatter);
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
			dataFormatada = datahora.toString();
		}
		
		return dataFormatada;
		
	}
	
	public static String getUserTokenJwt(HttpSession session) {
				
		return (String) session.getAttribute("token");
		
	}
	
}
