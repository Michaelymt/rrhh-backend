package pe.com.tss.runakuna.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.tss.runakuna.dto.*;
import pe.com.tss.runakuna.service.HorarioService;

import java.util.*;

@RestController
@RequestMapping(value = "/horario")
public class HorarioController {
	
	private static final Logger LOG = LoggerFactory.getLogger(HorarioController.class);

	@Autowired
	HorarioService horarioService;

	@RequestMapping(value = "/busquedaHorario", method = RequestMethod.POST)
	public @ResponseBody  ResponseEntity<List<HorarioDto>>  busquedaEmpleado(@RequestBody BusquedaHorarioDto busquedaHorarioDto) {

		List<HorarioDto> result = new ArrayList<>();
		result = horarioService.busquedaHorario(busquedaHorarioDto);
		if(result == null)
			result = new ArrayList<>();
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/obtenerHorariosPorTipoHorario", method = RequestMethod.POST)
	public @ResponseBody  List<HorarioDto>  obtenerHorariosPorTipoHorario(@RequestBody HorarioDto horarioDto) {

		List<HorarioDto> result = new ArrayList<>();
		result = horarioService.obtenerHorariosPorTipoHorario(horarioDto);
		return result;

	}
	
	@RequestMapping(value = "/obtenerHorarioPorTipoHorarioPorDefecto", method = RequestMethod.POST)
	public @ResponseBody  HorarioDto  obtenerHorarioPorTipoHorarioPorDefecto(@RequestBody HorarioDto horarioDto) {
		HorarioDto result = new HorarioDto();
		result = horarioService.obtenerHorarioPorTipoHorarioPorDefecto(horarioDto);
		return result;

	}
	
	@RequestMapping(value = "/obtenerHorarioDiaPorHorario", method = RequestMethod.POST)
	public @ResponseBody List<HorarioDiaDto> obtenerHorarioDiaPorHorario(@RequestBody HorarioDto horarioDto) {
		List<HorarioDiaDto> result = new ArrayList<>();
		result = horarioService.obtenerHorarioDiaPorHorario(horarioDto);
		return result;

	}
	
	@RequestMapping(value = "/registrarHorario", method = RequestMethod.POST)
	public @ResponseBody  NotificacionDto  registrarHorario(@RequestBody HorarioDto horarioDto) {

		NotificacionDto dto = new NotificacionDto();
		if(horarioDto.getIdHorario() == null){
			dto = horarioService.registrarHorario(horarioDto);
		}else{
			dto = horarioService.actualizarHorario(horarioDto);
		}
		
		return dto;
	}
	
	@RequestMapping(value = "/obtenerHorario", method = RequestMethod.POST)
	public @ResponseBody  HorarioDto  obtenerHorario(@RequestBody HorarioDto horarioDto) {
		HorarioDto result = new HorarioDto();
		result = horarioService.obtenerHorarioPorIdHorario(horarioDto);
		return result;

	}
	
	@RequestMapping(value = "/obtenerHorarios", method = RequestMethod.POST)
	public @ResponseBody List<HorarioDto> obtenerHorarios() {
		List<HorarioDto> result = new ArrayList<>();
		result = horarioService.obtenerHorarios();
		return result;

	}
	
}