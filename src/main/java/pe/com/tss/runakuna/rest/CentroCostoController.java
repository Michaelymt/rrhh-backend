package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.CentroCostoDto;
import pe.com.tss.runakuna.service.CentroCostoService;

@RestController
@RequestMapping(value="/centroCosto")
@CrossOrigin()
public class CentroCostoController {

	@Autowired
	CentroCostoService centroCostoService;
		
	@RequestMapping(value = "/obtenerCentrosCosto", method = RequestMethod.GET)
	public @ResponseBody List<CentroCostoDto> obtenerDepartamentos() {
		List<CentroCostoDto> result = null;
		result = centroCostoService.obtenerCentrosCosto();
		if(result == null)
			result = new ArrayList<>();
		return result;
	}
	
}
