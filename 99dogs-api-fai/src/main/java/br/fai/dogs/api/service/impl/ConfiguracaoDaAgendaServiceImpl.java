package br.fai.dogs.api.service.impl;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.fai.dogs.api.service.ConfiguracaoDaAgendaService;
import br.fai.dogs.db.dao.ConfiguracaoDaAgendaDao;
import br.fai.dogs.model.entities.ConfiguracaoDaAgenda;

@Service
public class ConfiguracaoDaAgendaServiceImpl implements ConfiguracaoDaAgendaService {
	
	@Autowired
	private ConfiguracaoDaAgendaDao configuracaoDaAgendaDao;
	
	@Override
	public boolean create(ConfiguracaoDaAgenda entity) {
		
		boolean response = configuracaoDaAgendaDao.create(entity);
		return response;
		
	}

	@Override
	public List<ConfiguracaoDaAgenda> readByProfissionalId(Long id) {
		
		List<ConfiguracaoDaAgenda> response = configuracaoDaAgendaDao.readByProfissionalId(id);
		return response;
		
	}

	@Override
	public ConfiguracaoDaAgenda readById(Long id) {
		
		ConfiguracaoDaAgenda response = configuracaoDaAgendaDao.readById(id);
		return response;
		
	}

	@Override
	public boolean delete(Long id) {
		
		boolean response = configuracaoDaAgendaDao.delete(id);
		return response;
		
	}
	
	public Map<String,String> horariosDisponiveisPorData(String data, Long id){
		
		Map<String, String> horarios = new HashMap<>();
		List<ConfiguracaoDaAgenda> config = readByProfissionalId(id);
		
		try {
			
			LocalDate dataSelecionada = LocalDate.parse(data);
			Calendar dataAtual  = new GregorianCalendar();
			
			Calendar calendario = new GregorianCalendar(dataSelecionada.getYear(), dataSelecionada.getMonthValue() -1, dataSelecionada.getDayOfMonth());
		    int diaSemana = calendario.get(Calendar.DAY_OF_WEEK);
		    
		    if(calendario.before(dataAtual)) {
		    	return horarios;
		    }
		    
		    String hora;
		    
		    for(ConfiguracaoDaAgenda horario: config) {
		    			    	
		    	if(diaSemana == horario.getDiaSemana()) {
		    		
		    		int limiter = horario.getHoraFinal().getHour() - horario.getHoraInicio().getHour();
		    		
		    		for (int i = 0; i <= limiter; i++) {
						
		    			int horaSomada = horario.getHoraInicio().getHour() + i;
		    			
		    			if(horaSomada<10) {
		    				hora = "0" + horaSomada + ":00";
		    			}else {
		    				hora = horaSomada + ":00";
		    			}
		    			
		    			horarios.put(hora, horario.getValorPasseio().toString());
		    			
					}
		    		
		    	}
		    	
		    }
		    
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return horarios;
		
	}

	@Override
	public boolean update(ConfiguracaoDaAgenda entity) {
		
		boolean response = configuracaoDaAgendaDao.update(entity);
		return response;
		
	}
	
}
