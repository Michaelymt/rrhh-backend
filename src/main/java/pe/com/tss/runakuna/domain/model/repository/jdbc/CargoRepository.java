package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.HorarioDto;

public interface CargoRepository {

	List<CargoDto> findCargo();
	List<HorarioDto> obtenerNombreHorario();
}
