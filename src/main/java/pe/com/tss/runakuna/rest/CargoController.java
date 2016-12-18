package pe.com.tss.runakuna.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.HorarioDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.service.CargoService;

@CrossOrigin
@RestController
@RequestMapping("/cargo")
public class CargoController {
	
	@Autowired
	CargoService cargoService;
	
	@Autowired
	HistorialLaboralJpaRepository historialLaboralJpaRepository;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody CargoDto dto) {

		String dataCargoProcess = cargoService.save(dto);

		return   new ResponseEntity<String>( dataCargoProcess , HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/findAll", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<CargoDto> departamentos() {

		return cargoService.getCargo();
	}
	
	@RequestMapping(value = "/nombreHorario", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	public List<HorarioDto> obtenerNombreHorario() {

		return cargoService.getObtenerNombreHorario();
	}
	
	@RequestMapping(value="/actualizarCargo", method = RequestMethod.POST)
	public ResponseEntity<Long> actualizarCargo(@RequestBody HistoriaLaboralDto historialLaboraldto){
		
		System.out.println("Data historialLaboraldto " + historialLaboraldto.toString());
		Long id = cargoService.actualizarCargo(historialLaboraldto);
		
		return new ResponseEntity<Long>(id, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/crearCargo", method = RequestMethod.POST)
		public @ResponseBody NotificacionDto crearCargo(@RequestBody HistoriaLaboralDto historiaLaboralDto){
		
		NotificacionDto dto = cargoService.crearCargo(historiaLaboralDto);
		return dto;
		
	}
	
	@RequestMapping(value = "/eliminarCargo/{idHistorialLaboral}")
	public ResponseEntity<String> eliminarCargo(@PathVariable Long idHistorialLaboral) {
		String message = cargoService.eliminarCargo(idHistorialLaboral);
		return new ResponseEntity<String>("{\"message\":\"" + message + "\"}", HttpStatus.CREATED);
	}
	

}
