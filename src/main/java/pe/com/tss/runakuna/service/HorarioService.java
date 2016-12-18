package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.BusquedaHorarioDto;
import pe.com.tss.runakuna.dto.HorarioDiaDto;
import pe.com.tss.runakuna.dto.HorarioDto;
import pe.com.tss.runakuna.dto.NotificacionDto;

public interface HorarioService {

	List<HorarioDto> busquedaHorario(BusquedaHorarioDto busquedaHorarioDto);
	List<HorarioDto> obtenerHorariosPorTipoHorario(HorarioDto horarioDto);
	HorarioDto obtenerHorarioPorTipoHorarioPorDefecto(HorarioDto horarioDto);
	List<HorarioDiaDto> obtenerHorarioDiaPorHorario(HorarioDto horarioDto);
	List<HorarioDto> obtenerHorarios();
	
	 NotificacionDto registrarHorario(HorarioDto horarioDto);
	 
	 HorarioDto obtenerHorarioPorIdHorario(HorarioDto horarioDto);
	 
	 NotificacionDto actualizarHorario(HorarioDto horarioDto);
}
