package pe.com.tss.runakuna.service;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;

public interface PeriodoEmpleadoService {
	NotificacionDto registrarPeriodoEmpleado(EmpleadoDto empleadoDto);

}
