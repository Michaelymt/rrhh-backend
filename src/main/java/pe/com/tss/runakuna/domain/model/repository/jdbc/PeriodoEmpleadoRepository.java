package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;

public interface PeriodoEmpleadoRepository {
	
	PeriodoEmpleadoDto obtenerPeriodoEmpleadoActual(EmpleadoDto empleado);
}
