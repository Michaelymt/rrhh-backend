package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;

public interface VacacionEmpleadoRepository {

	VacacionEmpleadoDto obtenerDiasDisponibles(EmpleadoDto empleadoDto);

	VacacionEmpleadoDto obtenerPeriodo(EmpleadoDto empleado);

	Long obtenerCantidadVacaciones(Long idPeriodoEmpleado);

	VacacionEmpleadoDto obtenerPrimeraVacacion(Long idPeriodoEmpleado);

	VacacionEmpleadoDto obtenerUltimaVacacion(Long idPeriodoEmpleado);

	List<VacacionEmpleadoDto> allListVacacion(Long idPeriodoEmpleado);

}
