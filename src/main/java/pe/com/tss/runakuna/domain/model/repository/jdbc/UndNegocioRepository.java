package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.UndNegocioDto;

public interface UndNegocioRepository {

	List<UndNegocioDto> findUndNegocio();
	
	List<CargoDto> findListCargos();
}
