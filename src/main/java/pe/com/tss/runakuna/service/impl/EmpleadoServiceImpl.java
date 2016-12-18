package pe.com.tss.runakuna.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.domain.model.entities.*;
import pe.com.tss.runakuna.domain.model.repository.jdbc.EmpleadoJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.HistoriaLaboralRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.*;
import pe.com.tss.runakuna.dto.*;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.gateway.common.ValidationUtils;
import pe.com.tss.runakuna.rest.EmpleadoController;
import pe.com.tss.runakuna.service.EmpleadoService;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmpleadoServiceImpl implements EmpleadoService {

	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	DocumentoEmpleadoJpaRepository documentoEmpleadoJpaRepository;
	
	@Autowired
	EducacionJpaRepository educacionJpaRepository;
	
	@Autowired
	ExperienciaLaboralJpaRepository experienciaLaboralJpaRepository;
	
	@Autowired
	EquipoEntregadoJpaRepository equipoEntregadoJpaRepository;
	
	@Autowired
	DependienteJpaRepository dependienteJpaRepository;
	
	@Autowired
	EmpresaJpaRepository empresaJpaRepository;
	
	@Autowired
	CentroCostoJpaRepository centroCostoJpaRepository;
	
	@Autowired
	HistoriaLaboralRepository historiaLaboralRepository;

    @Autowired
    TablaGeneralJpaRepository tablaGeneralJpaRepository;

	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	EmpleadoJdbcRepository empleadoJdbcRepository;

    @Autowired
    PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
    
    @Autowired
    HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
    
    @Autowired
    HorasExtraJpaRepository horasExtraJpaRepository;
    
    @Autowired
    HorarioJpaRepository horarioJpaRepository;
    
    @Autowired
    HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	private static final Logger log = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Override
	@Transactional
	public NotificacionDto registrarEmpleado(EmpleadoDto empleado) {
		
		NotificacionDto notificacion = new NotificacionDto();
		Empleado entity = new Empleado();
		
		List<Empleado> empleados = empleadoJpaRepository.BuscarEmpleadoPorCodigo(empleado.getCodigo());
			
		if(empleados != null && empleados.size() >0){
			notificacion.setCodigo(0L);
			notificacion.setMensaje("El codigo del empleado ya existe.");
			return notificacion;
		}
		
		mapper.map(empleado, entity);
		
		entity.setDocumentosEmpleado(new ArrayList<>());
		
		if(empleado.getDocumentos() !=null){
			for (DocumentoEmpleadoDto documento : empleado.getDocumentos()) {
				
				DocumentoEmpleado documentoEntity;
				documentoEntity = mapper.map(documento, DocumentoEmpleado.class);
				documentoEntity.setEmpleado(entity);
				
				entity.getDocumentosEmpleado().add(documentoEntity);
			}
		}
		
		
		
		entity.setEducacionesEmpleado(new ArrayList<>());
		
		if(empleado.getEducaciones() !=null){
			for (EducacionDto educacion : empleado.getEducaciones()) {
				Educacion educacionEntity;
				educacionEntity = mapper.map(educacion, Educacion.class);
				educacionEntity.setEmpleado(entity);
				
				entity.getEducacionesEmpleado().add(educacionEntity);
			}
		}
		
		entity.setExperienciasLaboralesEmpleado(new ArrayList<>());
		
		if(empleado.getExperienciasLaborales() !=null){
			for (ExperienciaLaboralDto experienciaLaboral : empleado.getExperienciasLaborales()) {
				ExperienciaLaboral experienciaLaboralEntity;
				experienciaLaboralEntity = mapper.map(experienciaLaboral, ExperienciaLaboral.class);
				experienciaLaboralEntity.setEmpleado(entity);
				
				entity.getExperienciasLaboralesEmpleado().add(experienciaLaboralEntity);
			}
		}
		
		entity.setEquiposEntregadoEmpleado(new ArrayList<>());
		
		if(empleado.getEquiposEntregados() !=null){
			for (EquipoEntregadoDto equipoEntregado : empleado.getEquiposEntregados()) {
				EquipoEntregado equipoEntregadoEntity;
				equipoEntregadoEntity = mapper.map(equipoEntregado, EquipoEntregado.class);
				equipoEntregadoEntity.setEmpleado(entity);
				entity.getEquiposEntregadoEmpleado().add(equipoEntregadoEntity);
			}
		}
		
		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());
		
		entity.setEmpresa(empresa);
		entity.setCentroCosto(centroCosto);
		
		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	public NotificacionDto registrarDarBajaEmpleado(EmpleadoDto empleado) {
		NotificacionDto notificacion = new NotificacionDto();
		Empleado entity = new Empleado();
		
		entity = empleadoJpaRepository.findOne(empleado.getIdEmpleado());
		
//		mapper.map(empleado, entity);
		
		//entity.setDocumentosEmpleado(new ArrayList<>());
		
		 if (entity.getDocumentosEmpleado() != null) {

	            final List<DocumentoEmpleado> documentosActuales = entity.getDocumentosEmpleado();
	            for (int i = 0; i < documentosActuales.size(); i++) {
	                if (documentoEmpleadoToDelete(empleado.getDocumentos(),  documentosActuales.get(i).getIdDocumentoEmpleado()))
	                    entity.removeDocumentoEmpleado(documentosActuales.get(i));
	            }
		 } else 
			 entity.setDocumentosEmpleado(new ArrayList<>());
		
		
		
		if(empleado.getDocumentos() !=null){
			for (DocumentoEmpleadoDto documento : empleado.getDocumentos()) {
				
				DocumentoEmpleado documentoEntity = new DocumentoEmpleado();
				
				if(documento.getIdDocumentoEmpleado()!=null && documento.getIdDocumentoEmpleado() > 0){
					documentoEntity = documentoEmpleadoJpaRepository.findOne(documento.getIdDocumentoEmpleado());
				}else
					documentoEntity = new DocumentoEmpleado();
				
				mapper.map(documento, documentoEntity);
				
				documentoEntity.setEmpleado(entity);
				entity.addDocumentoEmpleado(documentoEntity);
			}
		}
		
		
//		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
//		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());
//		
//		entity.setEmpresa(empresa);
//		entity.setCentroCosto(centroCosto);
		
		entity.setFechaRenuncia(empleado.getFechaRenuncia());
		entity.setFechaCese(empleado.getFechaCese());
		entity.setMotivoRenuncia(empleado.getMotivoRenuncia());
		
		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	@Transactional
	public NotificacionDto actualizarEmpleado(EmpleadoDto empleado) {
		
		NotificacionDto notificacion = new NotificacionDto();
		Empleado entity = new Empleado();
		
		entity = empleadoJpaRepository.findOne(empleado.getIdEmpleado());
		
		mapper.map(empleado, entity);
		
		//entity.setDocumentosEmpleado(new ArrayList<>());
		
		 if (entity.getDocumentosEmpleado() != null) {

	            final List<DocumentoEmpleado> documentosActuales = entity.getDocumentosEmpleado();
	            for (int i = 0; i < documentosActuales.size(); i++) {
	                if (documentoEmpleadoToDelete(empleado.getDocumentos(),  documentosActuales.get(i).getIdDocumentoEmpleado()))
	                    entity.removeDocumentoEmpleado(documentosActuales.get(i));
	            }
		 } else 
			 entity.setDocumentosEmpleado(new ArrayList<>());
		
		
		
		if(empleado.getDocumentos() !=null){
			for (DocumentoEmpleadoDto documento : empleado.getDocumentos()) {
				
				DocumentoEmpleado documentoEntity = new DocumentoEmpleado();
				
				if(documento.getIdDocumentoEmpleado()!=null && documento.getIdDocumentoEmpleado() > 0){
					documentoEntity = documentoEmpleadoJpaRepository.findOne(documento.getIdDocumentoEmpleado());
				}else
					documentoEntity = new DocumentoEmpleado();
				
				mapper.map(documento, documentoEntity);
				
				documentoEntity.setEmpleado(entity);
				entity.addDocumentoEmpleado(documentoEntity);
			}
		}
		
		if (entity.getEducacionesEmpleado() != null) {

	           final List<Educacion> educacionesActuales = entity.getEducacionesEmpleado();
	           for (int i = 0; i < educacionesActuales.size(); i++) {
	               if (educacionToDelete(empleado.getEducaciones(),  educacionesActuales.get(i).getIdEducacion()))
	                   entity.removeEducacion(educacionesActuales.get(i));
	           }
		} else 
			 entity.setEducacionesEmpleado(new ArrayList<>());
		
		if(empleado.getEducaciones() !=null){
			for (EducacionDto educacion : empleado.getEducaciones()) {
				Educacion educacionEntity= new Educacion();
				
				if(educacion.getIdEducacion()!=null && educacion.getIdEducacion()> 0){
					educacionEntity = educacionJpaRepository.findOne(educacion.getIdEducacion());
				}else
					educacionEntity = new Educacion();
				
				mapper.map(educacion, educacionEntity);
				educacionEntity.setEmpleado(entity);
				
				entity.addEducacion(educacionEntity);
			}
		}
		
		if (entity.getExperienciasLaboralesEmpleado() != null) {

            final List<ExperienciaLaboral> experienciasLaboralesActuales = entity.getExperienciasLaboralesEmpleado();
            for (int i = 0; i < experienciasLaboralesActuales.size(); i++) {
                if (experienciaLaboralToDelete(empleado.getExperienciasLaborales(),  experienciasLaboralesActuales.get(i).getIdExperienciaLaboral()))
                    entity.removeExperienciaLaboral(experienciasLaboralesActuales.get(i));
            }
		} else 
		 entity.setExperienciasLaboralesEmpleado(new ArrayList<>());
		
		if(empleado.getExperienciasLaborales() !=null){
			for (ExperienciaLaboralDto experienciaLaboral : empleado.getExperienciasLaborales()) {
				ExperienciaLaboral experienciaLaboralEntity = new ExperienciaLaboral();
				
				if(experienciaLaboral.getIdExperienciaLaboral()!=null && experienciaLaboral.getIdExperienciaLaboral() > 0){
					experienciaLaboralEntity = experienciaLaboralJpaRepository.findOne(experienciaLaboral.getIdExperienciaLaboral());
				}else
					experienciaLaboralEntity = new ExperienciaLaboral();
				
				mapper.map(experienciaLaboral, experienciaLaboralEntity);
				experienciaLaboralEntity.setEmpleado(entity);
				
				entity.addExperienciaLaboral(experienciaLaboralEntity);
			}
		}
		
		if (entity.getEquiposEntregadoEmpleado() != null) {

            final List<EquipoEntregado> equiposEntregadosActuales = entity.getEquiposEntregadoEmpleado();
            for (int i = 0; i < equiposEntregadosActuales.size(); i++) {
                if (equipoEntregadoToDelete(empleado.getEquiposEntregados(),  equiposEntregadosActuales.get(i).getIdEquipoEntregado()))
                    entity.removeEquipoEntregado(equiposEntregadosActuales.get(i));
            }
		} else 
		 entity.setEquiposEntregadoEmpleado(new ArrayList<>());
		
		if(empleado.getEquiposEntregados() !=null){
			for (EquipoEntregadoDto equipoEntregado : empleado.getEquiposEntregados()) {
				EquipoEntregado equipoEntregadoEntity = new EquipoEntregado();
				if(equipoEntregado.getIdEquipoEntregado()!= null && equipoEntregado.getIdEquipoEntregado() > 0){
					equipoEntregadoEntity = equipoEntregadoJpaRepository.findOne(equipoEntregado.getIdEquipoEntregado());
				}else
					equipoEntregadoEntity = new EquipoEntregado();
				
				 mapper.map(equipoEntregado,equipoEntregadoEntity);
				equipoEntregadoEntity.setEmpleado(entity);
				
				entity.addEquipoEntregado(equipoEntregadoEntity);
			}
		}
		
		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());
		
		entity.setEmpresa(empresa);
		entity.setCentroCosto(centroCosto);
		
		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	@Transactional
	public NotificacionDto registrarHorarioEmpleado(HorarioEmpleadoDto horarioEmpleado) {
		
		NotificacionDto notificacion = new NotificacionDto();
		HorarioEmpleado entity = new HorarioEmpleado();
				
		mapper.map(horarioEmpleado, entity);
		
		
		Empleado empleadoEntity = empleadoJpaRepository.findOne(horarioEmpleado.getIdEmpleado());
		entity.setEmpleado(empleadoEntity);
		
		if(horarioEmpleado.getIdHorario() != null){
			Horario horarioEntity = horarioJpaRepository.findOne(horarioEmpleado.getIdHorario());
			entity.setHorario(horarioEntity);
			
		}
		
		entity.setHorarioEmpleadoDias(new ArrayList<>());
		
		if(horarioEmpleado.getHorariosEmpleadoDia() !=null){
			for (HorarioEmpleadoDiaDto horarioEmpleadoDia : horarioEmpleado.getHorariosEmpleadoDia()) {
				HorarioEmpleadoDia horarioEmpleadoDiaEntity;
				horarioEmpleadoDiaEntity = mapper.map(horarioEmpleadoDia, HorarioEmpleadoDia.class);
				horarioEmpleadoDiaEntity.setHorarioEmpleado(entity);
					
				entity.getHorarioEmpleadoDias().add(horarioEmpleadoDiaEntity);
			}
		}
		
		
		entity = horarioEmpleadoJpaRepository.save(entity);
		horarioEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	@Transactional
	public NotificacionDto actualizarHorarioEmpleado(HorarioEmpleadoDto horarioEmpleado) {
		
		NotificacionDto notificacion = new NotificacionDto();
		HorarioEmpleado entity = new HorarioEmpleado();
				
		entity = horarioEmpleadoJpaRepository.findOne(horarioEmpleado.getIdHorarioEmpleado());
		
		mapper.map(horarioEmpleado, entity);
		
		
		Empleado empleadoEntity = empleadoJpaRepository.findOne(horarioEmpleado.getIdEmpleado());
		entity.setEmpleado(empleadoEntity);
		
		if(horarioEmpleado.getIdHorario() != null){
			Horario horarioEntity = horarioJpaRepository.findOne(horarioEmpleado.getIdHorario());
			entity.setHorario(horarioEntity);
			
		}
		
				
		if (entity.getHorarioEmpleadoDias() != null) {

            final List<HorarioEmpleadoDia> horarioEmpleadoDiasActuales = entity.getHorarioEmpleadoDias();
            for (int i = 0; i < horarioEmpleadoDiasActuales.size(); i++) {
                if (horarioEmpleadoDiaToDelete(horarioEmpleado.getHorariosEmpleadoDia(),  horarioEmpleadoDiasActuales.get(i).getIdHorarioEmpleadoDia()))
                    entity.removeHorarioEmpleadoDia(horarioEmpleadoDiasActuales.get(i));
            }
		} else 
		 entity.setHorarioEmpleadoDias(new ArrayList<>());
		
		
		if(horarioEmpleado.getHorariosEmpleadoDia() !=null){
			for (HorarioEmpleadoDiaDto horarioDiaDto : horarioEmpleado.getHorariosEmpleadoDia()) {
				
				HorarioEmpleadoDia horarioDiaEntity = new HorarioEmpleadoDia();
				
				horarioDiaEntity = horarioEmpleadoDiaJpaRepository.findOne(horarioDiaDto.getIdHorarioEmpleadoDia());
				
				mapper.map(horarioDiaDto, horarioDiaEntity);
				
				horarioDiaEntity.setLaboral(horarioDiaDto.getLaboral());
								
				horarioDiaEntity.setHorarioEmpleado(entity);
				entity.addHorarioEmpleadoDia(horarioDiaEntity);
			}
		}
		
		entity = horarioEmpleadoJpaRepository.save(entity);
		horarioEmpleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro Correctamente");
		return notificacion;
	}
	
	@Override
	@Transactional
	public NotificacionDto actualizarDatosPersonales(EmpleadoDto empleado) {
		
		NotificacionDto notificacion = new NotificacionDto();
		Empleado entity = new Empleado();
		
		entity = empleadoJpaRepository.findOne(empleado.getIdEmpleado());
		
		mapper.map(empleado, entity);
		
		
		
		if (entity.getExperienciasLaboralesEmpleado() != null) {

            final List<ExperienciaLaboral> experienciasLaboralesActuales = entity.getExperienciasLaboralesEmpleado();
            for (int i = 0; i < experienciasLaboralesActuales.size(); i++) {
                if (experienciaLaboralToDelete(empleado.getExperienciasLaborales(),  experienciasLaboralesActuales.get(i).getIdExperienciaLaboral()))
                    entity.removeExperienciaLaboral(experienciasLaboralesActuales.get(i));
            }
		} else 
		 entity.setExperienciasLaboralesEmpleado(new ArrayList<>());
		
		if(empleado.getExperienciasLaborales() !=null){
			for (ExperienciaLaboralDto experienciaLaboral : empleado.getExperienciasLaborales()) {
				ExperienciaLaboral experienciaLaboralEntity = new ExperienciaLaboral();
				
				if(experienciaLaboral.getIdExperienciaLaboral()!=null && experienciaLaboral.getIdExperienciaLaboral() > 0){
					experienciaLaboralEntity = experienciaLaboralJpaRepository.findOne(experienciaLaboral.getIdExperienciaLaboral());
				}else
					experienciaLaboralEntity = new ExperienciaLaboral();
				
				mapper.map(experienciaLaboral, experienciaLaboralEntity);
				experienciaLaboralEntity.setEmpleado(entity);
				
				entity.addExperienciaLaboral(experienciaLaboralEntity);
			}
		}
		
		if (entity.getDependientesEmpleado() != null) {

            final List<Dependiente> dependientesActuales = entity.getDependientesEmpleado();
            for (int i = 0; i < dependientesActuales.size(); i++) {
                if (dependienteToDelete(empleado.getDependientes(),  dependientesActuales.get(i).getIdDependiente()))
                    entity.removeDependiente(dependientesActuales.get(i));
            }
		} else 
		 entity.setDependientesEmpleado(new ArrayList<>());
		
		if(empleado.getDependientes() !=null){
			for (DependienteDto dependiente : empleado.getDependientes()) {
				Dependiente dependienteEntity = new Dependiente();
				if(dependiente.getIdDependiente()!= null && dependiente.getIdDependiente() > 0){
					dependienteEntity = dependienteJpaRepository.findOne(dependiente.getIdDependiente());
				}else
					dependienteEntity = new Dependiente();
				
				 mapper.map(dependiente,dependienteEntity);
				 dependienteEntity.setEmpleado(entity);
				
				entity.addDependiente(dependienteEntity);
			}
		}
		
		Empresa empresa = empresaJpaRepository.findOne(empleado.getIdEmpresa());
		CentroCosto centroCosto = centroCostoJpaRepository.findOne(empleado.getIdCentroCosto());
		
		entity.setEmpresa(empresa);
		entity.setCentroCosto(centroCosto);
		
		entity = empleadoJpaRepository.save(entity);
		empleadoJpaRepository.flush();
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se actualzo los datos personales Correctamente");
		return notificacion;
	}
	
	private boolean documentoEmpleadoToDelete(List<DocumentoEmpleadoDto> documentos, Long idDocumento) {
        boolean delete = true;
        for (DocumentoEmpleadoDto item : documentos) {
            if (item.getIdDocumentoEmpleado().intValue() == idDocumento.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }
	
	private boolean educacionToDelete(List<EducacionDto> educaciones, Long idEducacion) {
        boolean delete = true;
        for (EducacionDto item : educaciones) {
            if (item.getIdEducacion().intValue() == idEducacion.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }
	
	private boolean experienciaLaboralToDelete(List<ExperienciaLaboralDto> experienciasLaborales, Long idExperienciaLaboral) {
        boolean delete = true;
        for (ExperienciaLaboralDto item : experienciasLaborales) {
            if (item.getIdExperienciaLaboral().intValue() == idExperienciaLaboral.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }
	
	private boolean equipoEntregadoToDelete(List<EquipoEntregadoDto> equiposEntregados, Long idEquipoEntregado) {
        boolean delete = true;
        for (EquipoEntregadoDto item : equiposEntregados) {
            if (item.getIdEquipoEntregado().intValue() == idEquipoEntregado.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }
	
	private boolean dependienteToDelete(List<DependienteDto> dependientes, Long idDependiente) {
        boolean delete = true;
        for (DependienteDto item : dependientes) {
            if (item.getIdDependiente().intValue() == idDependiente.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }

	@Override
	public List<HistoriaLaboralDto> obtenerHistoriaLaboral(Long idEmpleado) {
		// TODO Auto-generated method stub
		return historiaLaboralRepository.obtenerHistoriaLaboral(idEmpleado);
	}

	@Override
	public List<HistoriaLaboralDto> obtenerIdHistoriaLaboral(Long idHistorialLaboral) {
		// TODO Auto-generated method stub
		return historiaLaboralRepository.obtenerIdHistoriaLaboral(idHistorialLaboral);
	}
	
	@Override
	public List<EmpleadoDto> busquedaEmpleado(BusquedaEmpleadoDto busquedaEmpleadoDto) {
		return empleadoJdbcRepository.busquedaEmpleado(busquedaEmpleadoDto);
	}
	
	@Override
	public List<MarcacionDto> busquedaMarcacionesEmpleado(BusquedaMarcacionDto busquedaMarcacionDto) {
		return empleadoJdbcRepository.busquedaMarcacionEmpleado(busquedaMarcacionDto);
	}

	@Override
	public EmpleadoDto verEmpleado(EmpleadoDto empleado) {
		
		EmpleadoDto dto;
		dto = empleadoJdbcRepository.verEmpleado(empleado);
		
		return dto;
	}
	
	@Override
	public List<DocumentoEmpleadoDto> verDocumentos(EmpleadoDto empleado) {
		
		List<DocumentoEmpleado> entity;
		List<DocumentoEmpleadoDto> dto = new ArrayList<>();
		entity = documentoEmpleadoJpaRepository.buscarDocumentosPorEmpleado(empleado.getIdEmpleado());
		if(entity != null)
			dto = entity.stream().map(m -> mapper.map(m, DocumentoEmpleadoDto.class)).collect(toList());
		
		return dto;
	}
	
	@Override
	public List<EducacionDto> verEducacion(EmpleadoDto empleado) {
		
		List<EducacionDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verEducacion(empleado);
		return dto;
	}
	
	@Override
	public List<ExperienciaLaboralDto> verExperienciaLaboral(EmpleadoDto empleado) {
		
		List<ExperienciaLaboral> entity;
		List<ExperienciaLaboralDto> dto = new ArrayList<>();
		entity = experienciaLaboralJpaRepository.buscarExperienciaLaboralPorEmpleado(empleado.getIdEmpleado());
		if(entity != null)
			dto = entity.stream().map(m -> mapper.map(m, ExperienciaLaboralDto.class)).collect(toList());
		
		return dto;
	}
	
	@Override
	public List<EquipoEntregadoDto> verEquipoEntregado(EmpleadoDto empleado) {
		
		List<EquipoEntregadoDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verEquipoEntregado(empleado);
		return dto;
	}
	
	@Override
	public List<DependienteDto> verDependiente(EmpleadoDto empleado) {
		
		List<DependienteDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verDependiente(empleado);
		return dto;
	}
	
	@Override
	public List<LicenciaDto> verLicencia(PeriodoEmpleadoDto periodoEmpleado) {
		
		List<LicenciaDto> dto = new ArrayList<>();
		if(periodoEmpleado.getIdPeriodoEmpleado() != null){
		
			PeriodoEmpleado entity = periodoEmpleadoJpaRepository.findOne(periodoEmpleado.getIdPeriodoEmpleado());
			if(entity != null){
				periodoEmpleado.setFechaInicio(entity.getFechaInicio());
				periodoEmpleado.setFechaFin(entity.getFechaFin());
			}else{
				periodoEmpleado.setIdPeriodoEmpleado(null);
			}
		}
		dto = empleadoJdbcRepository.verLicencia(periodoEmpleado);
		
		
		
		return dto;
	}

    @Override
    public List<EmpleadoDto> procesarMasivamenteEmpleados(List<EmpleadoDto> dtos) {

        try {

            ListEmpleadosNuevosYAntiguos listEmpleadosNuevosYAntiguos = validarSiEmpleadosExisten(dtos);

            validarInfoDeEmpleados(listEmpleadosNuevosYAntiguos.getEmpleadosAntiguos(), true);

            processUpdateEmpleado(listEmpleadosNuevosYAntiguos.getEmpleadosAntiguos());


            validarInfoDeEmpleados(listEmpleadosNuevosYAntiguos.getEmpleadosNuevos(), false);


            processSaveEmpleado(listEmpleadosNuevosYAntiguos.getEmpleadosNuevos());

            return dtos;
        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = e.getMessage() == null ? "" : e.getMessage();

            throw new GenericRestException("ERR_001", msg);
        }
    }

    private void processSaveEmpleado(List<EmpleadoDto> dtos) {

        try {

            for(EmpleadoDto empleadoItem :dtos){
                if(empleadoItem.getStatusProcessDtoList().size()==0){


                    Long  idEmpleado = processDataSaveEmpleado(empleadoItem);

                    empleadoItem.setIdEmpleado(idEmpleado);
                    StatusProcessDto statusProcessDto = new StatusProcessDto();
                    statusProcessDto.setStatus("Processed");
                    statusProcessDto.setMessage("");
                    empleadoItem.getStatusProcessDtoList().add(statusProcessDto);
                    log.info("Processed ");


                }

            }
        }catch (Exception e){
            log.debug("ERROR", e);

            String msg = e.getMessage()==null?"":e.getMessage();

            throw new GenericRestException("ERR_001",msg);
        }



    }

    private Long processDataSaveEmpleado(EmpleadoDto dto) {


        try {
            log.info("Creando Empleado");
            Empleado empleado = new Empleado();
            empleado.setCodigo(dto.getCodigo());
            empleado.setNombre(dto.getNombre());
            empleado.setApellidoMaterno(dto.getApellidoMaterno());
            empleado.setApellidoPaterno(dto.getApellidoPaterno());
            empleado.setTipoDocumento(dto.getTipoDocumento());
            empleado.setNumeroDocumento(dto.getNumeroDocumento());
            empleado.setGenero(dto.getGenero());
            empleado.setEstadoCivil(dto.getEstadoCivil());
            empleado.setGrupoSangre(dto.getGrupoSangre());
            empleado.setDiscapacitado(dto.getDiscapacitado());
            empleado.setFechaNacimiento(dto.getFechaNacimiento());
            empleado.setPaisNacimiento(dto.getPaisNacimiento());
            empleado.setEmailInterno(dto.getEmailInterno());
            empleado.setTelefonoInterno(dto.getTelefonoInterno());
            empleado.setAnexoInterno(dto.getAnexoInterno());
            empleado.setCentroCosto(dto.getCentroCosto());
            empleado.setContratoTrabajo(dto.getContratoTrabajo());
            empleado.setTipoTrabajador(dto.getTipoTrabajador());
            empleado.setRegimenHorario(dto.getRegimenHorario());
            empleado.setRegimenLaboral(dto.getRegimenLaboral());
            empleado.setEsPersonalDeConfianza(dto.getEsPersonalDeConfianza());
            empleado.setDireccionDomicilio(dto.getDireccionDomicilio());
            empleado.setTipoDomicilio(dto.getTipoDomicilio());
            empleado.setDistritoDomicilio(dto.getDistritoDomicilio());
            empleado.setTelefonoCasa(dto.getTelefonoCasa());
            empleado.setEmailPersonal(dto.getEmailPersonal());
            empleado.setRelacionContactoEmergencia(dto.getRelacionContactoEmergencia());
            empleado.setNombreContactoEmergencia(dto.getNombreContactoEmergencia());
            empleado.setTelefonoContactoEmergencia(dto.getTelefonoContactoEmergencia());
            empleado.setEmailContactoEmergencia(dto.getEmailContactoEmergencia());
            empleado.setEstado(dto.getEstado());

            //TODO: HARDCODE por ahora
            empleado.setEmpresa(empresaJpaRepository.findOne(4L));
            empleado.setFechaIngreso(new Date());
            empleado.setTelefonoCelular("");

            empleadoJpaRepository.save(empleado);
            empleadoJpaRepository.flush();

            log.info("Empleado creado [id:" + empleado.getIdEmpleado() + ", codigo: " + empleado.getCodigo() + "]");

            return empleado.getIdEmpleado();

        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = ((e.getMessage() == null) ? "" : e.getMessage());

            throw new GenericRestException("ERR_001", msg);
        }
    }

    @Override
    public Long processDataUpdateEmpleado(EmpleadoDto dto) throws Exception {
        //TODO deberia usar un mapper
        Empleado empleado = empleadoJpaRepository.findByCodigoExacto(dto.getCodigo());
        empleado.setNombre(dto.getNombre());
        empleado.setApellidoMaterno(dto.getApellidoMaterno());
        empleado.setApellidoPaterno(dto.getApellidoPaterno());
        empleado.setTipoDocumento(dto.getTipoDocumento());
        empleado.setNumeroDocumento(dto.getNumeroDocumento());
        empleado.setGenero(dto.getGenero());
        empleado.setEstadoCivil(dto.getEstadoCivil());
        empleado.setGrupoSangre(dto.getGrupoSangre());
        empleado.setDiscapacitado(dto.getDiscapacitado());
        empleado.setFechaNacimiento(dto.getFechaNacimiento());
        empleado.setPaisNacimiento(dto.getPaisNacimiento());
        empleado.setEmailInterno(dto.getEmailInterno());
        empleado.setTelefonoInterno(dto.getTelefonoInterno());
        empleado.setAnexoInterno(dto.getAnexoInterno());
        //TODO Centro de costo
        empleado.setContratoTrabajo(dto.getContratoTrabajo());
        empleado.setTipoTrabajador(dto.getTipoTrabajador());
        empleado.setRegimenHorario(dto.getRegimenHorario());
        empleado.setRegimenLaboral(dto.getRegimenLaboral());
        empleado.setEsPersonalDeConfianza(dto.getEsPersonalDeConfianza());
        empleado.setDireccionDomicilio(dto.getDireccionDomicilio());
        empleado.setTipoDomicilio(dto.getTipoDomicilio());
        empleado.setDistritoDomicilio(dto.getDistritoDomicilio());
        empleado.setTelefonoCasa(dto.getTelefonoCasa());
        empleado.setEmailPersonal(dto.getEmailPersonal());
        empleado.setRelacionContactoEmergencia(dto.getRelacionContactoEmergencia());
        empleado.setNombreContactoEmergencia(dto.getNombreContactoEmergencia());
        empleado.setTelefonoContactoEmergencia(dto.getTelefonoContactoEmergencia());
        empleado.setEmailContactoEmergencia(dto.getEmailContactoEmergencia());
        empleado.setEstado(dto.getEstado());

        try {
            empleadoJpaRepository.save(empleado);
            empleadoJpaRepository.flush();
            log.info("Empleado actualizado [id:" + empleado.getIdEmpleado() + ", codigo: " + empleado.getCodigo() + "]");

            return empleado.getIdEmpleado();

        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = ((e.getMessage() == null) ? "" : e.getMessage());
            if (e instanceof GenericRestException) {
                throw new GenericRestException("ERR_001", msg);
            } else if (e instanceof ObjectOptimisticLockingFailureException) {
                throw new GenericRestException("ERROR",
                        "The record was changed by another user. Please re-enter the screen.");
            } else if (e instanceof EmptyResultDataAccessException) {
                throw new GenericRestException("ERROR",
                        "The record was changed by another user. Please re-enter the screen.");
            } else {
                throw new GenericRestException("ERR_001", msg);
            }

        }


    }

    @Override
    public List<PermisoEmpleadoDto> busquedaPermisoEmpleado(BusquedaPermisoEmpleadoDto busquedaPermisoEmpleadoDto) {
        return empleadoJdbcRepository.busquedaPermisoEmpleado(busquedaPermisoEmpleadoDto);
    }

    private void processUpdateEmpleado(List<EmpleadoDto> dtos) {

        try {
            for (EmpleadoDto empleadoItem : dtos) {


                if (empleadoItem.getStatusProcessDtoList().size() == 0) {

                    Long empleadoId = this.processDataUpdateEmpleado(empleadoItem);

                    empleadoItem.setIdEmpleado(empleadoId);
                    StatusProcessDto statusProcessDto = new StatusProcessDto();
                    statusProcessDto.setStatus("Processed");
                    statusProcessDto.setMessage("");
                    empleadoItem.getStatusProcessDtoList().add(statusProcessDto);
                    log.info("Processed ");

                }

            }

        } catch (Exception e) {
            log.debug("ERROR", e);
            String msg = e.getMessage() == null ? "" : e.getMessage();

            throw new GenericRestException("ERR_001", msg);
        }


    }


    private void validarInfoDeEmpleados(List<EmpleadoDto> dtos, boolean isEdit) {

        for (EmpleadoDto empleadoItem : dtos) {

            List<StatusProcessDto> statusProcessListExistInfo = validateDataMasiveEmpleado(empleadoItem,false);
            if(statusProcessListExistInfo.size()>0){
                empleadoItem.getStatusProcessDtoList().addAll(statusProcessListExistInfo);
            }

        }
    }

    private List<StatusProcessDto> validateDataMasiveEmpleado(EmpleadoDto empleadoItem, boolean b) {

        List<StatusProcessDto> statusProcessDtos = new ArrayList<>();
        StatusProcessDto statusProcessDto = new StatusProcessDto();

        if (!StringUtils.isBlank(empleadoItem.getTipoDocumentoString())) {
            //TODO ver si no se pone en duro y se usa constante para Empleado.TipoDocumento
            TablaGeneral tablaTipoDocumento = tablaGeneralJpaRepository.findByGrupoAndNombre("Empleado.TipoDocumento", empleadoItem.getTipoDocumentoString());
            empleadoItem.setTipoDocumento(tablaTipoDocumento.getCodigo());
        }

        if (!StringUtils.isBlank(empleadoItem.getTipoDomicilioString())) {
            //TODO ver si no se pone en duro y se usa constante para Empleado.TipoDocumento
            TablaGeneral tablaTipoDomicilio = tablaGeneralJpaRepository.findByGrupoAndNombre("Empleado.TipoDomicilio", empleadoItem.getTipoDomicilioString());
            empleadoItem.setTipoDomicilio(tablaTipoDomicilio.getCodigo());
        }


        if (!StringUtils.isBlank(empleadoItem.getCentroCostoString())) {
            CentroCosto centroCosto = centroCostoJpaRepository.findByNombreExacto(empleadoItem.getCentroCostoString());
            if(centroCosto!=null)
                empleadoItem.setCentroCosto(centroCosto);
            else{
                statusProcessDtos.add(new StatusProcessDto("Error", "Centro de Costo no existe"));
            }
        }

        return statusProcessDtos;

    }

    private ListEmpleadosNuevosYAntiguos validarSiEmpleadosExisten(List<EmpleadoDto> dto) {
        ListEmpleadosNuevosYAntiguos contenedor = new ListEmpleadosNuevosYAntiguos();
        for (EmpleadoDto empleadoItem : dto) {
            Empleado empleadoEncontrado = empleadoJpaRepository.findByCodigoExacto(empleadoItem.getCodigo());
            if (empleadoEncontrado != null) {
                contenedor.getEmpleadosAntiguos().add(empleadoItem);
            }else{
                contenedor.getEmpleadosNuevos().add(empleadoItem);
            }
        }
        return contenedor;
    }

	@Override
	public HorarioEmpleadoDto verHorarioEmpleado(EmpleadoDto empleado) {
		HorarioEmpleadoDto dto = new HorarioEmpleadoDto();
		dto = empleadoJdbcRepository.verHorarioEmpleado(empleado);
		if(dto != null){
			List<HorarioEmpleadoDiaDto> horarioEmpleadoDiaDto = empleadoJdbcRepository.verHorarioEmpleadoDia(dto);
			dto.setHorariosEmpleadoDia(horarioEmpleadoDiaDto);
		}
		return dto;
	}
	
	@Override
	public List<HorarioEmpleadoDiaDto> obtenerHorarioEmpleadoDiasPorHorarioEmpleado(HorarioEmpleadoDto horarioEmpleadoDto) {
		
		List<HorarioEmpleadoDiaDto> dto = empleadoJdbcRepository.verHorarioEmpleadoDia(horarioEmpleadoDto);
			
		return dto;
	}
		
	@Override
	public List<PeriodoEmpleadoDto> verPeriodoEmpleado(EmpleadoDto empleado) {
		List<PeriodoEmpleadoDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verPeriodoEmpleado(empleado);
		
		return dto;
	}
	
	@Override
	public List<PermisoEmpleadoDto> verPermisoEmpleado(PeriodoEmpleadoDto periodoEmpleado) {
		List<PermisoEmpleadoDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verPermisoEmpleado(periodoEmpleado);
		
		return dto;
	}
	
	@Override
	public List<VacacionDto> verVacacion(PeriodoEmpleadoDto periodoEmpleado) {
		List<VacacionDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verVacaciones(periodoEmpleado);
		
		return dto;
	}
	
	@Override
	public List<MarcacionDto> verMarcacion(EmpleadoDto empleado) {
		List<MarcacionDto> dto = new ArrayList<>();
		dto = empleadoJdbcRepository.verMarcaciones(empleado);
		
		return dto;
	}

    @Override
    public Long eliminarPermisoEmpleado(Long idPermisoEmpleado) {

        if (permisoEmpleadoJpaRepository.findOne(idPermisoEmpleado) == null) {
            throw new GenericRestException("ERROR", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
            permisoEmpleadoJpaRepository.delete(idPermisoEmpleado);
            permisoEmpleadoJpaRepository.flush();
            log.info("Deleted in ROUTING_RESTRICTION where id was <" + idPermisoEmpleado + ">");
        } catch (Exception e) {
            throw new GenericRestException("ERR_001", "Restriction is Being used, Can't be deleted");
        }

        return idPermisoEmpleado;
    }

	@Override
	public List<PermisoEmpleadoDto> obtenerCodigoPermiso(String codigo) {
		List<PermisoEmpleado> entityPermisoEmpleado = permisoEmpleadoJpaRepository.findByCodigo(codigo);
		
		List<PermisoEmpleadoDto> items;
		
		items = entityPermisoEmpleado.stream().map(m -> mapper.map(m, PermisoEmpleadoDto.class)).collect(toList());
		return items;
	}

	@Override
	public List<VacacionEmpleadoDto> busquedaVacacionesEmpleado(
			BusquedaVacacionesEmpleadoDto busquedaVacacionesEmpleadoDto) {
		return empleadoJdbcRepository.busquedaVacacionesEmpleado(busquedaVacacionesEmpleadoDto);
	}

	@Override
	public List<HorasExtraDto> busquedaHorasExtrasEmpleado(
			BusquedaHorasExtraEmpleadoDto busquedaHorasExtraEmpleadoDto) {
		// TODO Auto-generated method stub
		return empleadoJdbcRepository.busquedaHorasExtrasEmpleado(busquedaHorasExtraEmpleadoDto);
	}

	@Override
	public HorasExtraDto informacionAdicionalHorasExtras(EmpleadoDto empleado) {
		
		HorasExtraDto dto = new HorasExtraDto();
		Integer dayOfWeek = getDayOfWeek(empleado.getFechaIngreso());
		dto = empleadoJdbcRepository.getHorarioEmpleadoDia(empleado.getIdEmpleado(), dayOfWeek);
		
		return dto;
	}
	public static int getDayOfWeek(Date aDate) {
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(aDate);
	    return cal.get(Calendar.DAY_OF_WEEK);
	}

	@Override
	public NotificacionDto registrarHorasExtra(HorasExtraDto horasExtraDto) {
		NotificacionDto notificacion = new NotificacionDto();
		
		List<HorasExtraDto> listHorasExtra = empleadoJdbcRepository.listHorasExtraEmpleado(horasExtraDto.getIdEmpleado());

		for(HorasExtraDto next: listHorasExtra){
			if(horasExtraDto.getFecha().equals(next.getFecha())){
				boolean isWithin = ValidationUtils.isWithinRangeHours(horasExtraDto.getHoraSalidaSolicitado(), 
																next.getHoraSalidaHorario(), next.getHoraSalidaSolicitado());
				if(horasExtraDto.getHoraSalidaSolicitado().equals(next.getHoraSalidaSolicitado()) || isWithin==true){
					notificacion.setCodigo(0L);
					notificacion.setMensaje("No es posible registrar, la hora extra se cruza con el código: " +
											next.getIdHorasExtra() +" Desde: "+next.getHoraSalidaHorario() +" Hasta: "+next.getHoraSalidaSolicitado());
					return notificacion;
				}
				
			}
			
		}
		
		HorasExtra entity;
		if(horasExtraDto.getIdHorasExtra() == null)
			entity = new HorasExtra();
		else entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		
		Empleado empleado = empleadoJpaRepository.findOne(horasExtraDto.getIdEmpleado());
		entity.setEmpleado(empleado);
		
		entity.setFecha(horasExtraDto.getFecha());
		entity.setHoraSalidaHorario(horasExtraDto.getHoraSalidaHorario());
		Empleado empleadoJefe = empleadoJpaRepository.findOne(horasExtraDto.getIdAtendidoPor());
		entity.setIdAtendidoPor(empleadoJefe);
		entity.setHoraSalidaSolicitado(horasExtraDto.getHoraSalidaSolicitado());
		entity.setHoras(Double.parseDouble(horasExtraDto.getHoras()));
		entity.setMotivo(horasExtraDto.getMotivo());
		entity.setEstado("P");
//		entity.setHorasCompensadas(Double.parseDouble(horasExtraDto.getHorasCompensadas()));
		
		horasExtraJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("Se registro correctamente");
		return notificacion;
	}

	@Override
	public List<EquipoEntregadoDto> obtenerEquiposPendientesDevolucion(Long idEmpleado) {
		// TODO Auto-generated method stub
		return empleadoJdbcRepository.obtenerEquiposPendientesDevolucion(idEmpleado);
	}

	@Override
	public NotificacionDto countEquiposPendientesDevolucion(EmpleadoDto empleadoDto) {
		// TODO Auto-generated method stub
		NotificacionDto notificacion = new NotificacionDto();
		Long totalCount = empleadoJdbcRepository.countEquiposPendientesDevolucion(empleadoDto.getIdEmpleado());
		if(totalCount > 0){
			notificacion.setCodigo(1L);
			notificacion.setMensaje("Hay equipos pendientes de devolución");
		}
		return notificacion;
		
	}

	@Override
	public  List<HorarioEmpleadoDto> verHorariosEmpleado(EmpleadoDto empleado) {
		List<HorarioEmpleadoDto> horarios = new ArrayList<>();
		horarios = empleadoJdbcRepository.verHorariosEmpleado(empleado);
		
		return horarios;
	}
	
	private boolean horarioEmpleadoDiaToDelete(List<HorarioEmpleadoDiaDto> horarioEmpleadoDias, Long idHorarioEmpleadoDia) {
        boolean delete = true;
        for (HorarioEmpleadoDiaDto item : horarioEmpleadoDias) {
            if (item.getIdHorarioEmpleadoDia().intValue() == idHorarioEmpleadoDia.intValue()) {
                delete = false;
                break;
            }
        }
        return delete;
    }

	@Override
	public Long eliminarHorasExtra(Long idHorasExtra) {
		if (horasExtraJpaRepository.findOne(idHorasExtra) == null) {
            throw new GenericRestException("ERROR", "The record was changed by another user. Please re-enter the screen.");
        }
        try {
        	horasExtraJpaRepository.delete(idHorasExtra);
        	horasExtraJpaRepository.flush();
        } catch (Exception e) {
            throw new GenericRestException("ERR_001", "Restriction is Being used, Can't be deleted");
        }

        return idHorasExtra;
	}

	@Override
	public NotificacionDto rechazarHorasExtra(HorasExtraDto horasExtraDto) {
		NotificacionDto notificacion = new NotificacionDto();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("R");
		horasExtraJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue rechazado correctamente");
		return notificacion;
	}

	@Override
	public NotificacionDto aprobarHorasExtra(HorasExtraDto horasExtraDto) {
		NotificacionDto notificacion = new NotificacionDto();
		HorasExtra entity = horasExtraJpaRepository.findOne(horasExtraDto.getIdHorasExtra());
		entity.setEstado("A");
		horasExtraJpaRepository.save(entity);
		
		notificacion.setCodigo(1L);
		notificacion.setMensaje("El registro fue aprobado correctamente");
		return notificacion;
	}
}
