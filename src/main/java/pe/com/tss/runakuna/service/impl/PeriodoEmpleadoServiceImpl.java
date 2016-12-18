package pe.com.tss.runakuna.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.ConfiguracionSistema;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;

@Service
public class PeriodoEmpleadoServiceImpl  implements PeriodoEmpleadoService{

	@Autowired
	ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Override
	public NotificacionDto registrarPeriodoEmpleado(EmpleadoDto empleadoDto) {
		NotificacionDto notificacionDto=new NotificacionDto();
		
		ConfiguracionSistema cadenaDiasCrearPeriodoEmpleado = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.PeriodoEmpleado");
		String diasCrearPeriodoEmpleado=cadenaDiasCrearPeriodoEmpleado.getValor();
		PeriodoEmpleado periodoEmpleado=periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(new Date(), empleadoDto.getIdEmpleado());
		try {
			if(periodoEmpleado==null) {
				crearPeriodoEmpleado(empleadoDto,null);
				notificacionDto.setCodigo(1L);
				notificacionDto.setMensaje("Se crea un nuevo periodoEmpleado");
			} else if(verificarPeriodoEmpleadoNuevo(empleadoDto,diasCrearPeriodoEmpleado)){
				
				notificacionDto.setCodigo(1L);
				notificacionDto.setMensaje("Ya tiene periodoEmpleado creado y activo");
				
			} else {
				Date fechaFinPeriodo=periodoEmpleado.getFechaFin();
				Calendar calendar=Calendar.getInstance();
				calendar.setTime(fechaFinPeriodo);
				calendar.add(Calendar.DAY_OF_YEAR, -1*(Integer.parseInt(diasCrearPeriodoEmpleado)));
				Calendar hoy=Calendar.getInstance();
				hoy.setTime(new Date());
				if(!hoy.before(calendar.getTime())) {
					Calendar calendarFechaInicioPeriodo=Calendar.getInstance();
					calendarFechaInicioPeriodo.setTime(fechaFinPeriodo);
					calendarFechaInicioPeriodo.add(Calendar.DAY_OF_YEAR, 1);
					crearPeriodoEmpleado(empleadoDto,calendarFechaInicioPeriodo.getTime());
					notificacionDto.setCodigo(1L);
					notificacionDto.setMensaje("Se genero un nuevo periodoEmpleado, a partir de un periodo existente");
				}
			}
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setMensaje("No es posible crear periodoEmpleado, "+e.getMessage());
		}
		
		return notificacionDto;
	}
	
	private boolean verificarPeriodoEmpleadoNuevo(EmpleadoDto empleadoDto, String diasCrearPeriodoEmpleado) {
		Boolean result=false;
		Calendar calendar=Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DAY_OF_YEAR, Integer.parseInt(diasCrearPeriodoEmpleado));
		Date fecha=calendar.getTime();
		PeriodoEmpleado periodoEmpleado=periodoEmpleadoJpaRepository.obtenerPeriodoEmpleadoPorFechayEmpleado(fecha, empleadoDto.getIdEmpleado());
		if(periodoEmpleado!=null) {
			result=true;
		}
		return result;
	}

	private void crearPeriodoEmpleado(EmpleadoDto empleadoDto, Date fechaInicioPeriodo) {
		ConfiguracionSistema cadenaMaxPermi = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.Vacaciones");
		ConfiguracionSistema cadenaMaxVacacion = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("GestionDeTiempo.PermisosPermitidos");
		
		PeriodoEmpleado periodoEmpleado=new PeriodoEmpleado();
		Empleado empleado=empleadoJpaRepository.findOne(empleadoDto.getIdEmpleado());
		periodoEmpleado.setEmpleado(empleado);
		Date fechaInicio=empleado.getFechaIngreso();
		Calendar calendar=Calendar.getInstance();
		if(fechaInicioPeriodo==null)
			calendar.setTime(fechaInicio);
		else
			calendar.setTime(fechaInicioPeriodo);
		calendar.add(Calendar.MONTH, 12);
		calendar.add(Calendar.DAY_OF_YEAR, -1);
		Date fechaFin =calendar.getTime();
		if(fechaInicioPeriodo==null)
			periodoEmpleado.setFechaInicio(fechaInicio);
		else
			periodoEmpleado.setFechaInicio(fechaInicioPeriodo);
		periodoEmpleado.setFechaFin(fechaFin);
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		String fechaInicioCadena=sdf.format(periodoEmpleado.getFechaInicio());
		String fechaFinCadena=sdf.format(periodoEmpleado.getFechaFin());
		periodoEmpleado.setPeriodo(fechaInicioCadena+"-"+fechaFinCadena);
		periodoEmpleado.setMaxDiasVacaciones(Integer.parseInt(cadenaMaxVacacion.getValor()));
		periodoEmpleado.setDiasVacacionesDisponibles(Integer.parseInt(cadenaMaxVacacion.getValor()));
		periodoEmpleado.setMaxPermisos(Integer.parseInt(cadenaMaxPermi.getValor()));
		periodoEmpleado.setPermisosDisponibles(Integer.parseInt(cadenaMaxPermi.getValor()));
		periodoEmpleadoJpaRepository.save(periodoEmpleado);
		
	}

	
	
}
