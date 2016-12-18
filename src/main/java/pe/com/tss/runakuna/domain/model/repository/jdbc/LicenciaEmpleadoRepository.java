package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.BusquedaLicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.LicenciaEmpleadoDto;

public interface LicenciaEmpleadoRepository {

	public List<LicenciaEmpleadoDto> obtenerLicencias(BusquedaLicenciaEmpleadoDto busquedaLicenciaEmpleadoDto);
	
	
}
