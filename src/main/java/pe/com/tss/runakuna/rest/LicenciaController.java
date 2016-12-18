package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.BusquedaLicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.LicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.service.LicenciaEmpleadoService;

@RestController
@RequestMapping(value = "/licenciaEmpleado")
public class LicenciaController {
	
	private final Logger LOG = LoggerFactory.getLogger(LicenciaController.class);

	@Autowired
	LicenciaEmpleadoService licenciaEmpleadoService;
	
	@RequestMapping(value = "/registrarLicencia", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto registrarLicenciaEmpleado(@RequestBody LicenciaEmpleadoDto licenciaEmpleadoDto) {
		NotificacionDto dto = new NotificacionDto();
		if(licenciaEmpleadoDto.getIdLicencia()!=null){
			dto = licenciaEmpleadoService.actualizarLicenciaEmpleado(licenciaEmpleadoDto);
		} else {
			dto = licenciaEmpleadoService.registrarLicenciaEmpleado(licenciaEmpleadoDto);
		}
		return dto;

	}
	
	@RequestMapping(value = "/aprobarLicencia", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto aprobarLicenciaEmpleado(@RequestBody LicenciaEmpleadoDto licenciaEmpleadoDto) {
		NotificacionDto dto = new NotificacionDto();
		dto = licenciaEmpleadoService.aprobarLicenciaEmpleado(licenciaEmpleadoDto);
	 	return dto;

	}
	
	/*@RequestMapping(value = "/actualizarLicencia", method = RequestMethod.POST)
	public @ResponseBody NotificacionDto actualizarLicenciaEmpleado(@RequestBody LicenciaEmpleadoDto licenciaEmpleadoDto) {

		NotificacionDto dto = licenciaEmpleadoService.actualizarLicenciaEmpleado(licenciaEmpleadoDto);
		return dto;

	}*/
	
	@RequestMapping(value = "/eliminarLicencia", method = RequestMethod.POST)
	public ResponseEntity<String> eliminarVacacionEmpleado(@RequestBody LicenciaEmpleadoDto licenciaEmpleadoDto) {
		Long idLicencia = licenciaEmpleadoService.eliminarLicenciaEmpleado(licenciaEmpleadoDto.getIdLicencia());
		return new ResponseEntity<String>(idLicencia.toString(),HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obtenerLicencias", method = RequestMethod.POST)
	public @ResponseBody ResponseEntity<List<LicenciaEmpleadoDto>> obtenerLicencias(@RequestBody BusquedaLicenciaEmpleadoDto busquedaLicenciaEmpleadoDto){
		List<LicenciaEmpleadoDto> result = new ArrayList<LicenciaEmpleadoDto>();
		result = licenciaEmpleadoService.obtenerLicencias(busquedaLicenciaEmpleadoDto);
		if(result == null)
			result = new ArrayList<>();
		LOG.info("Msg obtenerLicencias: " + result);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/obtenerLicencia", method = RequestMethod.POST)
	public @ResponseBody LicenciaEmpleadoDto obtenerLicencia(@RequestBody LicenciaEmpleadoDto dto){
		LicenciaEmpleadoDto result = new LicenciaEmpleadoDto();
		result = licenciaEmpleadoService.obtenerLicencia(dto.getIdLicencia());
		if(result == null)
			result = new LicenciaEmpleadoDto();
		LOG.info("Msg obtenerLicencia: " + result);
		return result;
	}

}
