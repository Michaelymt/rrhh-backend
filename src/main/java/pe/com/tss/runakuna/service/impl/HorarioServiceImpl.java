package pe.com.tss.runakuna.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.query.Param;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HorarioJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.dto.*;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.rest.EmpleadoController;
import pe.com.tss.runakuna.service.EmpleadoService;
import pe.com.tss.runakuna.service.HorarioService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class HorarioServiceImpl implements HorarioService {

	@Autowired
	Mapper mapper;
	
	@Autowired
	HorarioJdbcRepository horarioJdbcRepository;
	
	@Autowired
	EmpresaJpaRepository empresaJpaRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	HorarioJpaRepository horarioJpaRepository;
	
	@Autowired
	HorarioDiaJpaRepository horarioDiaJpaRepository;

	private static final Logger log = LoggerFactory.getLogger(HorarioServiceImpl.class);
	
	@Override
	public List<HorarioDto> busquedaHorario(BusquedaHorarioDto busquedaHorarioDto) {
		
		return horarioJdbcRepository.busquedaHorario(busquedaHorarioDto);
	}
	
	@Override
	public List<HorarioDto> obtenerHorariosPorTipoHorario(HorarioDto horarioDto) {
		
		return horarioJdbcRepository.obtenerHorariosPorTipoHorario(horarioDto);
	}
	
	@Override
	public HorarioDto obtenerHorarioPorTipoHorarioPorDefecto(HorarioDto horarioDto) {
		
		HorarioDto dto = horarioJdbcRepository.obtenerHorariosPorTipoHorarioyPorDefecto(horarioDto);
		if(dto!=null){
			List<HorarioDiaDto> horarioDias = horarioJdbcRepository.obtenerHorarioDiaPorHorario(dto);
			dto.setHorarioDias(horarioDias);
		}
		
		return dto;
	}
	
	@Override
	public List<HorarioDiaDto> obtenerHorarioDiaPorHorario(HorarioDto horarioDto) {
		
		List<HorarioDiaDto> horarioDias = horarioJdbcRepository.obtenerHorarioDiaPorHorario(horarioDto);
			
		return horarioDias;
	}
	
	@Override
	@Transactional
	public NotificacionDto registrarHorario(HorarioDto horarioDto) {
		
		NotificacionDto notificacion = new NotificacionDto();
		Horario entity = new Horario();
				
		mapper.map(horarioDto, entity);
		
		List<Horario> horariosExistentes = horarioJpaRepository.validarHorarioPorNombre(horarioDto.getNombre());
		
		if(horariosExistentes != null && horariosExistentes.size()>0){
			notificacion.setCodigo(0L);
			notificacion.setMensaje("Ya existe horario con el nombre "+horarioDto.getNombre()+".");
			return notificacion;
		}
		
		Empresa empresaEntity = empresaJpaRepository.findOne(horarioDto.getIdEmpresa());
		entity.setEmpresa(empresaEntity);
		
		if(horarioDto.getIdProyecto() != null){
			Proyecto proyectoEntity = proyectoJpaRepository.findOne(horarioDto.getIdProyecto());
			entity.setProyecto(proyectoEntity);
		}
				
		if(horarioDto.getPorDefecto().intValue() == 1){
			if(horarioDto.getIdProyecto() != null){
				horarioJpaRepository.updatePorDefectoPorProyecto(horarioDto.getIdEmpresa(), horarioDto.getIdProyecto());
			}else{
				horarioJpaRepository.updatePorDefectoPorEmpresa(horarioDto.getIdEmpresa());
			}
		}
		
		entity.setHorariosDias(new ArrayList<>());
		
		if(horarioDto.getHorarioDias() !=null){
			for (HorarioDiaDto horarioDiaDto : horarioDto.getHorarioDias()) {
				HorarioDia horarioDiaEntity;
				horarioDiaEntity = mapper.map(horarioDiaDto, HorarioDia.class);
				
				horarioDiaEntity.setHorario(entity);
					
				entity.getHorariosDias().add(horarioDiaEntity);
			}
		}
		
		entity = horarioJpaRepository.save(entity);
		horarioJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	@Transactional
	public NotificacionDto actualizarHorario(HorarioDto horarioDto) {
		
		NotificacionDto notificacion = new NotificacionDto();
		Horario entity = new Horario();
		
		entity = horarioJpaRepository.findOne(horarioDto.getIdHorario());
		
		mapper.map(horarioDto, entity);
		
		 Empresa empresa = empresaJpaRepository.findOne(horarioDto.getIdEmpresa());
			entity.setEmpresa(empresa);
			if(horarioDto.getIdProyecto() != null){
				Proyecto proyectoEntity = proyectoJpaRepository.findOne(horarioDto.getIdProyecto());
				entity.setProyecto(proyectoEntity);
			}
		
			if (entity.getHorariosDias() != null) {

	            final List<HorarioDia> horarioDiasActuales = entity.getHorariosDias();
	            for (int i = 0; i < horarioDiasActuales.size(); i++) {
	                if (horarioDiaToDelete(horarioDto.getHorarioDias(),  horarioDiasActuales.get(i).getIdHorarioDia()))
	                    entity.removeHorarioDia(horarioDiasActuales.get(i));
	            }
		 } else 
			 entity.setHorariosDias(new ArrayList<>());
			
			
		if(horarioDto.getHorarioDias() !=null){
			for (HorarioDiaDto horarioDiaDto : horarioDto.getHorarioDias()) {
				
				HorarioDia horarioDiaEntity = new HorarioDia();
				
				horarioDiaEntity = horarioDiaJpaRepository.findOne(horarioDiaDto.getIdHorarioDia());
				
				mapper.map(horarioDiaDto, horarioDiaEntity);
				horarioDiaEntity.setLaboral(horarioDiaDto.getLaboral());
								
				horarioDiaEntity.setHorario(entity);
				entity.addHorarioDia(horarioDiaEntity);
			}
		}
		
		entity = horarioJpaRepository.save(entity);
		horarioJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	public HorarioDto obtenerHorarioPorIdHorario(HorarioDto horarioDto) {
		HorarioDto dto = new HorarioDto();
		Horario entity;
		entity = horarioJpaRepository.findOne(horarioDto.getIdHorario());
		if(entity!= null){
			mapper.map(entity, dto);
			if(entity.getProyecto() != null)
				dto.setIdProyecto(entity.getProyecto().getIdProyecto());
			
		}
		
		return dto;
	}
	
	private boolean horarioDiaToDelete(List<HorarioDiaDto> horarioDias, Long idHorarioDia) {
        boolean delete = true;
        for (HorarioDiaDto item : horarioDias) {
            if (item.getIdHorarioDia().intValue() == idHorarioDia.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }
	
	@Override
	public List<HorarioDto> obtenerHorarios() {
		
		List<HorarioDto> dto = new ArrayList<>();
		
		List<Horario> horarioDias = horarioJpaRepository.findAll();
		
		if(horarioDias!= null){
			dto = horarioDias.stream().map(m -> mapper.map(m, HorarioDto.class)).collect(toList());
		}
		
			
		return dto;
	}
	
}
