package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.BusquedaPermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;

public interface PermisoEmpleadoService {

	NotificacionDto registrarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado);
	
	NotificacionDto actualizarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado);
	
	NotificacionDto enviarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado);
	
	NotificacionDto devolverPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado);
	
	NotificacionDto aprobarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado);
	
	NotificacionDto rechazarPermisoEmpleado(PermisoEmpleadoDto permisoEmpleado);
	
	HistoriaLaboralDto obtenerHistoriaLaboralActual(EmpleadoDto empleadoDto);
	
	PeriodoEmpleadoDto obtenerPeriodoEmpleadoActual(EmpleadoDto empleadoDto);
	
	List<BusquedaPermisoEmpleadoDto> autocompleteEmpleado(String search);
	
}
