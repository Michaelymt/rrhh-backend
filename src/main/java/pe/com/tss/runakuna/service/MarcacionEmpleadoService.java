package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.MarcacionDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.SolicitudCambioMarcacionDto;

public interface MarcacionEmpleadoService {

	MarcacionDto obtenerMarcacionPorEmpleadoyFechaActual(EmpleadoDto empleado);
	
	NotificacionDto registrarSolicitudCambioMarcacion(SolicitudCambioMarcacionDto solicitudCambioMarcacion);
	
}
