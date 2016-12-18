package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.dto.BusquedaEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaMarcacionDto;
import pe.com.tss.runakuna.dto.BusquedaPermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.DependienteDto;
import pe.com.tss.runakuna.dto.EducacionDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.EquipoEntregadoDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDiaDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDto;
import pe.com.tss.runakuna.dto.LicenciaDto;
import pe.com.tss.runakuna.dto.MarcacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.VacacionDto;

import java.util.List;

/**
 * Created by josediaz on 2/11/2016.
 */
public interface EmpleadoRepository {
     List<EmpleadoDto> busquedaEmpleado(BusquedaEmpleadoDto busquedaEmpleadoDto);
     
     EmpleadoDto verEmpleado(EmpleadoDto empleadoDto);
     List<EducacionDto> verEducacion(EmpleadoDto empleadoDto);
     List<EquipoEntregadoDto> verEquipoEntregado(EmpleadoDto empleadoDto);
     List<DependienteDto> verDependiente(EmpleadoDto empleadoDto);
     List<LicenciaDto> verLicencia(PeriodoEmpleadoDto periodoEmpleado);
     List<PermisoEmpleadoDto> busquedaPermisoEmpleado(BusquedaPermisoEmpleadoDto busquedaPermisoEmpleadoDto);
     List<MarcacionDto> busquedaMarcacionEmpleado(BusquedaMarcacionDto busquedaMarcacionDto);
     HorarioEmpleadoDto verHorarioEmpleado(EmpleadoDto empleadoDto);
     List<HorarioEmpleadoDto> verHorariosEmpleado(EmpleadoDto empleadoDto);
     List<HorarioEmpleadoDiaDto> verHorarioEmpleadoDia(HorarioEmpleadoDto horarioEmpleadoDto);
     List<PeriodoEmpleadoDto> verPeriodoEmpleado(EmpleadoDto empleadoDto);
     List<PermisoEmpleadoDto> verPermisoEmpleado(PeriodoEmpleadoDto periodoEmpleado);
     List<VacacionDto> verVacaciones(PeriodoEmpleadoDto periodoEmpleado);
     List<BusquedaPermisoEmpleadoDto> autocompleteEmpleado(String search);
     List<MarcacionDto> verMarcaciones(EmpleadoDto empleado);

}
