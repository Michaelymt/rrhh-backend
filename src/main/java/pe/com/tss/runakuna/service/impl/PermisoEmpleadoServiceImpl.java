package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.dto.BusquedaPermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;
import pe.com.tss.runakuna.service.PermisoEmpleadoService;


@Service
public class PermisoEmpleadoServiceImpl implements PermisoEmpleadoService {

	@Autowired
	PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	EmpleadoRepository empleadoRepository;

	@Autowired
	Mapper mapper;
	
	@Override
	public NotificacionDto enviarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado) {

		NotificacionDto notificacion = new NotificacionDto();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		System.out.println(">>>>>>>>>IdPermisoEmpleado: " +permisoEmpleado.getIdPermisoEmpleado());
		entity.setEstado("E");
		permisoEmpleadoJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue enviado correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionDto devolverPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado) {
		NotificacionDto notificacion = new NotificacionDto();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		System.out.println(">>>>>>>>>IdPermisoEmpleado: " +permisoEmpleado.getIdPermisoEmpleado());
		entity.setEstado("P");
		permisoEmpleadoJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue devuelto correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionDto aprobarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado) {
		NotificacionDto notificacion = new NotificacionDto();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		System.out.println(">>>>>>>>>IdPermisoEmpleado: " +permisoEmpleado.getIdPermisoEmpleado());
		entity.setEstado("A");
		permisoEmpleadoJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue aprobado correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionDto rechazarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado) {
		NotificacionDto notificacion = new NotificacionDto();
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		System.out.println(">>>>>>>>>IdPermisoEmpleado: " +permisoEmpleado.getIdPermisoEmpleado());
		entity.setEstado("R");
		permisoEmpleadoJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue rechazado correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionDto actualizarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado) {

		NotificacionDto notificacion = new NotificacionDto();
		
		PermisoEmpleado entity = permisoEmpleadoJpaRepository.findOne(permisoEmpleado.getIdPermisoEmpleado());
		System.out.println(">>>>>>>>>IdPermisoEmpleado: " +permisoEmpleado.getIdPermisoEmpleado());
		
		entity.setMotivo(permisoEmpleado.getMotivo());
		entity.setRazon(permisoEmpleado.getRazon());
		entity.setFecha(permisoEmpleado.getFecha());
		entity.setHoraInicio(permisoEmpleado.getHoraInicio());
		entity.setHoraFin(permisoEmpleado.getHoraFin());
		entity.setEstado(permisoEmpleado.getEstado());
		entity.setHoras(permisoEmpleado.getHoras());
		entity.setFechaRecuperacion(permisoEmpleado.getFechaRecuperacion());
		entity.setHoraInicioRecuperacion(permisoEmpleado.getHoraInicioRecuperacion());
		entity.setHoraFinRecuperacion(permisoEmpleado.getHoraFinRecuperacion());
		//entity.setCodigo(permisoEmpleado.getCodigo());
		
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
		entity.setPeriodoEmpleado(periodoEntity);

		Empleado empleado = empleadoJpaRepository.findOne((long) 477);
		entity.setEmpleado(empleado);
		
		permisoEmpleadoJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se actualizo el código: "+permisoEmpleado.getIdPermisoEmpleado());
		return notificacion;
	}
	
	@Override
	public NotificacionDto registrarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado) {
		NotificacionDto notificacion = new NotificacionDto();
		
		//List<PermisoEmpleado> lista = permisoEmpleadoJpaRepository.buscarPermisoPorFecha(permisoEmpleado.getFecha(), permisoEmpleado.getHoraInicio(), permisoEmpleado.getHoraFin());
		
		List<PermisoEmpleado> lista = permisoEmpleadoJpaRepository.buscarPermisoPorFecha(permisoEmpleado.getFecha());
		
		if(lista != null && lista.size() >0){
			notificacion.setCodigo(0L);
			notificacion.setMensaje("La Fecha del Permiso ya existe.");
			return notificacion;
		}
		
		
		PermisoEmpleado entity;
		entity = mapper.map(permisoEmpleado, PermisoEmpleado.class);
		
		
		
		
		PeriodoEmpleado periodoEntity = periodoEmpleadoJpaRepository.findOne(permisoEmpleado.getPeriodoEmpleado().getIdPeriodoEmpleado());
		
		entity.setPeriodoEmpleado(periodoEntity);
		
		entity.setEstado("P");
		permisoEmpleadoJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro correctamente");
		return notificacion;
	}

	@Override
	public HistoriaLaboralDto obtenerHistoriaLaboralActual(EmpleadoDto empleadoDto) {
		HistoriaLaboralDto dto = new HistoriaLaboralDto();
		
		dto = historiaLaboralRepository.obtenerHistoriaLaboralActual(empleadoDto);
		return dto;
	}

	@Override
	public PeriodoEmpleadoDto obtenerPeriodoEmpleadoActual(EmpleadoDto empleadoDto) {
		PeriodoEmpleadoDto dto = new PeriodoEmpleadoDto();
		
		dto = periodoEmpleadoRepository.obtenerPeriodoEmpleadoActual(empleadoDto);
		return dto;
	}
	
	@Override
	public  List<BusquedaPermisoEmpleadoDto> autocompleteEmpleado(String search) {
		List<BusquedaPermisoEmpleadoDto> dto = new ArrayList<>();
		
		dto = empleadoRepository.autocompleteEmpleado(search);
		return dto;
	}
	

}
