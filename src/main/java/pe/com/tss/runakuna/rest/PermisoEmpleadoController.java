package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.BusquedaPermisoEmpleadoDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PermisoEmpleadoDto;
import pe.com.tss.runakuna.service.PermisoEmpleadoService;

@RestController
@RequestMapping(value = "/permisoEmpleado")
public class PermisoEmpleadoController {
	
	@Autowired
	PermisoEmpleadoService permisoEmpleadoService;

	@RequestMapping(value = "/registrarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleado) {
		
		NotificacionDto dto = permisoEmpleadoService.registrarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/actualizarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto actualizarPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleado) {
		
		NotificacionDto dto = permisoEmpleadoService.actualizarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/enviarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto enviarPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleado) {
		
		NotificacionDto dto = permisoEmpleadoService.enviarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/devolverPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto devolverPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleado) {
		
		NotificacionDto dto = permisoEmpleadoService.devolverPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/aprobarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto aprobarPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleado) {
		
		NotificacionDto dto = permisoEmpleadoService.aprobarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/rechazarPermisoEmpleado", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto rechazarPermisoEmpleado(@RequestBody PermisoEmpleadoDto permisoEmpleado) {
		
		NotificacionDto dto = permisoEmpleadoService.rechazarPermisoEmpleado(permisoEmpleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerHistoriaLaboralActual", method = RequestMethod.POST)
	public @ResponseBody HistoriaLaboralDto obtenerHistoriaLaboralActual(@RequestBody EmpleadoDto empleado) {
		HistoriaLaboralDto dto = permisoEmpleadoService.obtenerHistoriaLaboralActual(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/obtenerPeriodoEmpleadoActual", method = RequestMethod.POST)
	public @ResponseBody PeriodoEmpleadoDto obtenerPeriodoEmpleadoActual(@RequestBody EmpleadoDto empleado) {
		PeriodoEmpleadoDto dto = permisoEmpleadoService.obtenerPeriodoEmpleadoActual(empleado);
		return dto;
	}
	
	@RequestMapping(value = "/autocompleteEmpleado", method = RequestMethod.GET)
	public @ResponseBody List<BusquedaPermisoEmpleadoDto> autocompleteEmpleado(@RequestParam(name="search") String search){
		List<BusquedaPermisoEmpleadoDto> dto = permisoEmpleadoService.autocompleteEmpleado(search);
		return dto;
	}

}