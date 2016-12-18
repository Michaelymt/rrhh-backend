package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.MarcacionDto;
import pe.com.tss.runakuna.dto.RegistroMarcacionEmpleadoDto;

import java.util.List;

public interface MarcacionRepository {
	
	List<RegistroMarcacionEmpleadoDto> obtenerMarcacion();
	
	MarcacionDto obtenerMarcacionesPorEmpleadoyFechaActual(EmpleadoDto empleado);

}
