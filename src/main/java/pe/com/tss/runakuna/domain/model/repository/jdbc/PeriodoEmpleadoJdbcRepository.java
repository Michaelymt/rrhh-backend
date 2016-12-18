package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.support.WhereParams;

@Repository
public class PeriodoEmpleadoJdbcRepository implements PeriodoEmpleadoRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public PeriodoEmpleadoDto obtenerPeriodoEmpleadoActual(EmpleadoDto empleado) {
		WhereParams params = new WhereParams();
		String sql = generarPeriodoEmpleadoActual(empleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoDto>(PeriodoEmpleadoDto.class));
	}
	
	private String generarPeriodoEmpleadoActual(EmpleadoDto empleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		   
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  pe.FechaInicio AS fechaInicio, ");
		sql.append("  pe.FechaFin AS fechaFin, ");
		sql.append("  pe.Periodo AS periodo ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe ");
		sql.append("  WHERE (pe.FechaInicio<= getdate() AND pe.FechaFin>= getdate()) ");
		sql.append("  AND pe.IdEmpleado="+empleado.getIdEmpleado());
				
		return sql.toString();
	}

}
