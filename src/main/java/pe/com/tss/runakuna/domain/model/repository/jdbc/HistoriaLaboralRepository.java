package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;

public interface HistoriaLaboralRepository {
	
	List<HistoriaLaboralDto> obtenerHistoriaLaboral(Long idEmpleado);
	List<HistoriaLaboralDto> obtenerIdHistoriaLaboral(Long idHistorialLaboral);
	HistoriaLaboralDto obtenerHistoriaLaboralActual(EmpleadoDto empleado);
	
	HistoriaLaboralDto obtenerUltimoCargo(Long idEmpleado);
	
	HistoriaLaboralDto obtenerPrimerCargo(Long idEmpleado);
	
	Long obtenerCantidadCargos(Long idEmpleado);
	
}
