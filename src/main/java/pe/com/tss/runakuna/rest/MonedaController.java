package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.MonedaDto;
import pe.com.tss.runakuna.service.MonedaService;

@CrossOrigin
@RestController
@RequestMapping("/moneda")
public class MonedaController {
	
	@Autowired
	MonedaService monedaService;
	
	@RequestMapping(value = "/obtenerMoneda", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<MonedaDto> getMoneda() {

		return monedaService.getMoneda();
	}

}
