package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.DepartamentoDto;
import pe.com.tss.runakuna.service.DepartamentoService;

@CrossOrigin
@RestController
@RequestMapping("/departamento")
public class DepartamentoController {
	
//	@Autowired
//	DepartamentoService departamentoService;
	
//	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
//	public List<DepartamentoDto> departamentos() {
//
//		return departamentoService.getDepartamentos();
//	}

}
