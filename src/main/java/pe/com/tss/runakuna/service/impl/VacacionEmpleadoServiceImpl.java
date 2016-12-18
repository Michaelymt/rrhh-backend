package pe.com.tss.runakuna.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Vacacion;
import pe.com.tss.runakuna.domain.model.repository.jdbc.VacacionEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.VacacionJpaRepository;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.service.VacacionEmpleadoService;

@Service
public class VacacionEmpleadoServiceImpl implements VacacionEmpleadoService{

	@Autowired
	VacacionEmpleadoRepository vacacionEmpleadoRepository;
	
	@Autowired
	VacacionJpaRepository vacacionJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Override
	public VacacionEmpleadoDto obtenerDiasDisponibles(EmpleadoDto empleadoDto) {
		VacacionEmpleadoDto dto = new VacacionEmpleadoDto();
		
		dto = vacacionEmpleadoRepository.obtenerDiasDisponibles(empleadoDto);
		return dto;
	}
	
	@Override
	public NotificacionDto registrarVacaciones(VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto notificacion = new NotificacionDto();
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		if(periodoEmpleado.getDiasVacacionesDisponibles() > 0 && periodoEmpleado.getPermisosDisponibles() > 0){
		
			List<VacacionEmpleadoDto> listVacacion = vacacionEmpleadoRepository.allListVacacion(vacacionEmpleadoDto.getIdPeriodoEmpleado());
			
			for(VacacionEmpleadoDto next: listVacacion){
				boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
				boolean isTrueFin 	 = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
				if(isTrueInicio==true || isTrueFin==true){
					String resultInicio,resultFin;
					SimpleDateFormat formatter;

					formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
					resultInicio = formatter.format(next.getFechaInicio());
					resultFin = formatter.format(next.getFechaFin());

					notificacion.setCodigo(0L);
					notificacion.setMensaje("No es posible registrar, la vacación se cruza con el código: " +
											next.getIdVacacion() +" Desde: "+resultInicio +" Hasta: "+resultFin);
					return notificacion;
				}else{
					continue;
				}
					
			}
			Vacacion entity;
			if(vacacionEmpleadoDto.getIdVacacion() == null)
				entity = new Vacacion();
			else entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
			
			
			entity.setPeriodoEmpleado(periodoEmpleado);
			
			Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdEmpleado());
			entity.setEmpleado(empleado);
			
			entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
			entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
			entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
			entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
			entity.setEstado("P");
			
			vacacionJpaRepository.save(entity);
			
			periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasHabiles());
			periodoEmpleado.setPermisosDisponibles(periodoEmpleado.getPermisosDisponibles()-1);
			periodoEmpleadoJpaRepository.save(periodoEmpleado);
			
			notificacion.setCodigo(1L);
			notificacion.setMensaje("Se registro correctamente");
			return notificacion;
		}else{
			notificacion.setCodigo(0L);
			notificacion.setMensaje("No es posible registrar, no hay Días disponibles o Permisos disponibles");
			return notificacion;
		}
		
	}

	@Override
	public VacacionEmpleadoDto obtenerPeriodo(EmpleadoDto empleado) {
		VacacionEmpleadoDto dto = new VacacionEmpleadoDto();
		
		dto = vacacionEmpleadoRepository.obtenerPeriodo(empleado);
		return dto;
	}

	@Override
	public NotificacionDto actualizarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto notificacion = new NotificacionDto();
		PeriodoEmpleado periodoEmpleado = periodoEmpleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdPeriodoEmpleado());
		
//		if(periodoEmpleado.getDiasVacacionesDisponibles() > 0 && periodoEmpleado.getPermisosDisponibles() > 0){
		
			List<VacacionEmpleadoDto> listVacacion = vacacionEmpleadoRepository.allListVacacion(vacacionEmpleadoDto.getIdPeriodoEmpleado());
			
			for(VacacionEmpleadoDto next: listVacacion){
				boolean isTrueInicio = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaInicio(),next.getFechaInicio(),next.getFechaFin());
				boolean isTrueFin = ValidationUtils.isWithinRange(vacacionEmpleadoDto.getFechaFin(),next.getFechaInicio(),next.getFechaFin());
				if(isTrueInicio==true || isTrueFin==true){
					String resultInicio,resultFin;
					SimpleDateFormat formatter;

					formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
					resultInicio = formatter.format(next.getFechaInicio());
					resultFin = formatter.format(next.getFechaFin());

					notificacion.setCodigo(0L);
					notificacion.setMensaje("No es posible registrar, la vacación se cruza con el código: " +
											next.getIdVacacion() +" Desde: "+resultInicio +" Hasta: "+resultFin);
					return notificacion;
				}else{
					continue;
				}
					
			}
		
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		
		entity.setPeriodoEmpleado(periodoEmpleado);
		
		Empleado empleado = empleadoJpaRepository.findOne(vacacionEmpleadoDto.getIdEmpleado());
		entity.setEmpleado(empleado);
		
		entity.setFechaInicio(vacacionEmpleadoDto.getFechaInicio());
		entity.setFechaFin(vacacionEmpleadoDto.getFechaFin());
		entity.setDiasCalendarios(vacacionEmpleadoDto.getDiasCalendarios());
		entity.setDiasHabiles(vacacionEmpleadoDto.getDiasHabiles());
		entity.setEstado("P");
		
		vacacionJpaRepository.save(entity);
		
		periodoEmpleado.setDiasVacacionesDisponibles(periodoEmpleado.getDiasVacacionesDisponibles()-vacacionEmpleadoDto.getDiasHabiles());
		periodoEmpleado.setPermisosDisponibles(periodoEmpleado.getPermisosDisponibles()-1);
		periodoEmpleadoJpaRepository.save(periodoEmpleado);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se actualizo correctamente");
		return notificacion;
		
//		}else{
//			notificacion.setCodigo(0L);
//			notificacion.setMensaje("No es posible registrar, no hay Días disponibles o Permisos disponibles");
//			return notificacion;
//		}
	}

	@Override
	public NotificacionDto enviarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto) {
		NotificacionDto notificacion = new NotificacionDto();
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		entity.setEstado("E");
		vacacionJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue enviado correctamente");
		return notificacion;
	}

	@Override
	public Long eliminarVacacionEmpleado(Long idVacacion) {

		if (vacacionJpaRepository.findOne(idVacacion) == null) {
			throw new GenericRestException("ERROR", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			vacacionJpaRepository.delete(idVacacion);
			vacacionJpaRepository.flush();
		} catch (Exception e) {
			throw new GenericRestException("ERR_001", "Restriction is Being used, Can't be deleted");
		}
		
		return idVacacion;
		
	}

	@Override
	public NotificacionDto devolverVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto) {
		NotificacionDto notificacion = new NotificacionDto();
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		entity.setEstado("P");
		vacacionJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue devuelto correctamente");
		return notificacion;
	}

	@Override
	public NotificacionDto aprobarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto) {
		NotificacionDto notificacion = new NotificacionDto();
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		entity.setEstado("A");
		vacacionJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue aprobado correctamente");
		return notificacion;
	}

	@Override
	public NotificacionDto rechazarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto) {
		NotificacionDto notificacion = new NotificacionDto();
		Vacacion entity = vacacionJpaRepository.findOne(vacacionEmpleadoDto.getIdVacacion());
		entity.setEstado("R");
		vacacionJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue rechazado");
		return notificacion;
	}

}
