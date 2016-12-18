package pe.com.tss.runakuna.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.dozer.Mapper;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.dto.BusquedaHorasExtraEmpleadoDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HorasExtraDto;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.support.WhereParams;

@Service
public class DarBajaJobImpl {
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	DataSource dataSource;

	private NamedParameterJdbcTemplate jdbcTemplate;

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date todayDate;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date daysAgo;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	Date dateBefore1Day;
	
	
	/*
	 * 0 0 12 * * ?	Fire at 12pm (noon) every day
	 * 0 0 12 1/1 * ? *
	 * 0 0 12 ? * MON-FRI *
	 * 0 15 10 ? * *	Fire at 10:15am every day
	 * 
	 * 
	 */
	@Scheduled(cron="0 0/1 * 1/1 * ?")
	public void darBajaEmpleado(){
		
		List<Empleado> listEntity = new ArrayList<>();
		List<EmpleadoDto> lista = new ArrayList<>();
		
		
		todayDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(todayDate);
		cal.add(Calendar.DATE, -1);
		dateBefore1Day = cal.getTime();
		
		daysAgo = new DateTime(todayDate).minusDays(1).toDate();
			
		List<EmpleadoDto> listFechaCese = getVal(dateBefore1Day);
//		List<Empleado> asss2 = getVal(daysAgo);
		
		if(listFechaCese!= null){
			for(EmpleadoDto next: listFechaCese){
				Empleado entity = new Empleado();
				
				entity = empleadoJpaRepository.findOne(next.getIdEmpleado());
				entity.setEstado("I");
				entity = empleadoJpaRepository.save(entity);
				empleadoJpaRepository.flush();
				System.out.println(">>>> Se cambio el estado a Inactivo: "+ next.getIdEmpleado()+" "+next.getNombre());
			}
		}
		
		
	}
	
	private List<EmpleadoDto> getVal(Date fecha) {
		WhereParams params = new WhereParams();
        String sql = getFechaCeseEmpleado(fecha, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(EmpleadoDto.class));
	}

	private String getFechaCeseEmpleado(Date fecha, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT * FROM Empleado he WHERE he.Estado = 'A' ");
        sql.append(params.filterDate_US(" AND he.FechaCese " , fecha));
        return sql.toString();
	}

}
