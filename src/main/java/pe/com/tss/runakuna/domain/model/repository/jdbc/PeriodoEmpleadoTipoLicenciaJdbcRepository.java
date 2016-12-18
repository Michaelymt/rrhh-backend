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

import pe.com.tss.runakuna.dto.PeriodoEmpleadoTipoLicenciaDto;
import pe.com.tss.runakuna.support.WhereParams;

@Repository
public class PeriodoEmpleadoTipoLicenciaJdbcRepository implements PeriodoEmpleadoTipoLicenciaRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PeriodoEmpleadoTipoLicenciaRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	
	@Override
	public List<PeriodoEmpleadoTipoLicenciaDto> obtenerDiasPorPeriodoEmpleadoTipoLicencia(
			PeriodoEmpleadoTipoLicenciaDto periodoEmpleadoTipoLicenciaDto) {
		WhereParams params = new WhereParams();
		String sql = generarDiasPeriodoEmpleadoTipoLicencia(periodoEmpleadoTipoLicenciaDto, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<PeriodoEmpleadoTipoLicenciaDto>(PeriodoEmpleadoTipoLicenciaDto.class));
	}

	private String generarDiasPeriodoEmpleadoTipoLicencia(PeriodoEmpleadoTipoLicenciaDto periodoEmpleadoTipoLicenciaDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT pe.IdPeriodoEmpleadoTipoLicencia as idPeriodoEmpleadoTipoLicencia, ");
		   
		sql.append("  CASE WHEN (pe.DiasLicencia IS NULL) THEN 0 ELSE pe.DiasLicencia END AS dias ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleadoTipoLicencia pe ");
		sql.append("  WHERE pe.IdPeriodoEmpleado= "+periodoEmpleadoTipoLicenciaDto.getIdPeriodoEmpleado());
		sql.append("  AND pe.IdTipoLicencia= "+periodoEmpleadoTipoLicenciaDto.getIdTipoLicencia());
				
		return sql.toString();
	}

}
