package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.TablaGeneral;
import pe.com.tss.runakuna.domain.model.repository.jpa.TablaGeneralJpaRepository;
import pe.com.tss.runakuna.dto.HorarioDiaDto;
import pe.com.tss.runakuna.dto.TablaGeneralDto;
import pe.com.tss.runakuna.service.TablaGeneralService;

@Service
public class TablaGeneralServiceImpl implements TablaGeneralService{

	@Autowired
    Mapper mapper;
	
	@Autowired
	TablaGeneralJpaRepository tablaGeneralJpaRepository;
	
	@Override
	public List<TablaGeneralDto> obtenerTiposDocumento() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.TipoDocumento"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerEstadosCivil() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.EstadoCivil");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerGruposSanguineo() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.GrupoSanguineo"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerGeneros() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.Generico"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerTiposDomicilio() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.TipoDomicilio"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerRegimenHorario() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RegimenHorario"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerContratoTrabajo() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.ContratoTrabajo"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerTipoTrabajo() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.TipoTrabajo"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerRegimenLaboral() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RegimenLaboral"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerNivelEducacion() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.NivelEducacion"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerTipoHorario() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Horario.TipoHorario"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<HorarioDiaDto> obtenerDias() {
		List<TablaGeneral> entities;
		List<HorarioDiaDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Dia"); 
		
		items = entities.stream().map(m -> mapper.map(m, HorarioDiaDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerTipoEquipo() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("EquiposEntregados.TipoEquipo"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerEmpleadoEstado() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerEstadoTipoEquipo() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("EquiposEntregados.Estado"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerRelacionContactoEmergencia() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RelacionContacto"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerMotivosPermiso() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Permiso.Motivo"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerPermisoEstado() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Permiso.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerRelacionDependiente() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;
		
		entities = tablaGeneralJpaRepository.findByGrupo("Empleado.RelacionDependiente"); 
		
		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());
		
		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerVacacionesEstados() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Vacaciones.Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerEstados() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Estado");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}

	@Override
	public List<TablaGeneralDto> obtenerMotivoRenuncia() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("BajaDeEmpleado.Motivo");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerEntidadFinanciera() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("Entidad Financiera");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerAFP() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("AFP");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}
	
	@Override
	public List<TablaGeneralDto> obtenerEPS() {
		List<TablaGeneral> entities;
		List<TablaGeneralDto> items;

		entities = tablaGeneralJpaRepository.findByGrupo("EPS");

		items = entities.stream().map(m -> mapper.map(m, TablaGeneralDto.class)).collect(toList());

		return items;
	}

}
