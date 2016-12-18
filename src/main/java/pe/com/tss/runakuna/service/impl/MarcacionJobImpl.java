package pe.com.tss.runakuna.service.impl;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;
import pe.com.tss.runakuna.domain.model.entities.Licencia;
import pe.com.tss.runakuna.domain.model.entities.Marcacion;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.RegistroMarcacionEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.LicenciaEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.dto.RegistroMarcacionEmpleadoDto;
import pe.com.tss.runakuna.util.DateUtil;
import pe.com.tss.runakuna.util.StringUtil;

@Service
public class MarcacionJobImpl{
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	RegistroMarcacionEmpleadoJpaRepository registroMarcacionEmpleadoJpaRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	VacacionJpaRepository vacacionJpaRepository;
	
	@Autowired
	LicenciaEmpleadoJpaRepository licenciaJpaRepository;
	
	@Autowired
	MarcacionRepository marcacionJdbcRepository;
	
	@Autowired
	Mapper mapper;

	@Scheduled(cron="0 0/5 * 1/1 * ?")
	@Transactional
	public void integrarMarcacionesSistemaAsistencia(){
		
		List<RegistroMarcacionEmpleado> registroMarcaciones= new ArrayList<>();
		
		List<RegistroMarcacionEmpleadoDto> marcaciones = marcacionJdbcRepository.obtenerMarcacion();
		
		if(marcaciones!= null && marcaciones.size() > 0){
						
			for (RegistroMarcacionEmpleadoDto registroMarcacionEmpleadoDto : marcaciones) {
				RegistroMarcacionEmpleado entity = new RegistroMarcacionEmpleado();
				
				
				Empleado empleadoEntity = empleadoJpaRepository.findByCodigoExacto(registroMarcacionEmpleadoDto.getCodigoEmpleado());
				if(empleadoEntity != null){
					
					entity.setFecha(DateUtil.formatoFechaArchivoMarcacion(registroMarcacionEmpleadoDto.getFechaMarcacion()));
					entity.setHora(StringUtil.formatoHoraArchivoMarcacion(registroMarcacionEmpleadoDto.getHoraMarcacion()));
					entity.setCodigoEmpleado(registroMarcacionEmpleadoDto.getCodigoEmpleado());
					entity.setDni(registroMarcacionEmpleadoDto.getDniEmpleado());
					entity.setTipo(registroMarcacionEmpleadoDto.getTipoMarcacion());
					entity.setEmpleado(empleadoEntity);
					entity.setCreador("cron-user");
					entity.setFechaCreacion(new Date());
					registroMarcacionEmpleadoJpaRepository.save(entity);
					
					if("O".equals(entity.getTipo())){
						registroMarcaciones.add(entity);
					}
					
				}
			}
			
			for (RegistroMarcacionEmpleado registroMarcacion : registroMarcaciones) {
				
				Marcacion marcacion = marcacionJpaRepository.obtenerMarcacionPorIdEmpleadoyDate(registroMarcacion.getEmpleado().getIdEmpleado(), registroMarcacion.getFecha());
				if(marcacion != null){
					
					if(marcacion.getHoraIngreso() == null){
						
						marcacion.setHoraIngreso(registroMarcacion.getHora());
						marcacion.setInasistencia(0);
						
						Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngreso());
						Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
							
						fechaHoraConfig = DateUtil.addMinutes(fechaHoraConfig, 10);
							
						if(fechaHoraConfig.before(fechaHoraMarcacion)){
							marcacion.setTardanza(1);
						}
						
						BigDecimal horaDemoraEntrada = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
						
						marcacion.setDemoraEntrada(horaDemoraEntrada);
						
						marcacionJpaRepository.save(marcacion);
						
					}else if(marcacion.getHoraInicioAlmuerzo() == null){
						
						marcacion.setHoraInicioAlmuerzo(registroMarcacion.getHora());
						
						marcacionJpaRepository.save(marcacion);
						
					}else if(marcacion.getHoraFinAlmuerzo() == null){
						
						marcacion.setHoraFinAlmuerzo(registroMarcacion.getHora());
						
						Date fechaHoraInicio = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraInicioAlmuerzo());
						Date fechaHoraFin =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraFinAlmuerzo());
						
						BigDecimal horaDemoraAlmuerzo = new BigDecimal((fechaHoraFin.getTime()-fechaHoraInicio.getTime())).divide(new BigDecimal((1000*60)),2);
						
						marcacion.setDemoraAlmuerzo(horaDemoraAlmuerzo);
						
						marcacionJpaRepository.save(marcacion);
						
					}else if(marcacion.getHoraSalida() == null){
						
						marcacion.setHoraSalida(registroMarcacion.getHora());
					
						Date fechaHoraMarcacion = DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalida());
						Date fechaHoraConfig =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
						
						BigDecimal horaDemoraSalida = new BigDecimal((fechaHoraMarcacion.getTime()-fechaHoraConfig.getTime())).divide(new BigDecimal((1000*60)),2);
						
						
						marcacion.setDemoraSalida(horaDemoraSalida);
						
						BigDecimal horascal = marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasPermiso());
						horascal=horascal.add(marcacion.getHorasExtra());
							
						
						BigDecimal minutoscal = marcacion.getDemoraEntrada().add(marcacion.getDemoraAlmuerzo());
								
						minutoscal = minutoscal.subtract(marcacion.getDemoraSalida());
						
