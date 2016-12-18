package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.DepartamentoAreaDto;
import pe.com.tss.runakuna.dto.DepartamentoDto;
import pe.com.tss.runakuna.dto.ProyectoDto;
import pe.com.tss.runakuna.dto.UndNegocioDto;
import pe.com.tss.runakuna.service.DepartamentoService;
import pe.com.tss.runakuna.service.UndNegocioService;

@CrossOrigin
@RestController
@RequestMapping("/undNegocio")
public class UnidadNegocioController {
	
	private final Logger LOG = LoggerFactory.getLogger(UnidadNegocioController.class);
	
	@Autowired
	UndNegocioService undNegocioService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@RequestMapping(value = "/obtenerUndNegocio", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<UndNegocioDto> getUndNegocio(){
		return undNegocioService.getUndNegocio();
	}
	
	@RequestMapping(value = "/obtenerDepaArea", 
							 method = RequestMethod.GET, 
							 produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DepartamentoAreaDto>> obtenerDepartamentos(
									@RequestParam(name="idUnidadDeNegocio", required = true)  Long idUnidadDeNegocio){
		List<DepartamentoAreaDto> result = new ArrayList<>();
		result = departamentoService.obtenerDepaAreaPorUndNegocio(idUnidadDeNegocio);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerDepartamentos: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obtenerProyecto", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<ProyectoDto> obtenerProyectos(@RequestParam(name="idDepartamentoArea")  Long idDepartamentoArea){
		List<ProyectoDto> result = null;
		result = departamentoService.obtenerProyPorDepaArea(idDepartamentoArea);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerProyectos: " + result);
		return result;
	}
	
	@RequestMapping(value = "/obtenerCargo", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CargoDto> obtenerCargos(@RequestParam(name="idProyecto")  Long idProyecto){
		List<CargoDto> result = null;
		result = departamentoService.obtenerCargoPorProy(idProyecto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerCargoPorProy: " + result);
		return result;
	}
	
	@RequestMapping(value = "/obtenerListCargos", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<CargoDto> getListCargos(){
		return undNegocioService.getListCargos();
	}
	
	
	@RequestMapping(value = "/obtenerProyectos", method = RequestMethod.GET)
	public @ResponseBody List<ProyectoDto> obtenerProyectoss(){
		List<ProyectoDto> result = null;
		result = departamentoService.obtenerProyectos();
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerProyectos: " + result);
		return result;
	}

}
