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

import pe.com.tss.runakuna.dto.BusquedaLicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.LicenciaEmpleadoDto;
import pe.com.tss.runakuna.support.WhereParams;

@Repository
public class LicenciaEmpleadoJdbcRepository implements LicenciaEmpleadoRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(LicenciaEmpleadoJdbcRepository.class);

	@Autowired
	DataSource dataSource;
	
	private NamedParameterJdbcTemplate jdbcTemplate;
	

	@PostConstruct
	public void init() {
		jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}


	@Override
	public List<LicenciaEmpleadoDto> obtenerLicencias(BusquedaLicenciaEmpleadoDto busquedaLicenciaEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarObtenerLicencias(busquedaLicenciaEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<LicenciaEmpleadoDto>(LicenciaEmpleadoDto.class));
	}


	private String generarObtenerLicencias(BusquedaLicenciaEmpleadoDto busquedaLicenciaEmpleadoDto,
			WhereParams params) {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select li.IdLicencia, li.Comentario as comentario, li.DiaEntero as diaEntero, li.HoraInicio as horaInicio, li.HoraFin as horaFin, ");
		sql.append(" CONCAT(e.ApellidoPaterno, ' ', e.ApellidoMaterno, ', ', e.Nombre) as nombreEmpleado, ");
		sql.append(" PROY.Nombre as nombreProyecto,  ");
		sql.append(" DEP.Nombre as nombreDepartamento, ");
        sql.append(" UN.Nombre as nombreUnidadNegocio, ");
        sql.append(" li.FechaInicio as fechaInicio, li.FechaFin as fechaFin, li.Dias as dias , CASE WHEN (li.Estado='P') THEN 'PENDIENTE' "
        		+ " ELSE 'APROBADO' END as estado");
        
        sql.append(" from Licencia li");
        sql.append(" LEFT JOIN TipoLicencia tl ON li.idTipoLicencia=tl.idTipoLicencia ");
        sql.append(" LEFT JOIN Empleado e ON e.idEmpleado=li.idEmpleado ");
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado  AND ((HISTORIAL.FechaInicio<=getdate() AND HISTORIAL.FechaFin>=getdate()) OR (HISTORIAL.FechaInicio<=getdate() AND HISTORIAL.FechaFin IS NULL)) ");
        sql.append(" LEFT JOIN PeriodoEmpleado periodo ON periodo.IdEmpleado = e.IdEmpleado  AND (periodo.FechaInicio<=getdate() AND periodo.FechaFin>=getdate()) ");
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = PROY.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = DEP.IdUnidadDeNegocio ");
        sql.append(" where 1=1 ");
        sql.append(params.filterDateDesde_US(" AND li.FechaInicio = : fechaInicio " , busquedaLicenciaEmpleadoDto.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND li.FechaFin = :fechaFin ", busquedaLicenciaEmpleadoDto.getFechaFin()));
        sql.append(params.filter(" AND li.idTipoLicencia = :idTipoLicencia ", busquedaLicenciaEmpleadoDto.getIdTipoLicencia()));
        sql.append(params.filter(" AND li.estado= :estado ", busquedaLicenciaEmpleadoDto.getEstado()));
        
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :idUnidadNegocio ", busquedaLicenciaEmpleadoDto.getIdUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :idDepartamentoArea ", busquedaLicenciaEmpleadoDto.getIdDepartamentoArea()));
        sql.append(params.filter(" AND PROY.IdProyecto = :idProyecto ", busquedaLicenciaEmpleadoDto.getIdProyecto()));
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND periodo.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND li.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND HISTORIAL.IdEmpleado = :idEmpleado ", busquedaLicenciaEmpleadoDto.getIdEmpleado()));
        sql.append(params.filter(" AND PROY.IdJefe = :idJefe ", busquedaLicenciaEmpleadoDto.getIdJefe()));
        
        /*sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefe)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefe)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefe) ) ", busquedaLicenciaEmpleadoDto.getIdJefe()));
        		*/
        
       
        
        
		return sql.toString();
	}
	



	
	
	

}