						horascal=horascal.multiply(new BigDecimal(60));
						
						BigDecimal horasReal = horascal.subtract(minutoscal);
						
						horasReal = horasReal.divide(new BigDecimal(60),2);
						
						marcacion.setHorasTrabajoReal(horasReal);
						
						marcacion.setHorasTrabajoPendiente(marcacion.getHorasTrabajoHorario().subtract(marcacion.getHorasTrabajoReal()));
						
						marcacionJpaRepository.save(marcacion);
						
					}
										
				}
				
			}
					
		}
	}
	
	
	
	@Scheduled(cron="0 30 0 1/1 * ?")
	@Transactional
	public void crearMarcacionesSistemaAsistencia(){
		
		Calendar c1 = Calendar.getInstance();
		
		List<Empleado> empleados = empleadoJpaRepository.findAll();
		
		if(empleados != null && empleados.size() > 0){
		
			Date fechaActual = new Date();
			String fechaCadena = DateUtil.fmtDt(fechaActual);
			
			for (Empleado empleado : empleados) {
				Marcacion marcacion = new Marcacion();
				marcacion.setEmpleado(empleado);
				marcacion.setFecha(DateUtil.formatoFechaArchivoMarcacion(fechaCadena));
				marcacion.setInasistencia(1);
				marcacion.setTardanza(0);
				
				HorarioEmpleado horarioEmpleado = horarioEmpleadoJpaRepository.obtenerHorarioPorFechaVigente(marcacion.getFecha(), empleado.getIdEmpleado());
				
				
				if(horarioEmpleado == null){
					continue;
				}
				
				HorarioEmpleadoDia horarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.obtenerHorarioDiaPorDiaDeSemana(horarioEmpleado.getIdHorarioEmpleado(), StringUtil.obtenerCodigoDia(c1.get(Calendar.DAY_OF_WEEK)));
				
				if(horarioEmpleadoDia == null){
					continue;
				}
				
				marcacion.setHoraIngresoHorario(horarioEmpleadoDia.getEntrada());
				marcacion.setHoraSalidaHorario(horarioEmpleadoDia.getSalida());
				
				PeriodoEmpleado periodo = periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(marcacion.getFecha(), empleado.getIdEmpleado());
				
				if(periodo == null){
					continue;
				}
				
				PermisoEmpleado permisoEmpleadoEntity = permisoEmpleadoJpaRepository.obtenerPermisoPorFechayPeriodoEmpleado(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
				
				if(permisoEmpleadoEntity != null){
					
					Date fechaHoraIngresoPermiso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleadoEntity.getHoraInicio());
					Date fechaHoraIngresoDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
					
					Date fechaHoraSalidaPermiso=  DateUtil.parse(new SimpleDateFormat("HH:mm"),permisoEmpleadoEntity.getHoraFin());
					Date fechaHoraSalidaDia=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
					
					if(fechaHoraIngresoDia.equals(fechaHoraIngresoPermiso)){
						marcacion.setHoraIngresoHorario(permisoEmpleadoEntity.getHoraFin());
					}
					
					if(fechaHoraSalidaDia.equals(fechaHoraSalidaPermiso)){
						marcacion.setHoraSalidaHorario(permisoEmpleadoEntity.getHoraInicio());
					}
					
					marcacion.setHorasPermiso(permisoEmpleadoEntity.getHoras());
				
				}else{
					marcacion.setHorasPermiso(new BigDecimal(0));
				}
				
				Date fechaHoraIngreso =  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraIngresoHorario());
				Date fechaHoraSalida=  DateUtil.parse(new SimpleDateFormat("HH:mm"),marcacion.getHoraSalidaHorario());
				
				BigDecimal horasTrabajo = new BigDecimal((fechaHoraSalida.getTime()-fechaHoraIngreso.getTime())).divide(new BigDecimal((1000*60*60)),2);
				
				horasTrabajo = horasTrabajo.subtract(new BigDecimal(-1));
				
				
				
				marcacion.setHorasTrabajoHorario(horasTrabajo);
				
				Vacacion vacacion = vacacionJpaRepository.obtenerVacaciones(marcacion.getFecha(), periodo.getIdPeriodoEmpleado());
				if(vacacion != null){
					marcacion.setVacaciones(new byte[]{1});
				}else{
					marcacion.setVacaciones(new byte[]{0});
				}
				
				Licencia licencia = licenciaJpaRepository.obtenerLicencia(marcacion.getFecha(), empleado.getIdEmpleado());
				
				if(licencia != null){
					marcacion.setLicencia(new byte[]{1});
				}else{
					marcacion.setLicencia(new byte[]{0});
				}
				
				marcacion.setHorasExtra(new BigDecimal(0));
				marcacion.setHorasTrabajoPendiente(new BigDecimal(0));
				marcacion.setHorasTrabajoReal(new BigDecimal(0));
				marcacion.setDemoraEntrada(new BigDecimal(0));
				marcacion.setDemoraAlmuerzo(new BigDecimal(0));
				marcacion.setDemoraSalida(new BigDecimal(0));
				
				marcacionJpaRepository.save(marcacion);
			}
		}
		
	}
	
	@Scheduled(cron="0 45 23 1/1 * ?")
	public void enviarAlertasMarcacionesIncorrectas(){
	//enviar alerta
		
		System.out.println("enviar alerta");
	}
	
}
