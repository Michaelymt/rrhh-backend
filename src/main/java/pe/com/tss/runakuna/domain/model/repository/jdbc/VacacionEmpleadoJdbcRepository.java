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
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;
import pe.com.tss.runakuna.support.WhereParams;

@Repository
public class VacacionEmpleadoJdbcRepository implements VacacionEmpleadoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(VacacionEmpleadoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Override
	public VacacionEmpleadoDto obtenerDiasDisponibles(EmpleadoDto empleadoDto) {
		WhereParams params = new WhereParams();
		String sql = obtenerDiasDisponibles(empleadoDto, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoDto>(VacacionEmpleadoDto.class));
	}

	private String obtenerDiasDisponibles(EmpleadoDto empleadoDto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		   
		sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  pe.DiasVacacionesDisponibles AS diasVacacionesDisponibles ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe");
		sql.append("  WHERE (pe.FechaInicio<= getdate() AND pe.FechaFin>= getdate()) ");
		sql.append("  AND pe.IdEmpleado="+empleadoDto.getIdEmpleado());
		System.out.println("SQL obtenerDiasDisponibles: "+ sql.toString());
				
		return sql.toString();
	}

	@Override
	public VacacionEmpleadoDto obtenerPeriodo(EmpleadoDto empleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPeriodo(empleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoDto>(VacacionEmpleadoDto.class));
	}

	private String obtenerPeriodo(EmpleadoDto empleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT ");
		   
		//sql.append("  pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
		sql.append("  pe.Periodo AS periodo ");
		sql.append("  FROM ");
		sql.append("  PeriodoEmpleado pe");
		sql.append("  WHERE (pe.FechaInicio<= getdate() AND pe.FechaFin>= getdate()) ");
		sql.append("  AND pe.IdEmpleado="+empleado.getIdEmpleado());
		System.out.println("SQL obtenerDiasDisponibles: "+ sql.toString());
				
		return sql.toString();
	}

	@Override
	public Long obtenerCantidadVacaciones(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerCantidadVacaciones(idPeriodoEmpleado, params);
		return calculateTotalRows(sql, params);
	}
	
	private long calculateTotalRows(String queryBase, WhereParams params) {
        String query = "SELECT COUNT(1) FROM (" + queryBase + ") X";
        return jdbcTemplate.queryForObject(query, params.getParams(), Long.class);
    }

	private String obtenerCantidadVacaciones(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdVacacion AS idVacacion,");
		sql.append("  IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM Vacacion");
		sql.append("  WHERE 1 = 1 ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
		
		return sql.toString();
	}

	@Override
	public VacacionEmpleadoDto obtenerPrimeraVacacion(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPrimeraVacacion(idPeriodoEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoDto>(VacacionEmpleadoDto.class));
	}

	private String obtenerPrimeraVacacion(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdVacacion AS idVacacion,");
		sql.append("  IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM (select IdVacacion,IdPeriodoEmpleado, row_number() over(partition by IdPeriodoEmpleado order by FechaInicio asc) as rn from Vacacion) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public VacacionEmpleadoDto obtenerUltimaVacacion(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerUltimaVacacion(idPeriodoEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoDto>(VacacionEmpleadoDto.class));
	}

	private String obtenerUltimaVacacion(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdVacacion AS idVacacion,");
		sql.append("  IdPeriodoEmpleado AS idPeriodoEmpleado ");
		sql.append("  FROM (select IdVacacion,IdPeriodoEmpleado, row_number() over(partition by IdPeriodoEmpleado order by FechaInicio desc) as rn from Vacacion) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public List<VacacionEmpleadoDto> allListVacacion(Long idPeriodoEmpleado) {
		WhereParams params = new WhereParams();
		String sql = allListVacacion(idPeriodoEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<VacacionEmpleadoDto>(VacacionEmpleadoDto.class));
	}

	private String allListVacacion(Long idPeriodoEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from Vacacion  ");
		sql.append("  WHERE 1 = 1 AND Estado != 'R' ");
		sql.append("  AND IdPeriodoEmpleado="+idPeriodoEmpleado);
		return sql.toString();
	}

}
