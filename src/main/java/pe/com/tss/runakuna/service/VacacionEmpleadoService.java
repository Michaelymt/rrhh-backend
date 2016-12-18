package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;

public interface VacacionEmpleadoService {
	
	VacacionEmpleadoDto obtenerDiasDisponibles(EmpleadoDto empleadoDto);

	NotificacionDto registrarVacaciones(VacacionEmpleadoDto vacacionEmpleadoDto);

	VacacionEmpleadoDto obtenerPeriodo(EmpleadoDto empleado);

	NotificacionDto actualizarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto);

	NotificacionDto enviarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto);

	Long eliminarVacacionEmpleado(Long idVacacion);

	NotificacionDto devolverVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto);

	NotificacionDto aprobarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto);

	NotificacionDto rechazarVacacionEmpleado(VacacionEmpleadoDto vacacionEmpleadoDto);

}
