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
import pe.com.tss.runakuna.support.WhereParams;

@Repository
public class HistoriaLaboralJdbcRepository implements HistoriaLaboralRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
//	private final NamedParameterJdbcTemplate jdbcTemplate;
	private NamedParameterJdbcTemplate jdbcTemplate;
	
//	@Autowired
//	HistoriaLaboralJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
//		this.jdbcTemplate = jdbcTemplate;
//	}
	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	private static final String SEARCH_ALL_CARGOS = "select * from Cargo";
	
	@Override
	public List<HistoriaLaboralDto> obtenerHistoriaLaboral(Long idEmpleado) {

        WhereParams params = new WhereParams();
		String sql = buscarHistoriaLaboralPorIdEmpleado(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralDto>(HistoriaLaboralDto.class));
	}

	private String buscarHistoriaLaboralPorIdEmpleado(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		
		   sql.append("  select h.IdHistorialLaboral idHistorialLaboral, \n");
		   sql.append("  un.Nombre unidadNegocio, \n");
		   sql.append("  da.Nombre departamentoArea, \n");
		   sql.append("  p.Nombre proyecto,  \n");
		   sql.append("  c.Nombre cargo, \n");
		   sql.append("  p.IdJefe jefeInmediato,  \n");
		   sql.append("  h.FechaInicio desdeFecha,  \n");
		   sql.append("  h.FechaFin hastaFecha, \n");
		   sql.append("  h.Salario salario,  \n");
		   sql.append("  h.Descripcion descripcion  \n");
		   sql.append("  From UnidadDeNegocio un \n");
		   sql.append("  inner join Cargo c on un.IdUnidadDeNegocio = c.IdUnidadDeNegocio \n");
		   sql.append("  inner join HistorialLaboral h on c.IdCargo = h.IdCargo  \n");
		   sql.append("  inner join Proyecto p on p.IdProyecto = h.IdProyecto \n");
		   sql.append("  inner join Empleado e on e.IdEmpleado = h.IdEmpleado \n");
		   sql.append("  inner join DepartamentoArea da on da.IdDepartamentoArea = p.IdDepartamentoArea \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append(params.filter(" AND h.IdEmpleado =:idEmpleado ", idEmpleado));
		   sql.append("  ORDER BY h.FechaInicio desc");
		
		System.out.println("Query buscarHistoriaLaboralPorIdEmpleado: " + sql.toString());
		
		return sql.toString();
	}

	@Override
	public List<HistoriaLaboralDto> obtenerIdHistoriaLaboral(Long idHistorialLaboral) {
		WhereParams params = new WhereParams();
		String sql = buscarHistoriaLaboralPorIdHistoriaLaboral(idHistorialLaboral, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralDto>(HistoriaLaboralDto.class));
	}
	
	private String buscarHistoriaLaboralPorIdHistoriaLaboral(Long idHistorialLaboral, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		
		   sql.append("  select h.IdHistorialLaboral idHistorialLaboral, \n");
		   sql.append("  un.Nombre unidadNegocio, \n");
		   sql.append("  da.Nombre departamentoArea, \n");
		   sql.append("  p.Nombre proyecto,  \n");
		   sql.append("  c.Nombre cargo, \n");
		   sql.append("  h.Salario salario,  \n");
		   sql.append("  h.Descripcion descripcion,  \n");
		   sql.append("  h.FechaInicio desdeFecha,  \n");
		   sql.append("  h.FechaFin hastaFecha \n");
		   sql.append("  From UnidadDeNegocio un \n");
		   sql.append("  inner join Cargo c on un.IdUnidadDeNegocio = c.IdUnidadDeNegocio \n");
		   sql.append("  inner join HistorialLaboral h on c.IdCargo = h.IdCargo  \n");
		   sql.append("  inner join Proyecto p on p.IdProyecto = h.IdProyecto \n");
		   sql.append("  inner join Empleado e on e.IdEmpleado = h.IdEmpleado \n");
		   sql.append("  inner join DepartamentoArea da on da.IdDepartamentoArea = p.IdDepartamentoArea \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append(params.filter(" AND h.IdHistorialLaboral =:idHistorialLaboral ", idHistorialLaboral));
		
		System.out.println("Query buscarHistoriaLaboralPorIdHistoriaLaboral: " + sql.toString() + " idHistoriaLaboral: " + idHistorialLaboral);
		
		return sql.toString();
	}

	@Override
	public HistoriaLaboralDto obtenerHistoriaLaboralActual(EmpleadoDto empleado) {
		WhereParams params = new WhereParams();
		String sql = generarHistoriaLaboralActual(empleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralDto>(HistoriaLaboralDto.class));
	}
	
	private String generarHistoriaLaboralActual(EmpleadoDto empleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT TOP 1 ");
		sql.append("  h.IdHistorialLaboral AS idHistorialLaboral, ");
		sql.append("  h.FechaInicio AS fechaInicio, ");
		sql.append("  h.FechaFin AS fechaFin, ");
		sql.append("  h.IdEmpleado AS idEmpleado, ");
		sql.append("  h.IdProyecto as idProyecto, ");
		
		sql.append("  p.Nombre AS proyecto, ");
		sql.append("  un.Nombre AS unidadNegocio, ");
		sql.append("  da.Nombre as departamentoArea, ");
		
		sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE 'No Asociado'  ");
		sql.append("  END AS jefeInmediato ");
		sql.append("  FROM ");
		sql.append("  HistorialLaboral h ");
		sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");
		   
		sql.append("  WHERE (h.FechaInicio<= getdate() AND h.FechaFin>= getdate()) ");
		sql.append("  AND h.IdEmpleado="+empleado.getIdEmpleado());
				
		
		return sql.toString();
	}

	@Override
	public HistoriaLaboralDto obtenerUltimoCargo(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerUltimoCargo(idEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralDto>(HistoriaLaboralDto.class));
	}
	
	private String obtenerUltimoCargo(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdHistorialLaboral AS idHistorialLaboral,");
		sql.append("  IdEmpleado AS IdEmpleado ");
		sql.append("  FROM (select IdHistorialLaboral,IdEmpleado, row_number() over(partition by IdEmpleado order by FechaInicio desc) as rn from HistorialLaboral) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdEmpleado="+idEmpleado);
				
		
		return sql.toString();
	}

	@Override
	public Long obtenerCantidadCargos(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerCantidadCargos(idEmpleado, params);
		return calculateTotalRows(sql, params);
	}

	private String obtenerCantidadCargos(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdHistorialLaboral AS idHistorialLaboral,");
		sql.append("  IdEmpleado AS IdEmpleado ");
		sql.append("  FROM HistorialLaboral");
		sql.append("  WHERE 1 = 1 ");
		sql.append("  AND IdEmpleado="+idEmpleado);
		
		return sql.toString();
	}
	
	private long calculateTotalRows(String queryBase, WhereParams params) {
        String query = "SELECT COUNT(1) FROM (" + queryBase + ") X";
        return jdbcTemplate.queryForObject(query, params.getParams(), Long.class);
    }

	@Override
	public HistoriaLaboralDto obtenerPrimerCargo(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerPrimerCargo(idEmpleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HistoriaLaboralDto>(HistoriaLaboralDto.class));
	}
	
	private String obtenerPrimerCargo(Long idEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT IdHistorialLaboral AS idHistorialLaboral,");
		sql.append("  IdEmpleado AS IdEmpleado ");
		sql.append("  FROM (select IdHistorialLaboral,IdEmpleado, row_number() over(partition by IdEmpleado order by FechaInicio asc) as rn from HistorialLaboral) as T");
		sql.append("  WHERE rn = 1 ");
		sql.append("  AND IdEmpleado="+idEmpleado);
				
		
		return sql.toString();
	}

}
