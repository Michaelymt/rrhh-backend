package pe.com.tss.runakuna.service.impl;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.MarcacionRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.dto.*;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;

import java.util.Date;

@Service
public class MarcacionEmpleadoServiceImpl implements MarcacionEmpleadoService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	MarcacionRepository marcacionJdbcRepository;
	
	@Autowired
	SolicitudCambioMarcacionJpaRepository solicitudCambioMarcacionJdbcRepository;
	

	private static final Logger log = LoggerFactory.getLogger(MarcacionEmpleadoServiceImpl.class);

	@Override
	public MarcacionDto obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoDto empleado) {
		
		MarcacionDto dto = marcacionJdbcRepository.obtenerMarcacionesPorEmpleadoyFechaActual(empleado);
		
		return dto;
	}

	@Override
	public NotificacionDto registrarSolicitudCambioMarcacion(SolicitudCambioMarcacionDto solicitudCambioMarcacion) {
		
		NotificacionDto notificacion = new NotificacionDto();
		
		SolicitudCambioMarcacion entity = new SolicitudCambioMarcacion();
		
		Marcacion marcacion = marcacionJpaRepository.findOne(solicitudCambioMarcacion.getMarcacion().getIdMarcacion());
		mapper.map(solicitudCambioMarcacion, entity);
		
		entity.setMarcacion(marcacion);
		
		solicitudCambioMarcacionJdbcRepository.save(entity);
		solicitudCambioMarcacionJdbcRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente.");
		return notificacion;
	}
	
	
}
