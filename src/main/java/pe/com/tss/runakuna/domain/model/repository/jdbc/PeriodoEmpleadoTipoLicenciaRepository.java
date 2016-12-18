package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.PeriodoEmpleadoTipoLicenciaDto;

public interface PeriodoEmpleadoTipoLicenciaRepository {
	
	List<PeriodoEmpleadoTipoLicenciaDto> obtenerDiasPorPeriodoEmpleadoTipoLicencia(PeriodoEmpleadoTipoLicenciaDto periodoEmpleadoTipoLicenciaDto);
}
