package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.dto.BusquedaEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaMarcacionDto;
import pe.com.tss.runakuna.dto.BusquedaHorasExtraEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaPermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaVacacionesEmpleadoDto;
import pe.com.tss.runakuna.dto.DependienteDto;
import pe.com.tss.runakuna.dto.EducacionDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.EquipoEntregadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDiaDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDto;
import pe.com.tss.runakuna.dto.HorasExtraDto;
import pe.com.tss.runakuna.dto.LicenciaDto;
import pe.com.tss.runakuna.dto.MarcacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.VacacionDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;
import pe.com.tss.runakuna.support.WhereParams;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.List;

/**
 * Created by josediaz on 2/11/2016.
 */

@Repository
public class EmpleadoJdbcRepository implements EmpleadoRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataSource dataSource;

    private NamedParameterJdbcTemplate jdbcTemplate;

    @PostConstruct
    public void init() {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<EmpleadoDto> busquedaEmpleado(BusquedaEmpleadoDto busquedaEmpleadoDto) {

        WhereParams params = new WhereParams();
        String sql = generarBusquedaEmpleado(busquedaEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<EmpleadoDto>(EmpleadoDto.class));
    }
    
    @Override
    public EmpleadoDto verEmpleado(EmpleadoDto empleadoDto) {

        WhereParams params = new WhereParams();
        String sql = generarVerEmpleado(empleadoDto, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<EmpleadoDto>(EmpleadoDto.class));
    }
    
    @Override
    public List<EducacionDto> verEducacion(EmpleadoDto empleadoDto) {

        WhereParams params = new WhereParams();
        String sql = generarVerEducacion(empleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<EducacionDto>(EducacionDto.class));
    }
    
	@Override
	public List<EquipoEntregadoDto> verEquipoEntregado(EmpleadoDto empleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerEquipoEntregado(empleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<EquipoEntregadoDto>(EquipoEntregadoDto.class));
	}
	
	@Override
	public List<DependienteDto> verDependiente(EmpleadoDto empleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerDependiente(empleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<DependienteDto>(DependienteDto.class));
	}
	
	@Override
	public List<LicenciaDto> verLicencia(PeriodoEmpleadoDto periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerLicencia(periodoEmpleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<LicenciaDto>(LicenciaDto.class));
	}
	
	@Override
	public HorarioEmpleadoDto verHorarioEmpleado(EmpleadoDto empleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorarioEmpleado(empleadoDto, params);
                
        HorarioEmpleadoDto horario = jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoDto>(HorarioEmpleadoDto.class));
        
        return horario;
        
	}
	
	@Override
	public List<HorarioEmpleadoDto> verHorariosEmpleado(EmpleadoDto empleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorariosEmpleado(empleadoDto, params);
                
        List<HorarioEmpleadoDto> horarios = jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoDto>(HorarioEmpleadoDto.class));
        
        return horarios;
        
	}
		
	@Override
	public List<PeriodoEmpleadoDto> verPeriodoEmpleado(EmpleadoDto empleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerPermisosPermitidos(empleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PeriodoEmpleadoDto>(PeriodoEmpleadoDto.class));
        
    }
	
	@Override
	public List<HorarioEmpleadoDiaDto> verHorarioEmpleadoDia(HorarioEmpleadoDto horarioEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarVerHorarioEmpleadoDia(horarioEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<HorarioEmpleadoDiaDto>(HorarioEmpleadoDiaDto.class));
    
	}
	
	@Override
	public List<PermisoEmpleadoDto> verPermisoEmpleado(PeriodoEmpleadoDto periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerPermisosEmpleado(periodoEmpleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<PermisoEmpleadoDto>(PermisoEmpleadoDto.class));
        
	}
	
	@Override
	public List<VacacionDto> verVacaciones(PeriodoEmpleadoDto periodoEmpleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerVacaciones(periodoEmpleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<VacacionDto>(VacacionDto.class));
        
	}
	
	@Override
	public List<MarcacionDto> verMarcaciones(EmpleadoDto empleado) {
		WhereParams params = new WhereParams();
        String sql = generarVerMarcaciones(empleado, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<MarcacionDto>(MarcacionDto.class));
        
	}
	
	
	public List<VacacionEmpleadoDto> busquedaVacacionesEmpleado(BusquedaVacacionesEmpleadoDto busquedaVacacionesEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaVacacionesEmpleado(busquedaVacacionesEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(VacacionEmpleadoDto.class));
	}

    private String generarBusquedaVacacionesEmpleado(BusquedaVacacionesEmpleadoDto busquedaVacacionesEmpleadoDto, WhereParams params) {
		
    	StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct va.IdVacacion AS idVacacion, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" va.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" va.FechaInicio as fechaInicio, ");
        sql.append(" va.FechaFin as fechaFin, ");
        sql.append(" va.DiasCalendarios as diasCalendarios, ") ;
        sql.append(" va.DiasHabiles as diasHabiles, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" va.Estado AS estado, ");
        sql.append(" ESTADO.Nombre AS estadoString ") ;
        sql.append(" from Vacacion va ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON va.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON va.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON va.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
                
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND va.Estado = :estado ", busquedaVacacionesEmpleadoDto.getEstado()));
        sql.append(params.filterDateDesde_US(" AND va.FechaInicio  " , busquedaVacacionesEmpleadoDto.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND va.FechaFin  ", busquedaVacacionesEmpleadoDto.getFechaFin()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaVacacionesEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaVacacionesEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaVacacionesEmpleadoDto.getProyecto()));
        sql.append(params.filter(" AND va.IdPermisoEmpleado = :codigoPermiso ", busquedaVacacionesEmpleadoDto.getCodigoPermiso()));
        
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaVacacionesEmpleadoDto.getIdEmpleado()));
        
        sql.append(params.filter(" AND va.IdAtendidoPor = :idEmpleado ", busquedaVacacionesEmpleadoDto.getIdJefeInmediato()));
        
        /*sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaVacacionesEmpleadoDto.getIdJefeInmediato()));
        */
        return sql.toString();
	}

	@Override
    public List<PermisoEmpleadoDto> busquedaPermisoEmpleado(BusquedaPermisoEmpleadoDto busquedaPermisoEmpleadoDto) {
        WhereParams params = new WhereParams();
        String sql = generarBusquedaPermisoEmpleado(busquedaPermisoEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(PermisoEmpleadoDto.class));
    }
	
	@Override
    public List<MarcacionDto> busquedaMarcacionEmpleado(BusquedaMarcacionDto busquedaMarcacionDto) {
        WhereParams params = new WhereParams();
        String sql = generarVerBusquedaMarcacionesEmpleado(busquedaMarcacionDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDto.class));
    }
    
    @Override
    public List<BusquedaPermisoEmpleadoDto> autocompleteEmpleado(String search) {
        WhereParams params = new WhereParams();
        String sql = generarAutocompleteEmpleado(search, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(BusquedaPermisoEmpleadoDto.class));
    }

    private String generarAutocompleteEmpleado(String search, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select ");
        sql.append(" EMPLEADO.IdEmpleado as idEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado ");
        sql.append(" from  Empleado EMPLEADO ");
                
        sql.append(" where (CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre)) like '%"+search+"%' ");


        return sql.toString();
    }
    
    private String generarBusquedaPermisoEmpleado(BusquedaPermisoEmpleadoDto busquedaPermisoEmpleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct pe.IdPermisoEmpleado as idPermisoEmpleado, ");
        sql.append(" EMPLEADO.IdEmpleado as idEmpleado, ");
        sql.append(" pe.IdPeriodoEmpleado as idPeriodoEmpleado, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" pe.IdAtendidoPor as idAtendidoPor, ");
        sql.append(" MOTIVO.nombre as motivo, ");
        sql.append(" pe.Razon as razon, ") ;
        sql.append(" pe.Fecha as fecha, ") ;
        sql.append(" pe.HoraInicio as horaInicio, ") ;
        sql.append(" pe.HoraFin as horaFin, ") ;
        sql.append(" pe.Horas as horas, ") ;
        sql.append(" pe.FechaRecuperacion as fechaRecuperacion, ") ;
        sql.append(" pe.HoraInicioRecuperacion as horaInicioRecuperacion, ") ;
        sql.append(" pe.HoraFinRecuperacion as horaFinRecuperacion, ") ;
        sql.append(" pe.Estado  as estado , ESTADO.Nombre as estadoString  ") ;
        
        sql.append(" from PermisoEmpleado pe ") ;
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON pe.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN TablaGeneral MOTIVO ON pe.MOTIVO=MOTIVO.Codigo and MOTIVO.GRUPO='Permiso.Motivo'");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO_EMPLEADO ON pe.IdPeriodoEmpleado = PERIODO_EMPLEADO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON PERIODO_EMPLEADO.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMPLEADO.IdEmpleado AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
                
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND pe.Estado = :estado ", busquedaPermisoEmpleadoDto.getEstado()));
        sql.append(params.filterDateDesde_US(" AND pe.Fecha  " , busquedaPermisoEmpleadoDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND pe.Fecha  ", busquedaPermisoEmpleadoDto.getHasta()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaPermisoEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaPermisoEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaPermisoEmpleadoDto.getProyecto()));
        sql.append(params.filter(" AND pe.IdPermisoEmpleado = :codigoPermiso ", busquedaPermisoEmpleadoDto.getCodigoPermiso()));
        
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaPermisoEmpleadoDto.getIdEmpleado()));
        
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaPermisoEmpleadoDto.getIdJefeInmediato()));
        
        return sql.toString();
    }

	private String generarVerPermisosPermitidos(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" pe.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" pe.Periodo AS periodo, ");
        sql.append(" pe.FechaInicio AS fechaInicio, ");
        sql.append(" pe.FechaFin AS fechaFin, ");
        sql.append(" pe.MaxPermisos AS permisosPermitidos, ");
        sql.append(" (SELECT count(*) FROM PermisoEmpleado LEFT JOIN PeriodoEmpleado ON PeriodoEmpleado.IdPeriodoEmpleado = pe.IdPeriodoEmpleado) AS permisosUsados ");
        sql.append(" FROM "); 
        sql.append(" PeriodoEmpleado pe ");
        sql.append(" where pe.IdEmpleado = "+empleadoDto.getIdEmpleado());
        sql.append(" order by pe.IdPeriodoEmpleado desc ");

        return sql.toString();
    }
	
	private String generarVerPermisosEmpleado(PeriodoEmpleadoDto periodoEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" PE.IdPermisoEmpleado AS idPermisoEmpleado, ");
        sql.append(" PE.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" PERM.Nombre AS nombreMotivo, ");
        sql.append(" PERIODO.Periodo AS periodo, ");
        sql.append(" PE.Motivo AS motivo, ");
        sql.append(" PE.Razon AS razon, ");
        sql.append(" PE.Fecha AS fecha, ");
        sql.append(" PE.HoraInicio AS horaInicio, ");
        sql.append(" PE.HoraFin AS horaFin, ");
        sql.append(" PE.FechaRecuperacion AS fechaRecuperacion, ");
        sql.append(" PE.HoraInicioRecuperacion AS horaInicioRecuperacion, ");
        sql.append(" PE.HoraFinRecuperacion AS horaFinRecuperacion, ");
        sql.append(" PE.Horas AS horas, ");
        sql.append(" PE.Estado AS estado, ");
        sql.append(" ESTPERM.Nombre AS nombreEstado, ");
        
        sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE ' '  ");
		sql.append("  END AS jefeInmediato ");
        
        sql.append(" FROM PermisoEmpleado PE ");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PE.IdPeriodoEmpleado = PERIODO.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = PE.IdAtendidoPor ");
        sql.append(" LEFT JOIN TablaGeneral PERM ON PERM.Codigo=PE.Motivo AND PERM.Grupo = 'Permiso.Motivo' ");
        sql.append(" LEFT JOIN TablaGeneral ESTPERM ON ESTPERM.Codigo=PE.Estado AND ESTPERM.Grupo = 'Permiso.Estado' ");
        
        sql.append(" LEFT JOIN HistorialLaboral h ON h.IdEmpleado = EMP.IdEmpleado AND (h.FechaInicio<=getdate() AND h.FechaFin>=getdate()) ");
        
        sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");

        sql.append(" where PERIODO.IdEmpleado = "+periodoEmpleado.getIdEmpleado());
        sql.append(params.filter(" AND PE.IdPeriodoEmpleado = :idPeriodoEmpleado ", periodoEmpleado.getIdPeriodoEmpleado()));
        sql.append(" order by PE.IdPermisoEmpleado desc ");

        return sql.toString();
    }
	
	private String generarVerVacaciones(PeriodoEmpleadoDto periodoEmpleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT "); 
        sql.append(" VAC.IdVacacion AS idVacacion, ");
        sql.append(" PERIODO.IdPeriodoEmpleado AS idPeriodoEmpleado, ");
        sql.append(" PERIODO.Periodo AS periodo, ");
        sql.append(" EMP.IdEmpleado AS idEmpleado, ");
        sql.append(" VAC.FechaInicio AS fechaInicio, ");
        sql.append(" VAC.FechaFin AS fechaFin, ");
        sql.append(" ESTVAC.Nombre AS nombreEstado, ");
        sql.append(" VAC.Estado AS Estado, ");
        
        sql.append(" VAC.DiasCalendarios AS diasCalendarios, ");
        sql.append(" VAC.DiasHabiles AS diasHabiles, ");
        
        sql.append("  CASE ");   
		sql.append("  WHEN (h.IdProyecto IS NOT NULL) THEN ep.Nombre+' '+ep.ApellidoPaterno +' '+ep.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NOT NULL) THEN eda.Nombre+' '+eda.ApellidoPaterno +' '+eda.ApellidoMaterno "); 
		sql.append("  WHEN (h.IdProyecto IS NULL AND h.IdDepartamentoArea IS NULL AND h.IdUnidadDeNegocio IS NOT NULL) THEN eun.Nombre+' '+eun.ApellidoPaterno +' '+eun.ApellidoMaterno "); 
		sql.append("  ELSE ' '  ");
		sql.append("  END AS jefeInmediato ");
        
        sql.append(" FROM Vacacion VAC ");
        sql.append(" LEFT JOIN PeriodoEmpleado PERIODO ON PERIODO.IdPeriodoEmpleado = VAC.IdPeriodoEmpleado ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = PERIODO.IdEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral ESTVAC ON ESTVAC.Codigo=VAC.Estado AND ESTVAC.Grupo = 'Vacaciones.Estado' ");
        
        sql.append(" LEFT JOIN HistorialLaboral h ON h.IdEmpleado = EMP.IdEmpleado AND (h.FechaInicio<=getdate() AND h.FechaFin>=getdate()) ");
        
        sql.append("  LEFT JOIN Proyecto p on p.IdProyecto = h.IdProyecto ");
		sql.append("  LEFT JOIN Empleado ep on p.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN DepartamentoArea da on da.IdDepartamentoArea = h.IdDepartamentoArea ");
		sql.append("  LEFT JOIN Empleado eda on da.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN UnidadDeNegocio un ON un.IdUnidadDeNegocio = h.IdUnidadDeNegocio ");
		sql.append("  LEFT JOIN Empleado eun on un.IdJefe = eun.IdEmpleado ");
        
        sql.append(" where PERIODO.IdEmpleado = "+periodoEmpleado.getIdEmpleado());
        sql.append(params.filter(" AND VAC.IdPeriodoEmpleado = :idPeriodoEmpleado ", periodoEmpleado.getIdPeriodoEmpleado()));
        sql.append(" order by VAC.IdVacacion  desc ");

        return sql.toString();
    }
	
	private String generarVerMarcaciones(EmpleadoDto empleado, WhereParams params) {

		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");  
        sql.append(" MAC.IdMarcacion AS idMarcacion, ");
        sql.append(" MAC.Fecha AS fecha, ");
        sql.append(" MAC.HoraIngreso AS horaIngreso, ");
        sql.append(" MAC.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");
        sql.append(" MAC.HoraFinAlmuerzo AS horaFinAlmuerzo, ");
        sql.append(" MAC.HoraSalida AS horaSalida, ");
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        sql.append(" CASE ");   
        sql.append(" WHEN (MAC.Tardanza =1) THEN 'Si' ");
        sql.append(" WHEN (MAC.Tardanza =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS tardanza, ");
        sql.append(" 'No' AS solicitudCambio ");
        
        
        sql.append(" FROM Marcacion MAC  ");
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAC.IdEmpleado  ");
        sql.append(" where  MAC.IdEmpleado = "+empleado.getIdEmpleado());

        return sql.toString();
    }
	
    private String generarVerHorarioEmpleado(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" he.IdHorarioEmpleado AS idHorarioEmpleado, ");
        sql.append(" he.HorasSemanal AS horasSemanal, ");
        sql.append(" h.HorasSemanal AS horasSemanalHorario, ");
        sql.append(" h.Nombre AS nombreHorario, ");
        sql.append(" TIPOHORARIO.Nombre AS nombreTipoHorario, ");
        sql.append(" he.InicioVigencia AS inicioVigencia, ");
        sql.append(" he.FinVigencia AS finVigencia ");

        sql.append(" FROM HorarioEmpleado he ");
        sql.append(" LEFT JOIN Horario h ON h.IdHorario = he.IdHorario ");
        sql.append(" LEFT JOIN TablaGeneral TIPOHORARIO ON he.TipoHorario= TIPOHORARIO.Codigo AND TIPOHORARIO.Grupo = 'Horario.TipoHorario' ");
        sql.append(" where (he.InicioVigencia<=getDate() and he.FinVigencia>=getDate()) ");
        sql.append(" and he.IdEmpleado = "+empleadoDto.getIdEmpleado());
        

        return sql.toString();
    }
    
    private String generarVerHorariosEmpleado(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" he.IdHorarioEmpleado AS idHorarioEmpleado, ");
        sql.append(" he.IdHorario AS idHorario, ");
        sql.append(" he.TipoHorario AS tipoHorario, ");
        sql.append(" he.HorasSemanal AS horasSemanal, ");
        sql.append(" h.HorasSemanal AS horasSemanalHorario, ");
        sql.append(" h.Nombre AS nombreHorario, ");
        sql.append(" TIPOHORARIO.Nombre AS nombreTipoHorario, ");
        sql.append(" he.InicioVigencia AS inicioVigencia, ");
        sql.append(" he.FinVigencia AS finVigencia ");

        sql.append(" FROM HorarioEmpleado he ");
        sql.append(" LEFT JOIN Horario h ON h.IdHorario = he.IdHorario ");
        sql.append(" LEFT JOIN TablaGeneral TIPOHORARIO ON he.TipoHorario= TIPOHORARIO.Codigo AND TIPOHORARIO.Grupo = 'Horario.TipoHorario' ");
        sql.append(" WHERE he.IdEmpleado = "+empleadoDto.getIdEmpleado());
        

        return sql.toString();
    }
        
    private String generarVerHorarioEmpleadoDia(HorarioEmpleadoDto horarioEmpleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" hed.IdHorarioEmpleadoDia AS idHorarioEmpleadoDia, ");
        sql.append(" hed.laboral AS laboral, ");
        sql.append(" hed.Entrada AS entrada, ");
        sql.append(" hed.DiaSemana AS diaSemana, ");
        sql.append(" hed.Salida AS salida, ");
        sql.append(" hed.TiempoAlmuerzo AS tiempoAlmuerzo, ");
        sql.append(" DIASEMANA.Nombre AS nombreDiaSemana ");

        sql.append(" FROM HorarioEmpleadoDia hed ");
        sql.append(" LEFT JOIN TablaGeneral DIASEMANA ON hed.DiaSemana= DIASEMANA.Codigo AND DIASEMANA.Grupo = 'Dia' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND hed.IdHorarioEmpleado = :idHorarioEmpleado ", horarioEmpleadoDto.getIdHorarioEmpleado()));

        return sql.toString();
    }
    
    private String generarVerBusquedaMarcacionesEmpleado(BusquedaMarcacionDto busquedaMarcacionDto,  WhereParams params){
		StringBuilder sql = new StringBuilder();
		
        sql.append(" SELECT distinct ");   
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");  
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");  
        
        sql.append(" MAR.DemoraEntrada AS demoraEntrada, ");  
        sql.append(" MAR.DemoraAlmuerzo AS demoraAlmuerzo, ");  
        sql.append(" MAR.DemoraSalida AS demoraSalida, ");  
                
        sql.append(" CASE ");   
        sql.append(" WHEN (MAR.Tardanza =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.Tardanza =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS tardanza, ");
        sql.append(" CASE ");   
        sql.append(" WHEN (MAR.Inasistencia =1) THEN 'Si' ");
        sql.append(" WHEN (MAR.Inasistencia =0) THEN 'No' ");
        sql.append(" ELSE 'No' ");
        sql.append(" END AS inasistencia, ");
        sql.append(" 'No' AS solicitudCambio, ");
        
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");  
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");  
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        
        sql.append(" EMP.Nombre AS nombreEmpleado, ");  
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");  
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");  
        sql.append(" EMP.Codigo AS codigoEmpleado ");  
        
        //sql.append(" PROY.Nombre AS nombreProyecto, ");  
        //sql.append(" DEP.Nombre AS nombreDepartamento, ");  
        //sql.append(" UN.Nombre AS nombreUnidadNegocio ");  

        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");  
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND (HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin > MAR.Fecha) ");  

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");  
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");  
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");  
        sql.append(" WHERE 1=1 ");
        
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", busquedaMarcacionDto.getIdEmpleado()));
        
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaMarcacionDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaMarcacionDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaMarcacionDto.getProyecto()));
        
        sql.append(params.filterDateDesde_US(" AND MAR.Fecha  " , busquedaMarcacionDto.getDesde()));
        sql.append(params.filterDateHasta_US(" AND MAR.Fecha  ", busquedaMarcacionDto.getHasta()));
        
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaMarcacionDto.getIdJefeInmediato()));
        
		return sql.toString();
	}
	

    private String generarBusquedaEmpleado(BusquedaEmpleadoDto busquedaEmpleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct e.IdEmpleado, e.Nombre as nombre,  ");
        sql.append(" e.ApellidoPaterno as apellidoPaterno, e.ApellidoMaterno as apellidoMaterno,  ");
        sql.append(" e.TipoDocumento as tipoDocumento,  TIPODOCUMENTO.Nombre as tipoDocumentoString, e.Codigo as codigo,  ");
        sql.append(" e.NumeroDocumento as numeroDocumento, e.Genero as genero,  ");
        sql.append(" e.EmailInterno as emailInterno, e.Codigo as codigo,  ");
        sql.append(" e.AnexoInterno as anexoInterno, e.IdCentroCosto as idCentroCosto,  CENTRO.Nombre as centroCostoString, ");

        sql.append(" e.Genero as genero, GENERO.Nombre as generoString, ");
        sql.append(" e.EstadoCivil as estadoCivil, ESTADOCIVIL.Nombre as estadoCivilString, ");
        sql.append(" e.GrupoSangre as grupoSangre, GRUPOSANGRE.Nombre as grupoSangreString, ");
        sql.append(" e.Discapacitado as discapacitado, DISCAPACITADO.Nombre as discapacitadoString, ");

        sql.append(" e.IdEmpresa as idEmpresa, e.Estado as estado, ESTADO.Nombre as estadoString, ");
        sql.append(" e.FechaNacimiento as fechaNacimiento ,");
        sql.append(" PAIS.Nombre as paisNacimiento ");
        
        //sql.append(" PROY.Nombre as nombreProyecto,  ");
        //sql.append(" DEP.Nombre as nombreDepartamento, ");
        //sql.append(" UN.Nombre as nombreUnidadNegocio ");

        sql.append(" from Empleado e");
        sql.append(" LEFT JOIN Pais PAIS ON e.PaisNacimiento=PAIS.Codigo ");
        sql.append(" LEFT JOIN TablaGeneral DISCAPACITADO ON e.Discapacitado=DISCAPACITADO.Codigo and DISCAPACITADO.GRUPO='Empleado.Discapacitado'");
        sql.append(" LEFT JOIN TablaGeneral GRUPOSANGRE ON e.GrupoSangre=GRUPOSANGRE.Codigo and GRUPOSANGRE.GRUPO='Empleado.GrupoSanguineo'");
        sql.append(" LEFT JOIN TablaGeneral GENERO ON e.Genero=GENERO.Codigo and GENERO.GRUPO='Empleado.Generico'");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCIVIL ON e.EstadoCivil=ESTADOCIVIL.Codigo and ESTADOCIVIL.GRUPO='Empleado.EstadoCivil'");
        sql.append(" LEFT JOIN TablaGeneral TIPODOCUMENTO ON e.TipoDocumento=TIPODOCUMENTO.Codigo and TIPODOCUMENTO.GRUPO='Empleado.TipoDocumento'");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo and ESTADO.GRUPO='Empleado.Estado'");
        sql.append(" LEFT JOIN CentroCosto CENTRO ON CENTRO.IdCentroCosto = e.IdCentroCosto");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = e.IdEmpleado AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        
        sql.append(" where 1=1");
        sql.append(params.filter(" AND UPPER(e.Nombre) LIKE UPPER('%' + :nombres + '%') ", busquedaEmpleadoDto.getNombres()));
        sql.append(params.filter(" AND UPPER(e.ApellidoPaterno) LIKE UPPER('%' + :apellidoPaterno + '%') ", busquedaEmpleadoDto.getApePaterno()));
        sql.append(params.filter(" AND UPPER(e.ApellidoMaterno) LIKE UPPER('%' + :apellidoMaterno + '%') ", busquedaEmpleadoDto.getApeMaterno()));
        sql.append(params.filter(" AND UPPER(e.EmailInterno) LIKE UPPER('%' + :correoElectronico + '%') ", busquedaEmpleadoDto.getCorreoElectronico()));
        sql.append(params.filter(" AND e.Codigo = :codigo ", busquedaEmpleadoDto.getCodigo()));
        sql.append(params.filter(" AND e.TipoDocumento = :tipoDocumento ", busquedaEmpleadoDto.getTipoDocumento()));
        sql.append(params.filter(" AND e.NumeroDocumento = :numeroDocumento ", busquedaEmpleadoDto.getNumeroDocumento()));
        
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaEmpleadoDto.getProyecto()));

        //TODO Unidad de Negocio, Proyecto, Departamento?
        //TODO Jefe?
        sql.append(params.filter(" AND e.IdCentroCosto = :centroCosto ", busquedaEmpleadoDto.getCentroCosto()));
        sql.append(params.filter(" AND UPPER(e.EmailInterno) LIKE UPPER('%' + :correoElectronico + '%') ", busquedaEmpleadoDto.getCorreoElectronico()));
        sql.append(params.filter(" AND e.Estado = :estado ", busquedaEmpleadoDto.getEstado()));
        
        sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaEmpleadoDto.getIdJefeInmediato()));

        return sql.toString();
    }
    
    private String generarVerEmpleado(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" select e.IdEmpleado as idEmpleado, "); 
        sql.append(" e.Nombre as nombre, ");  
        sql.append(" e.ApellidoPaterno as apellidoPaterno, "); 
        sql.append(" e.ApellidoMaterno as apellidoMaterno, ");   
        sql.append(" e.TipoDocumento as tipoDocumento, "); 
        sql.append(" TIPODOCUMENTO.Nombre as tipoDocumentoString, "); 
        sql.append(" e.NumeroDocumento as numeroDocumento, "); 
        sql.append(" e.Genero as genero, ");  
        sql.append(" GENERO.Nombre as generoString, ");  
        sql.append(" e.EstadoCivil as estadoCivil, "); 
        sql.append(" ESTADOCIVIL.Nombre as estadoCivilString, ");  
        sql.append(" e.GrupoSangre as grupoSangre, "); 
        sql.append(" GRUPOSANGRE.Nombre as grupoSangreString, ");  
        sql.append(" e.Discapacitado as discapacitado, ");
        sql.append(" e.FechaNacimiento as fechaNacimiento, ");
        sql.append(" e.PaisNacimiento as paisNacimiento, "); 
        sql.append(" PAIS.Nombre AS paisNacimientoString, ");
        sql.append(" e.DepartamentoNacimiento as departamentoNacimiento, "); 
        sql.append(" DEP.Nombre AS departamentoNacimientoString, ");
        sql.append(" e.ProvinciaNacimiento as provinciaNacimiento, "); 
        sql.append(" PROV.Nombre AS provinciaNacimientoString, ");
        sql.append(" e.DistritoNacimiento AS distritoNacimiento, ");
        sql.append(" e.Codigo as codigo, ");   
        sql.append(" e.FechaIngreso as fechaIngreso, ");   
        sql.append(" e.EmailInterno as emailInterno, "); 
        sql.append(" e.TelefonoInterno AS telefonoInterno, ");
        sql.append(" e.AnexoInterno as anexoInterno, ");
        sql.append(" e.IdCentroCosto as idCentroCosto, "); 
        sql.append(" CENTRO.Nombre as centroCostoString, ");  
        sql.append(" e.ContratoTrabajo as contratoTrabajo, "); 
        sql.append(" CONTRATOTRABAJO.Nombre AS contratoTrabajoString, ");
        sql.append(" e.TipoTrabajador as tipoTrabajador, "); 
        sql.append(" TIPOTRABAJO.Nombre AS tipoTrabajadorString, ");
        sql.append(" e.RegimenHorario as regimenHorario, "); 
        sql.append(" REGIMENHORARIO.Nombre AS regimenHorarioString, ");
        sql.append(" e.RegimenLaboral as regimenLaboral, "); 
        sql.append(" REGIMENLABORAL.Nombre AS regimenLaboralString, ");
        sql.append(" e.TipoDomicilio as tipoDomicilio, "); 
        sql.append(" TIPODOMICILIO.Nombre AS tipoDomicilioString, ");
        sql.append(" e.PaisDomicilio as paisDomicilio, "); 
        sql.append(" PAIS.Nombre AS paisDomicilioString, ");
        sql.append(" e.DepartamentoDomicilio as departamentoDomicilio, "); 
        sql.append(" DEP.Nombre AS departamentoDomicilioString, ");
        sql.append(" e.ProvinciaDomicilio as provinciaDomicilio, "); 
        sql.append(" PROV.Nombre AS provinciaDomicilioString, ");
        sql.append(" e.DistritoDomicilio AS distritoDomicilio, ");
        sql.append(" e.RelacionContactoEmergencia AS relacionContactoEmergencia, ");
        sql.append(" RELACION.Nombre AS relacionContactoEmergenciaString, ");
        sql.append(" ESTADO.Nombre as estadoString, ");
        sql.append(" e.Estado as estado, ");
        
        sql.append(" e.TieneEPS as tieneEps, ");
        sql.append(" e.EntidadBancariaSueldo as entidadBancariaSueldo, ");
        sql.append(" e.AFP as afp, ");
        sql.append(" e.CTS as cts, ");
        sql.append(" e.EPS as eps, ");
        
        sql.append(" ENTIDADBANCARIA.Nombre as entidadBancariaSueldoString, ");
        sql.append(" AFP.Nombre as afpString, ");
        sql.append(" CTS.Nombre as ctsString, ");
        sql.append(" EPS.Nombre as epsString, ");
        
        sql.append(" e.DireccionDomicilio AS direccionDomicilio, ");
        sql.append(" e.TelefonoCasa AS telefonoCasa, ");
        sql.append(" e.TelefonoCelular AS telefonoCelular, ");
        sql.append(" e.TelefonoAdicional AS telefonoAdicional, ");
        sql.append(" e.EmailPersonal AS emailPersonal, ");
        sql.append(" e.NombreContactoEmergencia AS nombreContactoEmergencia, ");
        sql.append(" e.TelefonoContactoEmergencia AS telefonoContactoEmergencia, ");
        sql.append(" e.EmailContactoEmergencia AS emailContactoEmergencia, ");
        sql.append(" e.Discapacitado AS discapacitado, ");
        sql.append(" e.EsPersonalDeConfianza AS esPersonalDeConfianza ");
        sql.append(" from Empleado e  ");
        
        sql.append(" lEFT JOIN Pais PAIS ON e.PaisNacimiento= PAIS.Codigo ");
        sql.append(" LEFT JOIN Departamento DEP ON e.DepartamentoNacimiento= DEP.Codigo ");
        sql.append(" LEFT JOIN Provincia PROV ON e.ProvinciaNacimiento= PROV.Codigo ");
        sql.append(" LEFT JOIN CentroCosto CENTRO ON CENTRO.IdCentroCosto = e.IdCentroCosto "); 
        sql.append(" lEFT JOIN Pais PAISDOM ON e.PaisDomicilio= PAISDOM.Codigo ");
        sql.append(" LEFT JOIN Departamento DEPDOM ON e.DepartamentoDomicilio= DEPDOM.Codigo ");
        sql.append(" LEFT JOIN Provincia PROVDOM ON e.ProvinciaDomicilio= PROVDOM.Codigo ");
        sql.append(" LEFT JOIN TablaGeneral GRUPOSANGRE ON e.GrupoSangre=GRUPOSANGRE.Codigo and GRUPOSANGRE.GRUPO='Empleado.GrupoSanguineo' "); 
        sql.append(" LEFT JOIN TablaGeneral GENERO ON e.Genero=GENERO.Codigo and GENERO.GRUPO='Empleado.Generico' ");
        sql.append(" LEFT JOIN TablaGeneral ESTADOCIVIL ON e.EstadoCivil=ESTADOCIVIL.Codigo and ESTADOCIVIL.GRUPO='Empleado.EstadoCivil' "); 
        sql.append(" LEFT JOIN TablaGeneral TIPODOCUMENTO ON e.TipoDocumento=TIPODOCUMENTO.Codigo and TIPODOCUMENTO.GRUPO='Empleado.TipoDocumento' "); 
        sql.append(" LEFT JOIN TablaGeneral CONTRATOTRABAJO ON e.ContratoTrabajo=CONTRATOTRABAJO.Codigo and CONTRATOTRABAJO.GRUPO='Empleado.ContratoTrabajo' "); 
        sql.append(" LEFT JOIN TablaGeneral TIPOTRABAJO ON e.TipoTrabajador=TIPOTRABAJO.Codigo and TIPOTRABAJO.GRUPO='Empleado.TipoTrabajo' "); 
        sql.append(" LEFT JOIN TablaGeneral REGIMENHORARIO ON e.RegimenHorario=REGIMENHORARIO.Codigo and REGIMENHORARIO.GRUPO='Empleado.RegimenHorario' "); 
        sql.append(" LEFT JOIN TablaGeneral REGIMENLABORAL ON e.RegimenLaboral=REGIMENLABORAL.Codigo and REGIMENLABORAL.GRUPO='Empleado.RegimenLaboral' "); 
        sql.append(" LEFT JOIN TablaGeneral TIPODOMICILIO ON e.TipoDomicilio=TIPODOMICILIO.Codigo and TIPODOMICILIO.GRUPO='Empleado.TipoDomicilio' "); 
        sql.append(" LEFT JOIN TablaGeneral RELACION ON e.RelacionContactoEmergencia=RELACION.Codigo and RELACION.GRUPO='Empleado.RelacionContacto' "); 
        
        sql.append(" LEFT JOIN TablaGeneral CTS ON e.CTS=CTS.Codigo and CTS.GRUPO='Entidad Financiera' "); 
        sql.append(" LEFT JOIN TablaGeneral AFP ON e.AFP=AFP.Codigo and AFP.GRUPO='AFP' "); 
        sql.append(" LEFT JOIN TablaGeneral EPS ON e.EPS=EPS.Codigo and EPS.GRUPO='EPS' "); 
        sql.append(" LEFT JOIN TablaGeneral ENTIDADBANCARIA ON e.EntidadBancariaSueldo=ENTIDADBANCARIA.Codigo and ENTIDADBANCARIA.GRUPO='Entidad Financiera' "); 
         
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON e.Estado=ESTADO.Codigo and ESTADO.GRUPO='Empleado.Estado' ");
         
        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", empleadoDto.getIdEmpleado()));

        return sql.toString();
    }
    
    private String generarVerEducacion(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" e.IdEducacion AS idEducacion, ");
        sql.append(" e.Institucion AS institucion, ");
        sql.append(" e.FechaInicio AS fechaInicio, ");
        sql.append(" e.FechaFin AS fechaFin, ");
        sql.append(" e.Titulo AS titulo, ");
        sql.append(" e.Descripcion AS descripcion, ");
        sql.append(" e.NivelEducacion AS nivelEducacion, ");
        sql.append(" NIVELEDU.Nombre AS nombreNivelEducacion ");
        sql.append(" FROM Educacion e ");
        sql.append(" LEFT JOIN TablaGeneral NIVELEDU ON e.NivelEducacion = NIVELEDU.Codigo AND NIVELEDU.Grupo = 'Empleado.NivelEducacion' ");
 
         
        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", empleadoDto.getIdEmpleado()));

        return sql.toString();
    }
    
    private String generarVerEquipoEntregado(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT e.Descripcion AS descripcion, ");
        sql.append(" e.IdEquipoEntregado AS idEquipoEntregado, ");
        sql.append(" e.FechaEntrega AS fechaEntrega, ");
        sql.append(" e.FechaDevolucion AS fechaDevolucion, ");
        sql.append(" e.TipoEquipo AS tipoEquipo, ");
        sql.append(" TIPOEQUIPO.Nombre AS nombreTipoEquipo, ");
        sql.append(" e.Estado AS estado, ");
        sql.append(" ESTADO.Nombre AS nombreEstado ");
        sql.append(" FROM EquipoEntregado e ");
        sql.append(" LEFT JOIN TablaGeneral TIPOEQUIPO ON TIPOEQUIPO.Codigo=e.TipoEquipo AND TIPOEQUIPO.Grupo = 'EquiposEntregados.TipoEquipo' ");
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON ESTADO.Codigo=e.Estado AND ESTADO.Grupo = 'EquiposEntregados.Estado' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND e.IdEmpleado = :idEmpleado ", empleadoDto.getIdEmpleado()));

        return sql.toString();
    }
    
    private String generarVerDependiente(EmpleadoDto empleadoDto, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("d.IdDependiente AS idDependiente, ");
        sql.append("d.Nombre AS nombre, ");
        sql.append("d.ApellidoPaterno AS apellidoPaterno, ");
        sql.append("d.ApellidoMaterno AS apellidoMaterno, ");
        sql.append("RELACION.Nombre AS nombreRelacion, ");
        sql.append("d.Relacion AS relacion, ");
        sql.append("d.TipoDocumento AS tipoDocumento, ");
        sql.append("TIPODOC.Nombre AS nombreTipoDocumento, ");
        sql.append("d.NumeroDocumento AS numeroDocumento, ");
        sql.append("d.FechaNacimiento AS fechaNacimiento ");
        sql.append("FROM Dependiente d ");

        sql.append("LEFT JOIN TablaGeneral RELACION ON RELACION.Codigo=d.Relacion AND RELACION.Grupo = 'Empleado.RelacionDependiente' ");
        sql.append("LEFT JOIN TablaGeneral TIPODOC ON TIPODOC.Codigo=d.TipoDocumento AND TIPODOC.Grupo = 'Empleado.TipoDocumento' ");

        sql.append(" where 1=1");
        sql.append(params.filter(" AND d.IdEmpleado = :idEmpleado ", empleadoDto.getIdEmpleado()));

        return sql.toString();
    }
    
    private String generarVerLicencia(PeriodoEmpleadoDto periodoEmpleado, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  ");
        sql.append(" l.IdLicencia AS IdLicencia, ");
        sql.append(" LIC.Nombre AS nombreMotivo, ");
        sql.append(" l.Comentario AS comentario, ");
        sql.append(" l.FechaInicio AS fechaInicio, ");
        sql.append(" l.FechaFin AS fechaFin, ");
        sql.append(" l.Dias AS dias ");

        sql.append(" FROM Licencia l ");
        sql.append(" LEFT JOIN Empleado ON Empleado.IdEmpleado = l.IdEmpleado ");
        sql.append(" LEFT JOIN PeriodoEmpleado ON PeriodoEmpleado.IdEmpleado = Empleado.IdEmpleado ");
        sql.append(" LEFT JOIN TablaGeneral LIC ON LIC.Codigo=l.Motivo AND LIC.Grupo = 'Licencia.TipoLicencia' ");
        
        sql.append(" where l.IdEmpleado = "+periodoEmpleado.getIdEmpleado());
        
        if(periodoEmpleado.getIdPeriodoEmpleado() != null){
        	sql.append(" AND PeriodoEmpleado.IdPeriodoEmpleado ="+periodoEmpleado.getIdPeriodoEmpleado());
        	sql.append(" AND ( ");
        	sql.append(" (  ");
        	sql.append(" l.FechaInicio<=PeriodoEmpleado.FechaFin ");
        	sql.append(" AND l.FechaInicio>=PeriodoEmpleado.FechaInicio ");
        	sql.append(" ) ");
        	sql.append(" OR ");
        	sql.append(" ( ");
        	sql.append(" l.FechaFin<=PeriodoEmpleado.FechaFin ");
        	sql.append(" AND l.FechaFin>=PeriodoEmpleado.FechaInicio");
        	sql.append(" ) ");
        	sql.append(" ) ");
        	
        }
        
        sql.append(" GROUP BY l.IdLicencia, ");
        sql.append(" l.Motivo, "); 
        sql.append(" l.Comentario, ");
        sql.append(" l.FechaInicio, "); 
        sql.append(" l.FechaFin,  ");
        sql.append(" l.Dias, ");
        sql.append(" LIC.Nombre  ");

        return sql.toString();
    }

	public List<HorasExtraDto> busquedaHorasExtrasEmpleado(
			BusquedaHorasExtraEmpleadoDto busquedaHorasExtraEmpleadoDto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaHorasExtrasEmpleado(busquedaHorasExtraEmpleadoDto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(HorasExtraDto.class));
	}

	private String generarBusquedaHorasExtrasEmpleado(BusquedaHorasExtraEmpleadoDto busquedaHorasExtraEmpleadoDto,
			WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT distinct he.IdHorasExtra AS idHorasExtra, ");
        sql.append(" EMPLEADO.IdEmpleado AS idEmpleado, ");
        sql.append(" he.Motivo AS motivo, ");
        sql.append(" he.HoraSalidaSolicitado AS horaSalidaSolicitado, ");
        sql.append(" he.HoraSalidaHorario AS horaSalidaHorario, ");
        //sql.append(" UN.Nombre AS unidadDeNegocio, ");
        //sql.append(" DEP.Nombre AS departamentoArea, ");
        //sql.append(" PROY.Nombre AS proyecto, ");
        //sql.append(" PROY.IdJefe AS JefeInmediato, ") ;
        sql.append(" CONCAT(ATENDIDO.ApellidoPaterno, ' ', ATENDIDO.ApellidoMaterno, ', ', ATENDIDO.Nombre) as nombreJefeInmediato, ");
        sql.append(" CONCAT(EMPLEADO.ApellidoPaterno, ' ', EMPLEADO.ApellidoMaterno, ', ', EMPLEADO.Nombre) as nombreEmpleado, ");
        sql.append(" he.Fecha as fecha, ");
        sql.append(" he.Horas as horas, ");
        sql.append(" he.Estado AS estado, ");
        sql.append(" ESTADO.Nombre AS estadoString ") ;
        sql.append(" from HorasExtra he ") ;
        
        sql.append(" LEFT JOIN TablaGeneral ESTADO ON he.Estado=ESTADO.Codigo and ESTADO.GRUPO='Permiso.Estado'");
        sql.append(" LEFT JOIN Empleado EMPLEADO ON he.IdEmpleado = EMPLEADO.IdEmpleado ");
        
        sql.append(" LEFT JOIN Empleado ATENDIDO ON he.IdAtendidoPor = ATENDIDO.IdEmpleado ");
        
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = he.IdEmpleado AND (HISTORIAL.FechaInicio < getdate() AND HISTORIAL.FechaFin > getDate()) ");
        
        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");
        
        sql.append("  LEFT JOIN Empleado ep on PROY.IdJefe = ep.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eda on DEP.IdJefe = eda.IdEmpleado ");
		sql.append("  LEFT JOIN Empleado eun on UN.IdJefe = eun.IdEmpleado ");
                
        sql.append(" where 1=1 ");
        sql.append(params.filter(" AND he.Estado = :estado ", busquedaHorasExtraEmpleadoDto.getEstado()));
        sql.append(params.filterDateDesde_US(" AND he.Fecha " , busquedaHorasExtraEmpleadoDto.getFechaInicio()));
        sql.append(params.filterDateHasta_US(" AND he.Fecha ", busquedaHorasExtraEmpleadoDto.getFechaFin()));
        sql.append(params.filter(" AND UN.IdUnidadDeNegocio = :unidadNegocio ", busquedaHorasExtraEmpleadoDto.getUnidadNegocio()));
        sql.append(params.filter(" AND DEP.IdDepartamentoArea = :departamento ", busquedaHorasExtraEmpleadoDto.getDepartamento()));
        sql.append(params.filter(" AND PROY.IdProyecto = :proyecto ", busquedaHorasExtraEmpleadoDto.getProyecto()));
        
        sql.append(params.filter(" AND EMPLEADO.IdEmpleado = :idEmpleado ", busquedaHorasExtraEmpleadoDto.getIdEmpleado()));
        
        sql.append(params.filter(" AND he.IdAtendidoPor = :idEmpleado ", busquedaHorasExtraEmpleadoDto.getIdJefeInmediato()));
        
        /*sql.append(params.filter(" AND ( "+
        		" (HISTORIAL.IdProyecto IS NOT NULL AND PROY.IdJefe = :idJefeInmediato)  OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NOT NULL AND DEP.IdJefe  = :idJefeInmediato)"+ 
        		" OR (HISTORIAL.IdProyecto IS  NULL AND HISTORIAL.IdDepartamentoArea IS NULL AND HISTORIAL.IdUnidadDeNegocio IS NOT NULL AND UN.IdJefe = :idJefeInmediato) ) ", busquedaHorasExtraEmpleadoDto.getIdJefeInmediato()));*/
        
        return sql.toString();
	}

	public HorasExtraDto informacionAdicionalHorasExtras(EmpleadoDto empleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerInformacionAdicionalHorasExtras(empleado, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HorasExtraDto>(HorasExtraDto.class));
	}

	private String obtenerInformacionAdicionalHorasExtras(EmpleadoDto empleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append("  SELECT TOP 1 ");
		return sql.toString();
	}

	public HorasExtraDto getHorarioEmpleadoDia(Long idEmpleado, Integer dayOfWeek) {
		WhereParams params = new WhereParams();
		String dayOfWeekVal = "";
		if(dayOfWeek==1){
			dayOfWeekVal = "DO";
		}else if(dayOfWeek==2){
			dayOfWeekVal = "LU";
		}else if(dayOfWeek==3){
			dayOfWeekVal = "MA";
		}else if(dayOfWeek==4){
			dayOfWeekVal = "MI";
		}else if(dayOfWeek==5){
			dayOfWeekVal = "JU";
		}else if(dayOfWeek==6){
			dayOfWeekVal = "VI";
		}else if(dayOfWeek==7){
			dayOfWeekVal = "SA";
		}
		
		String sql = getHorarioEmpleadoDia(idEmpleado, dayOfWeekVal, params);
		
		return jdbcTemplate.queryForObject(sql, params.getParams(),
				new BeanPropertyRowMapper<HorasExtraDto>(HorasExtraDto.class));
	}

	private String getHorarioEmpleadoDia(Long idEmpleado,String dayOfWeekVal, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select hed.Salida AS horaSalidaHorario");
		sql.append(" FROM HorarioEmpleado he  ");
		sql.append(" LEFT JOIN HorarioEmpleadoDia hed on hed.IdHorarioEmpleado = he.IdHorarioEmpleado ");
		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND (he.InicioVigencia<= getdate() AND he.FinVigencia>= getdate()) ");
		sql.append(" AND he.IdEmpleado="+idEmpleado);
		sql.append(" AND hed.DiaSemana='"+dayOfWeekVal+"'");
		return sql.toString();
	}

	public List<HorasExtraDto> listHorasExtraEmpleado(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = getListHorasExtraEmpleado(idEmpleado);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<HorasExtraDto>(HorasExtraDto.class));
	}

	private String getListHorasExtraEmpleado(Long idEmpleado) {
		StringBuilder sql = new StringBuilder();
		sql.append(" select h.Fecha as fecha, ");
		sql.append(" h.IdHorasExtra as idHorasExtra, ");
		sql.append(" h.HoraSalidaHorario as horaSalidaHorario, ");
		sql.append(" h.HoraSalidaSolicitado as horaSalidaSolicitado ");
		sql.append(" FROM HorasExtra h ");
		sql.append(" WHERE 1 = 1 AND h.Estado != 'R' ");
		sql.append(" AND h.IdEmpleado ="+idEmpleado);
		return sql.toString();
	}

	public List<EquipoEntregadoDto> obtenerEquiposPendientesDevolucion(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = getEquiposPendientesDevolucion(idEmpleado, params);
		
		return jdbcTemplate.query(sql, params.getParams(),
				new BeanPropertyRowMapper<EquipoEntregadoDto>(EquipoEntregadoDto.class));
	}

	private String getEquiposPendientesDevolucion(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		   sql.append("  select TIPO.Nombre tipoEquipo, \n");
		   sql.append("  ee.Descripcion descripcion, \n");
		   sql.append("  ee.FechaEntrega fechaEntrega, \n");
		   sql.append("  ee.FechaDevolucion fechaDevolucion,  \n");
		   sql.append("  ESTADO.Nombre estado \n");
		   
		   sql.append("  From EquipoEntregado ee \n");
		   sql.append("  LEFT JOIN TablaGeneral ESTADO ON ee.Estado = ESTADO.Codigo	AND ESTADO.GRUPO = 'EquiposEntregados.Estado' \n");
		   sql.append("  LEFT JOIN TablaGeneral TIPO ON ee.TipoEquipo = TIPO.Codigo	AND TIPO.GRUPO = 'EquiposEntregados.TipoEquipo'  \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append(params.filter(" AND ee.IdEmpleado =:idEmpleado ", idEmpleado));
		
		System.out.println("Query getEquiposPendientesDevolucion: " + sql.toString());
		
		return sql.toString();
	}

	public Long countEquiposPendientesDevolucion(Long idEmpleado) {
		WhereParams params = new WhereParams();
		String sql = obtenerEquiposPendientesDevolucion(idEmpleado, params);
		return calculateTotalRows(sql, params);
	}
	
	private String obtenerEquiposPendientesDevolucion(Long idEmpleado, WhereParams params) {
		StringBuilder sql = new StringBuilder();
		
		   sql.append("  select TIPO.Nombre tipoEquipo, \n");
		   sql.append("  ee.Descripcion descripcion, \n");
		   sql.append("  ee.FechaEntrega fechaEntrega, \n");
		   sql.append("  ee.FechaDevolucion fechaDevolucion,  \n");
		   sql.append("  ESTADO.Nombre estado \n");
		   
		   sql.append("  From EquipoEntregado ee \n");
		   sql.append("  LEFT JOIN TablaGeneral ESTADO ON ee.Estado = ESTADO.Codigo	AND ESTADO.GRUPO = 'EquiposEntregados.Estado' \n");
		   sql.append("  LEFT JOIN TablaGeneral TIPO ON ee.TipoEquipo = TIPO.Codigo	AND TIPO.GRUPO = 'EquiposEntregados.TipoEquipo'  \n");
		   sql.append("  WHERE 1 = 1 \n");
		   sql.append("  AND ESTADO.Codigo = 'EN' \n");
		   sql.append(params.filter(" AND ee.IdEmpleado =:idEmpleado ", idEmpleado));
		
		System.out.println("Query obtenerEquiposPendientesDevolucion: " + sql.toString());
		
		return sql.toString();
	}

	private long calculateTotalRows(String queryBase, WhereParams params) {
        String query = "SELECT COUNT(1) FROM (" + queryBase + ") X";
        return jdbcTemplate.queryForObject(query, params.getParams(), Long.class);
    }

    
}