package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;
import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.HorarioDto;
import pe.com.tss.runakuna.dto.NotificacionDto;

public interface CargoService {
	
	String save(CargoDto dto);
	
	List<CargoDto> getCargo();
	
	List<HorarioDto> getObtenerNombreHorario();
	
	Long actualizarCargo(HistoriaLaboralDto historiaLaboralDto);
	
//	Long crearCargo(HistoriaLaboralDto historiaLaboralDto);
	NotificacionDto crearCargo(HistoriaLaboralDto historiaLaboralDto);
	
	String eliminarCargo(Long idHistorialLaboral);
	

}
