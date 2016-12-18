package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.BusquedaLicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.LicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;


public interface LicenciaEmpleadoService {

	NotificacionDto registrarLicenciaEmpleado(LicenciaEmpleadoDto licenciaEmpleadoDto);
	NotificacionDto actualizarLicenciaEmpleado(LicenciaEmpleadoDto licenciaEmpleadoDto);
	NotificacionDto aprobarLicenciaEmpleado(LicenciaEmpleadoDto licenciaEmpleadoDto);
	Long eliminarLicenciaEmpleado(Long idLicencia);
	List<LicenciaEmpleadoDto> obtenerLicencias(BusquedaLicenciaEmpleadoDto busquedaLicenciaEmpleadoDto);
	LicenciaEmpleadoDto obtenerLicencia(Long idLicencia);
	
}
