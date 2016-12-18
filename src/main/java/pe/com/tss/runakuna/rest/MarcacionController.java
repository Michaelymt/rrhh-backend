package pe.com.tss.runakuna.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.com.tss.runakuna.dto.*;
import pe.com.tss.runakuna.service.HorarioService;
import pe.com.tss.runakuna.service.MarcacionEmpleadoService;

import java.util.*;

@RestController
@RequestMapping(value = "/marcacion")
public class MarcacionController {
	
	private static final Logger LOG = LoggerFactory.getLogger(MarcacionController.class);

	@Autowired
	MarcacionEmpleadoService marcacionEmpleadoService;

	@RequestMapping(value = "/registrarCorreccionMarcacion", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarCorreccionMarcacion(@RequestBody SolicitudCambioMarcacionDto solicitudCambioMarcacion) {

		NotificacionDto notificacion = new NotificacionDto();
		
		notificacion = marcacionEmpleadoService.registrarSolicitudCambioMarcacion(solicitudCambioMarcacion);
		return notificacion;

	}
		
}