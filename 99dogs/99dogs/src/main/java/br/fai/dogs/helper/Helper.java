package br.fai.dogs.helper;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Helper {
	
	private static final Locale BRAZIL = new Locale("pt","BR");
	private static final DecimalFormatSymbols REAL = new DecimalFormatSymbols(BRAZIL);
	public static final DecimalFormat DINHEIRO_REAL = new DecimalFormat("¤ ###,###,##0.00",REAL);
	
	public static String getPrimeiroDiaDoMes() {
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 0);
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		return sdf.format(c.getTime());
		
	}
	
	public static String tratarMoeda(Double valor) {
		
		String valorFormatado = DINHEIRO_REAL.format(valor);
		
		return valorFormatado;
		
	}
	
}
