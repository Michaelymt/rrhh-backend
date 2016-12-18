package pe.com.tss.runakuna.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.VacacionEmpleadoDto;
import pe.com.tss.runakuna.service.VacacionEmpleadoService;

@RestController
@RequestMapping(value = "/vacacionEmpleado")
public class VacacionController {
	
	@Autowired
	VacacionEmpleadoService vacacionEmpleadoService;
	
	@RequestMapping(value = "/obtenerDiasDisponibles", method = RequestMethod.POST)
	public @ResponseBody VacacionEmpleadoDto obtenerDiasDisponibles(@RequestBody EmpleadoDto empleado) {
		VacacionEmpleadoDto dto = vacacionEmpleadoService.obtenerDiasDisponibles(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerPeriodo", method = RequestMethod.POST)
	public @ResponseBody VacacionEmpleadoDto obtenerPeriodo(@RequestBody EmpleadoDto empleado) {
		VacacionEmpleadoDto dto = vacacionEmpleadoService.obtenerPeriodo(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/registrarVacaciones", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarVacaciones(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {

		NotificacionDto dto = vacacionEmpleadoService.registrarVacaciones(vacacionEmpleadoDto);
		return dto;

	}
	@RequestMapping(value = "/actualizarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto actualizarVacacionEmpleado(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto dto = vacacionEmpleadoService.actualizarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/enviarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto enviarVacacionEmpleado(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto dto = vacacionEmpleadoService.enviarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/eliminarVacacionEmpleado", method = RequestMethod.POST)
	public ResponseEntity<String> eliminarVacacionEmpleado(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		Long idVacacion = vacacionEmpleadoService.eliminarVacacionEmpleado(vacacionEmpleadoDto.getIdVacacion());
		return new ResponseEntity<String>(idVacacion.toString(),HttpStatus.OK);
	}
	@RequestMapping(value = "/devolverVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto devolverVacacionEmpleado(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto dto = vacacionEmpleadoService.devolverVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/aprobarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto aprobarVacacionEmpleado(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto dto = vacacionEmpleadoService.aprobarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}
	@RequestMapping(value = "/rechazarVacacionEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto rechazarVacacionEmpleado(@RequestBody VacacionEmpleadoDto vacacionEmpleadoDto) {
		
		NotificacionDto dto = vacacionEmpleadoService.rechazarVacacionEmpleado(vacacionEmpleadoDto);
		return dto;
	}

}
