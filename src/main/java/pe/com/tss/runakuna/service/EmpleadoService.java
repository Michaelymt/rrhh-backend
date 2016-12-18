package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.BusquedaEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaHorasExtraEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaMarcacionDto;
import pe.com.tss.runakuna.dto.BusquedaPermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.BusquedaVacacionesEmpleadoDto;
import pe.com.tss.runakuna.dto.DependienteDto;
import pe.com.tss.runakuna.dto.DocumentoEmpleadoDto;
import pe.com.tss.runakuna.dto.EducacionDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.EquipoEntregadoDto;
import pe.com.tss.runakuna.dto.ExperienciaLaboralDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDiaDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDto;
import pe.com.tss.runakuna.dto.HorasExtraDto;
import pe.com.tss.runakuna.dto.LicenciaDto;
import pe.com.tss.runakuna.dto.MarcacionDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.VacacionDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;

public interface EmpleadoService {

	NotificacionDto registrarEmpleado(EmpleadoDto empleado);
	NotificacionDto registrarHorarioEmpleado(HorarioEmpleadoDto horarioEmpleado);
	NotificacionDto actualizarHorarioEmpleado(HorarioEmpleadoDto horarioEmpleado);
	NotificacionDto actualizarEmpleado(EmpleadoDto empleado);
	NotificacionDto actualizarDatosPersonales(EmpleadoDto empleado);
	List<HistoriaLaboralDto> obtenerHistoriaLaboral(Long idEmpleado);
	List<HistoriaLaboralDto> obtenerIdHistoriaLaboral(Long idHistorialLaboral);
	List<EmpleadoDto> busquedaEmpleado(BusquedaEmpleadoDto busquedaEmpleadoDto);
	List<MarcacionDto> busquedaMarcacionesEmpleado(BusquedaMarcacionDto busquedaMarcacionDto);
	EmpleadoDto verEmpleado(EmpleadoDto empleado);
	List<DocumentoEmpleadoDto> verDocumentos(EmpleadoDto empleado);
	List<EducacionDto> verEducacion(EmpleadoDto empleado);
	List<ExperienciaLaboralDto> verExperienciaLaboral(EmpleadoDto empleado);
	List<EquipoEntregadoDto> verEquipoEntregado(EmpleadoDto empleado);
	List<DependienteDto> verDependiente(EmpleadoDto empleado);
	List<LicenciaDto> verLicencia(PeriodoEmpleadoDto periodoEmpleado);
	List<EmpleadoDto> procesarMasivamenteEmpleados(List<EmpleadoDto> dtos);
	Long processDataUpdateEmpleado(EmpleadoDto dto)  throws Exception;
	List<PermisoEmpleadoDto> busquedaPermisoEmpleado(BusquedaPermisoEmpleadoDto busquedaPermisoEmpleadoDto);
	HorarioEmpleadoDto verHorarioEmpleado(EmpleadoDto empleado);
	List<HorarioEmpleadoDiaDto> obtenerHorarioEmpleadoDiasPorHorarioEmpleado(HorarioEmpleadoDto horarioEmpleadoDto);
	List<PeriodoEmpleadoDto> verPeriodoEmpleado(EmpleadoDto empleado);
	List<PermisoEmpleadoDto> verPermisoEmpleado(PeriodoEmpleadoDto periodoEmpleado);
	List<VacacionDto> verVacacion(PeriodoEmpleadoDto periodoEmpleado);
    Long eliminarPermisoEmpleado(Long idPermisoEmpleado);
    List<PermisoEmpleadoDto> obtenerCodigoPermiso(String codigo);
	List<VacacionEmpleadoDto> busquedaVacacionesEmpleado(BusquedaVacacionesEmpleadoDto busquedaVacacionesEmpleadoDto);
	List<MarcacionDto> verMarcacion(EmpleadoDto empleado);
	List<HorasExtraDto> busquedaHorasExtrasEmpleado(BusquedaHorasExtraEmpleadoDto busquedaHorasExtraEmpleadoDto);
	HorasExtraDto informacionAdicionalHorasExtras(EmpleadoDto empleado);
	NotificacionDto registrarHorasExtra(HorasExtraDto horasExtraDto);
	List<EquipoEntregadoDto> obtenerEquiposPendientesDevolucion(Long idEmpleado);
	NotificacionDto countEquiposPendientesDevolucion(EmpleadoDto empleadoDto);
	List<HorarioEmpleadoDto> verHorariosEmpleado(EmpleadoDto empleado);
	NotificacionDto registrarDarBajaEmpleado(EmpleadoDto empleado);
	Long eliminarHorasExtra(Long idHorasExtra);
	NotificacionDto rechazarHorasExtra(HorasExtraDto horasExtraDto);
	NotificacionDto aprobarHorasExtra(HorasExtraDto horasExtraDto);
}
