package pe.com.tss.runakuna.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Cargo;
import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;
import pe.com.tss.runakuna.domain.model.entities.Horario;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;
import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;
import pe.com.tss.runakuna.domain.model.entities.Moneda;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.repository.jdbc.CargoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CargoRepositoryJpa;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HistorialLaboralJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MonedaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoRepositoryJpa;
import pe.com.tss.runakuna.domain.model.repository.jpa.UnidaDeNegocioJpaRepository;
import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.HistoriaLaboralDto;
import pe.com.tss.runakuna.dto.HorarioDto;
import pe.com.tss.runakuna.dto.HorarioEmpleadoDiaDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.service.CargoService;

@Service
public class CargoServiceImpl implements CargoService{

	final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	CargoRepository cargoRepository;
	
	@Autowired
	HistorialLaboralJpaRepository historialLaboralJpaRepository;
	
	@Autowired
	CargoRepositoryJpa cargoRepositoryJpa;
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	ProyectoRepositoryJpa proyectoRepositoryJpa;
	
	@Autowired
	MonedaJpaRepository monedaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	HorarioJpaRepository horarioJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	UnidaDeNegocioJpaRepository unidaDeNegocioJpaRepository;
	
	@Autowired
	DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;
	
	@Override
	public String save(CargoDto dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CargoDto> getCargo() {
		// TODO Auto-generated method stub
		return cargoRepository.findCargo();
	}

	@Override
	public Long actualizarCargo(HistoriaLaboralDto historiaLaboralDto) {
		
		HistorialLaboral entity = historialLaboralJpaRepository.findOne(historiaLaboralDto.getIdHistorialLaboral());
		
		entity.setFechaInicio(historiaLaboralDto.getDesdeFecha());
		entity.setFechaFin(historiaLaboralDto.getHastaFecha());
		entity.setSalario(historiaLaboralDto.getSalario());
		
		Moneda moneda = monedaJpaRepository.findOne(historiaLaboralDto.getIdMoneda());
		entity.setMoneda(moneda);
		
		entity.setDescripcion(historiaLaboralDto.getDescripcion());
		
		historialLaboralJpaRepository.save(entity);
		historialLaboralJpaRepository.flush();
		
		LOGGER.info("Update ID: "+ entity.getIdHistorialLaboral());
		
		return entity.getIdHistorialLaboral();
	}

	@Override
	public NotificacionDto crearCargo(HistoriaLaboralDto historiaLaboralDto) {
		
		Date date = new Date();
		NotificacionDto notificacion = new NotificacionDto();
		
//		HistorialLaboral historialLaboralExists = historialLaboralJpaRepository.findByIdEmpleado((long) 296);
		Long totalCount = historiaLaboralRepository.obtenerCantidadCargos(historiaLaboralDto.getIdEmpleado());
		if(totalCount > 0 ){
			
			HistoriaLaboralDto idUltimoHistorialLaboral = historiaLaboralRepository.obtenerUltimoCargo(historiaLaboralDto.getIdEmpleado());
			
			HistoriaLaboralDto idPrimerHistorialLaboral = historiaLaboralRepository.obtenerPrimerCargo(historiaLaboralDto.getIdEmpleado());

			HistorialLaboral entityPrimerCargo = historialLaboralJpaRepository.findOne(idPrimerHistorialLaboral.getIdHistorialLaboral());
			
			HistorialLaboral entityUltimoCargo = historialLaboralJpaRepository.findOne(idUltimoHistorialLaboral.getIdHistorialLaboral());
			
			if(entityUltimoCargo.getFechaInicio().before(historiaLaboralDto.getDesdeFecha())){
				
				//Start - Update FechaFin del Antiguo Cargo
				HorarioEmpleado ultimoCargoVigenteHE = horarioEmpleadoJpaRepository.findByFechaInicio(entityUltimoCargo.getFechaInicio(),historiaLaboralDto.getIdEmpleado());
				ultimoCargoVigenteHE.setFinVigencia(restarDiasFecha(historiaLaboralDto.getDesdeFecha(),-1));
				
				entityUltimoCargo.setFechaFin(restarDiasFecha(historiaLaboralDto.getDesdeFecha(),-1));
				// End - Update FechaFin del Antiguo Cargo
				
				HistorialLaboral entity;
				if(historiaLaboralDto.getIdHistorialLaboral() == null)
					entity = new HistorialLaboral();
				else entity = historialLaboralJpaRepository.findOne(historiaLaboralDto.getIdHistorialLaboral());
				
				//Se puede grabar el nuevo cargo
				Empleado empleado = empleadoJpaRepository.findOne(historiaLaboralDto.getIdEmpleado());
				entity.setEmpleado(empleado);
				
				if(historiaLaboralDto.getIdProyecto() != null){
					Proyecto proyecto = proyectoRepositoryJpa.findOne(historiaLaboralDto.getIdProyecto());
					entity.setProyecto(proyecto);
				}else{
					entity.setProyecto(null);
				}

				if(historiaLaboralDto.getIdCargo() != null){
					Cargo cargo = cargoRepositoryJpa.findOne(historiaLaboralDto.getIdCargo());
					entity.setCargo(cargo);
				}else{
					entity.setCargo(null);
				}
				
				
				entity.setFechaInicio(historiaLaboralDto.getDesdeFecha());
				
				if(historiaLaboralDto.getDescripcion() != null){
					entity.setDescripcion(historiaLaboralDto.getDescripcion());
				}else{
					entity.setDescripcion(null);
				}
				
				if(historiaLaboralDto.getSalario() != null){
					entity.setSalario(historiaLaboralDto.getSalario());
				}else{
					entity.setSalario(null);
				}
				
				Moneda moneda = monedaJpaRepository.findOne(historiaLaboralDto.getIdMoneda());
				entity.setMoneda(moneda);
				
				UnidadDeNegocio unidadDeNegocio = unidaDeNegocioJpaRepository.findOne(Long.parseLong(historiaLaboralDto.getUnidadNegocio()));
				entity.setUnidadDeNegocio(unidadDeNegocio);
				
				if(historiaLaboralDto.getDepartamentoArea() != null){
					DepartamentoArea departamentoArea = departamentoAreaJpaRepository.findOne(Long.parseLong(historiaLaboralDto.getDepartamentoArea()));
					entity.setDepartamentoArea(departamentoArea);
				}else{
					entity.setDepartamentoArea(null);
				}
				
				
				HorarioEmpleado entityHorario = new HorarioEmpleado();
				
				mapper.map(historiaLaboralDto.getHorarioEmpleado(), entityHorario);
				
				
				Empleado empleadoEntity = empleadoJpaRepository.findOne(historiaLaboralDto.getHorarioEmpleado().getIdEmpleado());
				entityHorario.setEmpleado(empleadoEntity);
				
				if(historiaLaboralDto.getHorarioEmpleado().getIdHorario() != null){
					Horario horarioEntity = horarioJpaRepository.findOne(historiaLaboralDto.getHorarioEmpleado().getIdHorario());
					entityHorario.setHorario(horarioEntity);
					
				}
				
				entityHorario.setHorarioEmpleadoDias(new ArrayList<>());
				
				if(historiaLaboralDto.getHorarioEmpleado().getHorariosEmpleadoDia() !=null){
					for (HorarioEmpleadoDiaDto horarioEmpleadoDia : historiaLaboralDto.getHorarioEmpleado().getHorariosEmpleadoDia()) {
						HorarioEmpleadoDia horarioEmpleadoDiaEntity;
						horarioEmpleadoDiaEntity = mapper.map(horarioEmpleadoDia, HorarioEmpleadoDia.class);
						horarioEmpleadoDiaEntity.setHorarioEmpleado(entityHorario);
							
						entityHorario.getHorarioEmpleadoDias().add(horarioEmpleadoDiaEntity);
					}
				}
				
				
				entityHorario = horarioEmpleadoJpaRepository.save(entityHorario);
				
				/*HorarioEmpleado entityHorarioEmpleado;
				if(historiaLaboralDto.getIdHorarioEmpleado() == null)
					entityHorarioEmpleado = new HorarioEmpleado();
				else entityHorarioEmpleado = horarioEmpleadoJpaRepository.findOne(historiaLaboralDto.getIdHorarioEmpleado());
				
				entityHorarioEmpleado.setEmpleado(empleado);
				entityHorarioEmpleado.setInicioVigencia(historiaLaboralDto.getDesdeFecha());
				entityHorarioEmpleado.setHorasSemanal(historiaLaboralDto.getHorasSemanal());
				
				// TIPO HORARIO
				if (historiaLaboralDto.getTipoHorario().equals("PE")) {
					HorarioEmpleadoDia entityHorarioEmpleadoDia;
					if(historiaLaboralDto.getIdHorarioEmpleadoDia() == null)
						entityHorarioEmpleadoDia = new HorarioEmpleadoDia();
					else entityHorarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.findOne(historiaLaboralDto.getIdHorarioEmpleadoDia());
					//DUDA
					entityHorarioEmpleadoDia.setHorarioEmpleado(entityHorarioEmpleado);
					
					
				} else {
					entityHorarioEmpleado.setTipoHorario(historiaLaboralDto.getTipoHorario());
				}
				
				Horario horario = horarioJpaRepository.findOne(historiaLaboralDto.getIdHorario());
				entityHorarioEmpleado.setHorario(horario);
				entityHorarioEmpleado.setHorasSemanal(historiaLaboralDto.getHorasSemanalHorario());
				*/
				
				
				historialLaboralJpaRepository.save(entity);
				//horarioEmpleadoJpaRepository.save(entityHorarioEmpleado);
				//update
				historialLaboralJpaRepository.save(entityUltimoCargo);
				horarioEmpleadoJpaRepository.save(ultimoCargoVigenteHE);
				
				notificacion.setCodigo(1L);
				notificacion.setMensaje("El registro es correctamente");
				return notificacion;
				
			} else if(entityUltimoCargo.getFechaInicio().after(historiaLaboralDto.getDesdeFecha()) && entityPrimerCargo.getFechaInicio().after(historiaLaboralDto.getDesdeFecha())) {
				//Grabar Cargos menores al mas antiguo cargo
				
				HistorialLaboral entity;
				if(historiaLaboralDto.getIdHistorialLaboral() == null)
					entity = new HistorialLaboral();
				else entity = historialLaboralJpaRepository.findOne(historiaLaboralDto.getIdHistorialLaboral());
				
				//Se puede grabar el nuevo cargo
				Empleado empleado = empleadoJpaRepository.findOne(historiaLaboralDto.getIdEmpleado());
				entity.setEmpleado(empleado);
				
				if(historiaLaboralDto.getIdProyecto() != null){
					Proyecto proyecto = proyectoRepositoryJpa.findOne(historiaLaboralDto.getIdProyecto());
					entity.setProyecto(proyecto);
				}else{
					entity.setProyecto(null);
				}

				if(historiaLaboralDto.getIdCargo() != null){
					Cargo cargo = cargoRepositoryJpa.findOne(historiaLaboralDto.getIdCargo());
					entity.setCargo(cargo);
				}else{
					entity.setCargo(null);
				}
				
				entity.setFechaInicio(historiaLaboralDto.getDesdeFecha());
				entity.setFechaFin(restarDiasFecha(entityPrimerCargo.getFechaInicio(), -1));
				
				if(historiaLaboralDto.getDescripcion() != null){
					entity.setDescripcion(historiaLaboralDto.getDescripcion());
				}else{
					entity.setDescripcion(null);
				}
				if(historiaLaboralDto.getSalario() != null){
					entity.setSalario(historiaLaboralDto.getSalario());
				}else{
					entity.setSalario(null);
				}
				
				Moneda moneda = monedaJpaRepository.findOne(historiaLaboralDto.getIdMoneda());
				entity.setMoneda(moneda);
				
				UnidadDeNegocio unidadDeNegocio = unidaDeNegocioJpaRepository.findOne(Long.parseLong(historiaLaboralDto.getUnidadNegocio()));
				entity.setUnidadDeNegocio(unidadDeNegocio);
				
				if(historiaLaboralDto.getDepartamentoArea() != null){
					DepartamentoArea departamentoArea = departamentoAreaJpaRepository.findOne(Long.parseLong(historiaLaboralDto.getDepartamentoArea()));
					entity.setDepartamentoArea(departamentoArea);
				}else{
					entity.setDepartamentoArea(null);
				}
				
				/*HorarioEmpleado entityHorarioEmpleado;
				if(historiaLaboralDto.getIdHorarioEmpleado() == null)
					entityHorarioEmpleado = new HorarioEmpleado();
				else entityHorarioEmpleado = horarioEmpleadoJpaRepository.findOne(historiaLaboralDto.getIdHorarioEmpleado());
				
				entityHorarioEmpleado.setEmpleado(empleado);
				entityHorarioEmpleado.setInicioVigencia(historiaLaboralDto.getDesdeFecha());
				entityHorarioEmpleado.setFinVigencia(restarDiasFecha(entityPrimerCargo.getFechaInicio(), -1));
				entityHorarioEmpleado.setHorasSemanal(historiaLaboralDto.getHorasSemanal());
				
				// TIPO HORARIO
				if (historiaLaboralDto.getTipoHorario().equals("PE")) {
					HorarioEmpleadoDia entityHorarioEmpleadoDia;
					if(historiaLaboralDto.getIdHorarioEmpleadoDia() == null)
						entityHorarioEmpleadoDia = new HorarioEmpleadoDia();
					else entityHorarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.findOne(historiaLaboralDto.getIdHorarioEmpleadoDia());
					//DUDA
					entityHorarioEmpleadoDia.setHorarioEmpleado(entityHorarioEmpleado);
					
					
				} else {
					entityHorarioEmpleado.setTipoHorario(historiaLaboralDto.getTipoHorario());
				}
				
				Horario horario = horarioJpaRepository.findOne(historiaLaboralDto.getIdHorario());
				entityHorarioEmpleado.setHorario(horario);
				entityHorarioEmpleado.setHorasSemanal(historiaLaboralDto.getHorasSemanalHorario());
				*/
				
				HorarioEmpleado entityHorario = new HorarioEmpleado();
				
				mapper.map(historiaLaboralDto.getHorarioEmpleado(), entityHorario);
				
				
				Empleado empleadoEntity = empleadoJpaRepository.findOne(historiaLaboralDto.getHorarioEmpleado().getIdEmpleado());
				entityHorario.setEmpleado(empleadoEntity);
				
				if(historiaLaboralDto.getHorarioEmpleado().getIdHorario() != null){
					Horario horarioEntity = horarioJpaRepository.findOne(historiaLaboralDto.getHorarioEmpleado().getIdHorario());
					entityHorario.setHorario(horarioEntity);
					
				}
				
				entityHorario.setHorarioEmpleadoDias(new ArrayList<>());
				
				if(historiaLaboralDto.getHorarioEmpleado().getHorariosEmpleadoDia() !=null){
					for (HorarioEmpleadoDiaDto horarioEmpleadoDia : historiaLaboralDto.getHorarioEmpleado().getHorariosEmpleadoDia()) {
						HorarioEmpleadoDia horarioEmpleadoDiaEntity;
						horarioEmpleadoDiaEntity = mapper.map(horarioEmpleadoDia, HorarioEmpleadoDia.class);
						horarioEmpleadoDiaEntity.setHorarioEmpleado(entityHorario);
							
						entityHorario.getHorarioEmpleadoDias().add(horarioEmpleadoDiaEntity);
					}
				}
				
				
				entityHorario = horarioEmpleadoJpaRepository.save(entityHorario);
				
				
				
				historialLaboralJpaRepository.save(entity);
				//horarioEmpleadoJpaRepository.save(entityHorarioEmpleado);
				
				notificacion.setCodigo(1L);
				notificacion.setMensaje("El registro es correctamente");
				return notificacion;
				
			} else {
				
				String result;
				SimpleDateFormat formatter;

				formatter = new SimpleDateFormat("dd/MM/yyyy", new Locale("es", "pe"));
				result = formatter.format(historiaLaboralDto.getDesdeFecha());
				System.out.println("Result: " + result);
				
				notificacion.setCodigo(0L);
				notificacion.setMensaje("No es posible registrar, revise la fecha: " + result);
				return notificacion;
			}
		} else {
			//NUEVO
			HistorialLaboral entity;
			if(historiaLaboralDto.getIdHistorialLaboral() == null)
				entity = new HistorialLaboral();
			else entity = historialLaboralJpaRepository.findOne(historiaLaboralDto.getIdHistorialLaboral());
			
			//Se puede grabar el nuevo cargo
			Empleado empleado = empleadoJpaRepository.findOne(historiaLaboralDto.getIdEmpleado());
			entity.setEmpleado(empleado);
			
			if(historiaLaboralDto.getIdProyecto() != null){
				Proyecto proyecto = proyectoRepositoryJpa.findOne(historiaLaboralDto.getIdProyecto());
				entity.setProyecto(proyecto);
			}else{
				entity.setProyecto(null);
			}

			if(historiaLaboralDto.getIdCargo() != null){
				Cargo cargo = cargoRepositoryJpa.findOne(historiaLaboralDto.getIdCargo());
				entity.setCargo(cargo);
			}else{
				entity.setCargo(null);
			}
			
			entity.setFechaInicio(historiaLaboralDto.getDesdeFecha());
//			entity.setFechaFin(historiaLaboralDto.getHastaFecha());
			
			if(historiaLaboralDto.getDescripcion() != null){
				entity.setDescripcion(historiaLaboralDto.getDescripcion());
			}else{
				entity.setDescripcion(null);
			}
			
			if(historiaLaboralDto.getSalario() != null){
				entity.setSalario(historiaLaboralDto.getSalario());
			}else{
				entity.setSalario(null);
			}
			
			Moneda moneda = monedaJpaRepository.findOne(historiaLaboralDto.getIdMoneda());
			entity.setMoneda(moneda);
			
			UnidadDeNegocio unidadDeNegocio = unidaDeNegocioJpaRepository.findOne(Long.parseLong(historiaLaboralDto.getUnidadNegocio()));
			entity.setUnidadDeNegocio(unidadDeNegocio);
			
			if(historiaLaboralDto.getDepartamentoArea() != null){
				DepartamentoArea departamentoArea = departamentoAreaJpaRepository.findOne(Long.parseLong(historiaLaboralDto.getDepartamentoArea()));
				entity.setDepartamentoArea(departamentoArea);
			}else{
				entity.setDepartamentoArea(null);
			}
			
			/*HorarioEmpleado entityHorarioEmpleado;
			if(historiaLaboralDto.getIdHorarioEmpleado() == null)
				entityHorarioEmpleado = new HorarioEmpleado();
			else entityHorarioEmpleado = horarioEmpleadoJpaRepository.findOne(historiaLaboralDto.getIdHorarioEmpleado());
			
			entityHorarioEmpleado.setEmpleado(empleado);
			entityHorarioEmpleado.setInicioVigencia(historiaLaboralDto.getDesdeFecha());
			entityHorarioEmpleado.setFinVigencia(historiaLaboralDto.getHastaFecha());
			entityHorarioEmpleado.setHorasSemanal(historiaLaboralDto.getHorasSemanal());
			
			// TIPO HORARIO
			if (historiaLaboralDto.getTipoHorario().equals("PE")) {
				HorarioEmpleadoDia entityHorarioEmpleadoDia;
				if(historiaLaboralDto.getIdHorarioEmpleadoDia() == null)
					entityHorarioEmpleadoDia = new HorarioEmpleadoDia();
				else entityHorarioEmpleadoDia = horarioEmpleadoDiaJpaRepository.findOne(historiaLaboralDto.getIdHorarioEmpleadoDia());
				//DUDA
				entityHorarioEmpleadoDia.setHorarioEmpleado(entityHorarioEmpleado);
				
				
			} else {
				entityHorarioEmpleado.setTipoHorario(historiaLaboralDto.getTipoHorario());
			}
			
			Horario horario = horarioJpaRepository.findOne(historiaLaboralDto.getIdHorario());
			entityHorarioEmpleado.setHorario(horario);
			entityHorarioEmpleado.setHorasSemanal(historiaLaboralDto.getHorasSemanalHorario());
			*/
			
			HorarioEmpleado entityHorario = new HorarioEmpleado();
			
			mapper.map(historiaLaboralDto.getHorarioEmpleado(), entityHorario);
			
			
			Empleado empleadoEntity = empleadoJpaRepository.findOne(historiaLaboralDto.getHorarioEmpleado().getIdEmpleado());
			entityHorario.setEmpleado(empleadoEntity);
			
			if(historiaLaboralDto.getHorarioEmpleado().getIdHorario() != null){
				Horario horarioEntity = horarioJpaRepository.findOne(historiaLaboralDto.getHorarioEmpleado().getIdHorario());
				entityHorario.setHorario(horarioEntity);
				
			}
			
			entityHorario.setHorarioEmpleadoDias(new ArrayList<>());
			
			if(historiaLaboralDto.getHorarioEmpleado().getHorariosEmpleadoDia() !=null){
				for (HorarioEmpleadoDiaDto horarioEmpleadoDia : historiaLaboralDto.getHorarioEmpleado().getHorariosEmpleadoDia()) {
					HorarioEmpleadoDia horarioEmpleadoDiaEntity;
					horarioEmpleadoDiaEntity = mapper.map(horarioEmpleadoDia, HorarioEmpleadoDia.class);
					horarioEmpleadoDiaEntity.setHorarioEmpleado(entityHorario);
						
					entityHorario.getHorarioEmpleadoDias().add(horarioEmpleadoDiaEntity);
				}
			}
			
			entityHorario = horarioEmpleadoJpaRepository.save(entityHorario);
						
			historialLaboralJpaRepository.save(entity);
			//horarioEmpleadoJpaRepository.save(entityHorarioEmpleado);
			
			notificacion.setCodigo(1L);
			notificacion.setMensaje("Se registro el primer cargo");
			return notificacion;
		}
		
	}
	
	public Date restarDiasFecha(Date fecha, int dias){
	
		       Calendar calendar = Calendar.getInstance();
		       calendar.setTime(fecha);
		       calendar.add(Calendar.DAY_OF_YEAR, dias);
	
		       return calendar.getTime();
	
	}


	@Override
	public List<HorarioDto> getObtenerNombreHorario() {
		// TODO Auto-generated method stub
		return cargoRepository.obtenerNombreHorario();
	}

	@Override
	public String eliminarCargo(Long idHistorialLaboral) {

		HistorialLaboral entity = historialLaboralJpaRepository.findOne(idHistorialLaboral);
		
		Date fechaActual = new Date();
		
		
		if(idHistorialLaboral == null){
			return "Error id is null";
		} else {
			
			if(fechaActual.before(entity.getFechaInicio())){
				historialLaboralJpaRepository.delete(idHistorialLaboral);
			}
			
			return "success";
		}
		
	}

	

}
