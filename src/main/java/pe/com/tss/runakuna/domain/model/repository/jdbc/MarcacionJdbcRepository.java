package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
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
import pe.com.tss.runakuna.dto.RegistroMarcacionEmpleadoDto;
import pe.com.tss.runakuna.dto.VacacionDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;
import pe.com.tss.runakuna.support.WhereParams;
import pe.com.tss.runakuna.util.DateUtil;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class MarcacionJdbcRepository implements MarcacionRepository {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("marcacionDataSource") 
    DataSource marcacionDataSource;
    
    @Autowired
    DataSource dataSource;

    private JdbcTemplate marcacionJdbcTemplate;
    
    private NamedParameterJdbcTemplate jdbcTemplate;
    

    @PostConstruct
    public void init() {
    	marcacionJdbcTemplate = new JdbcTemplate(marcacionDataSource);
    	marcacionJdbcTemplate.setResultsMapCaseInsensitive(true);
    	
    	jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public  List<RegistroMarcacionEmpleadoDto> obtenerMarcacion() {

       SimpleJdbcCall jdbcCall = new SimpleJdbcCall(marcacionJdbcTemplate).withProcedureName("spuMarcacionesPendientes")
    		   .withCatalogName("dbo").
    		   returningResultSet("Marcaciones",new BeanPropertyRowMapper<RegistroMarcacionEmpleadoDto>(RegistroMarcacionEmpleadoDto.class));
       
       Map<String, Object> out = jdbcCall.execute(new HashMap<String, Object>(0));
              
       List<RegistroMarcacionEmpleadoDto> lista = (List)out.get("Marcaciones");
             
       return lista;
       
    }
    
    @Override
    public MarcacionDto obtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoDto empleado) {
        WhereParams params = new WhereParams();
        String sql = generarObtenerMarcacionesPorEmpleadoyFechaActual(empleado, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<>(MarcacionDto.class));
    }
    
    private String generarObtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoDto empleado, WhereParams params) {

    	StringBuilder sql = new StringBuilder();
    	
    	String formateFechaActual = new SimpleDateFormat("MM/dd/yyyy").format(new Date());
    	
    	sql.append(" SELECT ");   
        sql.append(" MAR.IdMarcacion AS idMarcacion, ");  
        sql.append(" MAR.Fecha AS fecha, ");  
        sql.append(" MAR.HoraIngreso AS horaIngreso, ");  
        sql.append(" MAR.HoraInicioAlmuerzo AS horaInicioAlmuerzo, ");  
        sql.append(" MAR.HoraFinAlmuerzo AS horaFinAlmuerzo, ");  
        sql.append(" MAR.HoraSalida AS horaSalida, ");  
                
        
        sql.append(" MAR.HoraIngresoHorario AS horaIngresoHorario, ");  
        sql.append(" MAR.HoraSalidaHorario AS horaSalidaHorario, ");  
        
        sql.append(" EMP.Nombre+' '+EMP.ApellidoPaterno+' '+EMP.ApellidoMaterno AS nombreCompletoEmpleado, ");
        
        sql.append(" EMP.Nombre AS nombreEmpleado, ");  
        sql.append(" EMP.ApellidoPaterno AS apelPaternoEmpleado, ");  
        sql.append(" EMP.ApellidoMaterno AS apelMaternoEmpleado, ");  
        sql.append(" EMP.Codigo AS codigoEmpleado, ");  
        
        sql.append(" PROY.Nombre AS nombreProyecto, ");  
        sql.append(" DEP.Nombre AS nombreDepartamento, ");  
        sql.append(" UN.Nombre AS nombreUnidadNegocio ");  

        sql.append(" FROM Marcacion MAR ");  
        sql.append(" LEFT JOIN Empleado EMP ON EMP.IdEmpleado = MAR.IdEmpleado ");  
        sql.append(" LEFT JOIN HistorialLaboral HISTORIAL ON HISTORIAL.IdEmpleado = EMP.IdEmpleado AND ((HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin IS NULL) OR (HISTORIAL.FechaInicio < MAR.Fecha AND HISTORIAL.FechaFin IS NOT NULL AND HISTORIAL.FechaFin > MAR.Fecha))  ");  

        sql.append(" LEFT JOIN Proyecto PROY ON PROY.IdProyecto = HISTORIAL.IdProyecto ");  
        sql.append(" LEFT JOIN DepartamentoArea DEP ON DEP.IdDepartamentoArea = HISTORIAL.IdDepartamentoArea ");  
        sql.append(" LEFT JOIN UnidadDeNegocio UN ON UN.IdUnidadDeNegocio = HISTORIAL.IdUnidadDeNegocio ");  
        sql.append(" WHERE 1=1 ");
        
        sql.append(params.filter(" AND EMP.IdEmpleado = :idEmpleado ", empleado.getIdEmpleado()));
        
        sql.append(params.filterDate_US(" AND MAR.Fecha  " , DateUtil.parse(new SimpleDateFormat("MM/dd/yyyy"), formateFechaActual)));
     
		return sql.toString();
    }
    
    

    

}