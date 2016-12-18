package pe.com.tss.runakuna.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Alerta;
import pe.com.tss.runakuna.domain.model.entities.AlertaSubscriptor;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Empresa;
import pe.com.tss.runakuna.domain.model.repository.jdbc.AlertaJdbcRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.AlertaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AlertaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.AlertaSubscriptorJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpresaJpaRepository;
import pe.com.tss.runakuna.dto.AlertaDto;
import pe.com.tss.runakuna.dto.AlertaSubscriptorDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.service.AlertaService;

/**
 * Created by josediaz on 14/12/2016.
 */
@Service
public class AlertaServiceImpl implements AlertaService {

    @Autowired
    private AlertaRepository alertaRepository;
    
    @Autowired
    private AlertaJpaRepository alertaJpaRepository;
    
    @Autowired
    private AlertaJdbcRepository alertaJdbcRepository;
    
    @Autowired
    private EmpresaJpaRepository empresaJpaRepository;
    
    @Autowired
	Mapper mapper;
    
	@Autowired
	private EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	private AlertaSubscriptorJpaRepository alertaSubscriptorJpaRepository;
    
    @Override
    public AlertaDto obtenerAlerta(String codigo) {
        return alertaRepository.obtenerAlerta(codigo);
    }
	@Override
	public NotificacionDto registrarAlerta(AlertaDto alertaDto) {
		NotificacionDto notificacionDto=new NotificacionDto();
		Alerta entity=new Alerta();
		entity.setSubscriptores(new ArrayList<>());
		Empresa empresa=empresaJpaRepository.findOne(alertaDto.getIdEmpresa());
		AlertaSubscriptor alertaSubscriptor=null;
		try {
			entity.setEmpresa(empresa);
			entity.setTipoAlerta(alertaDto.getTipoAlerta());
			entity.setCodigo(alertaDto.getCodigo());
			entity.setCorreoElectronico(alertaDto.getCorreoElectronico());
			entity.setDashboard(alertaDto.getDashboard());
			entity.setPersonalAsunto(alertaDto.getPersonalAsunto());
			entity.setPersonalCuerpo(alertaDto.getPersonalCuerpo());
			entity.setGrupalAsunto(alertaDto.getGrupalAsunto());
			entity.setGrupalCuerpo(alertaDto.getGrupalCuerpo());
			entity.setJefeInmediato(alertaDto.getJefeInmediato());
			entity.setEstado(alertaDto.getEstado());
			if(alertaDto.getSubscriptores()!=null){
				for(AlertaSubscriptorDto subscriptor: alertaDto.getSubscriptores()){
					alertaSubscriptor =new AlertaSubscriptor();
					alertaSubscriptor.setAlerta(entity);
					Empleado empleado=empleadoJpaRepository.findOne(subscriptor.getIdEmpleado());
					alertaSubscriptor.setEmpleado(empleado);
					entity.getSubscriptores().add(alertaSubscriptor);
				}
			}
			
			alertaJpaRepository.save(entity);
			notificacionDto.setCodigo(1L);
			notificacionDto.setMensaje("Se registro correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setMensaje("No es posible registrar, "+e.getMessage());
		}
		return notificacionDto;
	}
	
	@Override
	public NotificacionDto actualizarAlerta(AlertaDto alertaDto) {
		NotificacionDto notificacionDto=new NotificacionDto();
		Alerta entity=new Alerta();
		Empresa empresa=empresaJpaRepository.findOne(alertaDto.getIdEmpresa());
		try{
			entity= alertaJpaRepository.findOne(alertaDto.getIdAlerta());
			entity.setEmpresa(empresa);
			entity.setTipoAlerta(alertaDto.getTipoAlerta());
			entity.setCodigo(alertaDto.getCodigo());
			entity.setCorreoElectronico(alertaDto.getCorreoElectronico());
			entity.setDashboard(alertaDto.getDashboard());
			entity.setPersonalAsunto(alertaDto.getPersonalAsunto());
			entity.setPersonalCuerpo(alertaDto.getPersonalCuerpo());
			entity.setGrupalAsunto(alertaDto.getGrupalAsunto());
			entity.setGrupalCuerpo(alertaDto.getGrupalCuerpo());
			entity.setJefeInmediato(alertaDto.getJefeInmediato());
			entity.setEstado(alertaDto.getEstado());
			
			if (entity.getSubscriptores() != null) {
	            final List<AlertaSubscriptor> subscriptoresActuales = entity.getSubscriptores();
	            for (int i = 0; i < subscriptoresActuales.size(); i++) {
	                if (alertaSubscriptorToDelete(alertaDto.getSubscriptores(),  subscriptoresActuales.get(i).getIdAlertaSubscriptor()))
	                    entity.removeAlertaSubscriptor(subscriptoresActuales.get(i));
	            }
			} else 
				entity.setSubscriptores(new ArrayList<>());
			if(alertaDto.getSubscriptores()!=null){
				for(AlertaSubscriptorDto subscriptor: alertaDto.getSubscriptores()){
					AlertaSubscriptor alertaSubscriptor=new AlertaSubscriptor();
					if(subscriptor.getIdAlertaSubscriptor()!=null && subscriptor.getIdAlertaSubscriptor().intValue()>0) {
						alertaSubscriptor = alertaSubscriptorJpaRepository.findOne(subscriptor.getIdAlertaSubscriptor());
					} else 
						alertaSubscriptor= new AlertaSubscriptor();
					mapper.map(subscriptor, alertaSubscriptor);
					alertaSubscriptor.setAlerta(entity);
					alertaSubscriptor.setEmpleado(empleadoJpaRepository.findOne(subscriptor.getIdEmpleado()));
					entity.getSubscriptores().add(alertaSubscriptor);
					
				}
			
			}	
			alertaJpaRepository.save(entity);
			notificacionDto.setCodigo(1L);
			notificacionDto.setMensaje("Se actualizo correctamente");
		} catch (Exception e) {
			
			notificacionDto.setCodigo(0L);
			notificacionDto.setMensaje("No es posible actualizar, "+e.getMessage());
		}
		
		return notificacionDto;
	}
	
	private boolean alertaSubscriptorToDelete(List<AlertaSubscriptorDto> subscripciones, Long idAlertaSubscriptor) {
        boolean delete = true;
        for (AlertaSubscriptorDto item : subscripciones) {
            if (item.getIdAlertaSubscriptor()!=null && (item.getIdAlertaSubscriptor().intValue() == idAlertaSubscriptor.intValue())) {
                delete = false;
                break;
            }
        }
        return delete;
    }
	@Override
	public List<AlertaDto> obtenerAlertas(AlertaDto alertaDto) {
		
		return alertaJdbcRepository.obtenerAlertas(alertaDto);
	}
	@Override
	public AlertaDto obtenerAlertaDetalle(Long idAlerta) {
		AlertaDto dto=new AlertaDto();
		dto.setSubscriptores(new ArrayList<>());
		Alerta alerta=alertaJpaRepository.findOne(idAlerta);
		dto.setIdAlerta(alerta.getIdAlerta());
		dto.setCodigo(alerta.getCodigo());
		dto.setIdEmpresa(alerta.getEmpresa().getIdEmpresa());
		dto.setCorreoElectronico(alerta.getCorreoElectronico());
		dto.setDashboard(alerta.getDashboard());
		dto.setEstado(alerta.getEstado());
		dto.setGrupalAsunto(alerta.getGrupalAsunto());
		dto.setGrupalCuerpo(alerta.getGrupalCuerpo());
		dto.setJefeInmediato(alerta.getJefeInmediato());
		dto.setPersonalAsunto(alerta.getPersonalAsunto());
		dto.setPersonalCuerpo(alerta.getPersonalCuerpo());
		dto.setTipoAlerta(alerta.getTipoAlerta());
		for(AlertaSubscriptor alertaSubscriptor:alerta.getSubscriptores()){
			AlertaSubscriptorDto alertaSubscriptorDto=new AlertaSubscriptorDto();
			mapper.map(alertaSubscriptor, alertaSubscriptorDto);
			alertaSubscriptorDto.setIdAlerta(dto.getIdAlerta());
			alertaSubscriptorDto.setIdEmpleado(alertaSubscriptor.getEmpleado().getIdEmpleado());
			dto.getSubscriptores().add(alertaSubscriptorDto);
		}
		
		
		return dto;
	}
	
}
