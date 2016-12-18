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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.AlertaDto;
import pe.com.tss.runakuna.dto.LicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.service.AlertaService;

/**
 * Created by josediaz on 14/12/2016.
 */
@CrossOrigin
@RestController
@RequestMapping("/alerta")
public class AlertaController {

	private final Logger LOG = LoggerFactory.getLogger(AlertaController.class);
	
    @Autowired
    AlertaService alertaService;

    @RequestMapping(value = "/alertaByCodigo", method = RequestMethod.GET, produces= MediaType.APPLICATION_JSON_VALUE)
    public AlertaDto getAlertaByCodigo(@RequestParam(name="codigo")  String codigo) {

        return alertaService.obtenerAlerta(codigo);
    }
    
    @RequestMapping(value = "/registrarAlerta", method = RequestMethod.POST)
    public @ResponseBody NotificacionDto registrarAlerta(@RequestBody AlertaDto alertaDto) {
    	NotificacionDto dto = new NotificacionDto();
    	if(alertaDto.getIdAlerta()==null){
			dto = alertaService.registrarAlerta(alertaDto);
		} else {
			dto = alertaService.actualizarAlerta(alertaDto);
		}
		return dto;
    }
    
    @RequestMapping(value = "/obtenerAlertas", method = RequestMethod.POST)
    public @ResponseBody ResponseEntity<List<AlertaDto>> obtenerAlertas(@RequestBody AlertaDto dto){
    	List<AlertaDto> result = new ArrayList<AlertaDto>();
		result = alertaService.obtenerAlertas(dto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerAlertas: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
    
    @RequestMapping(value = "/obtenerAlerta", method = RequestMethod.POST)
	public @ResponseBody AlertaDto obtenerLicencia(@RequestBody AlertaDto dto){
    	AlertaDto result = new AlertaDto();
		result = alertaService.obtenerAlertaDetalle(dto.getIdAlerta());
		if(result == null)
			result = new AlertaDto();
		LOG.info("Msg obtenerAlerta: " + result);
		return result;
	}
    

}
