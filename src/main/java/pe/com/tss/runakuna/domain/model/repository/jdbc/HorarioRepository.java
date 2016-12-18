package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.dto.BusquedaHorarioDto;
import pe.com.tss.runakuna.dto.HorarioDiaDto;
import pe.com.tss.runakuna.dto.HorarioDto;

import java.util.List;

public interface HorarioRepository {
     
     List<HorarioDto> busquedaHorario(BusquedaHorarioDto busquedaHorarioDto);
     List<HorarioDto> obtenerHorariosPorTipoHorario(HorarioDto horarioDto);
     HorarioDto obtenerHorariosPorTipoHorarioyPorDefecto(HorarioDto horarioDto);
     List<HorarioDiaDto> obtenerHorarioDiaPorHorario(HorarioDto horarioDto);
     
}
