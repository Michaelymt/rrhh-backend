package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.DepartamentoDto;
import pe.com.tss.runakuna.dto.DistritoDto;
import pe.com.tss.runakuna.dto.PaisDto;
import pe.com.tss.runakuna.dto.ProvinciaDto;
import pe.com.tss.runakuna.service.DepartamentoService;
import pe.com.tss.runakuna.service.DistritoService;
import pe.com.tss.runakuna.service.PaisService;
import pe.com.tss.runakuna.service.ProvinciaService;

@RestController
@RequestMapping(value="/pais")
@CrossOrigin()
public class PaisController {

	@Autowired
	PaisService paisService;
	
	@Autowired
	DepartamentoService departamentoService;
	
	@Autowired
	ProvinciaService provinciaService;
	
	@Autowired
	DistritoService distritoService;
	
	private List<PaisDto> resultList = new ArrayList<>();

	@RequestMapping(value = "/obtenerPaises", method = RequestMethod.GET)
	public @ResponseBody List<PaisDto> obtenerPaises() {
		
		resultList = paisService.obtenerPaises();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerDepartamentos", method = RequestMethod.GET)
	public @ResponseBody List<DepartamentoDto> obtenerDepartamentos(@RequestParam(name="codigoPais")  String codigoPais) {
		List<DepartamentoDto> result = null;
		result = departamentoService.obtenerDepartamentosPorPais(codigoPais);
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	@RequestMapping(value = "/obtenerProvincias", method = RequestMethod.GET)
	public @ResponseBody List<ProvinciaDto> obtenerProvincias(@RequestParam(name="codigoDpto")  String codigoDpto) {
		List<ProvinciaDto> result = null;
		result = provinciaService.obtenerProvinciasPorDepartamento(codigoDpto);
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	@RequestMapping(value = "/obtenerDistritos", method = RequestMethod.GET)
	public @ResponseBody List<DistritoDto> obtenerDistritos(@RequestParam(name="codigoProvincia")  String codigoProvincia) {
		List<DistritoDto> result = null;
		result = distritoService.obtenerDistritosPorProvincia(codigoProvincia);
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
	
	
}
