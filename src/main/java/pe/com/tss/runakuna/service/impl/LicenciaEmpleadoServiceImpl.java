package pe.com.tss.runakuna.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.DocumentoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.Licencia;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleadoTipoLicencia;
import pe.com.tss.runakuna.domain.model.entities.TipoLicencia;
import pe.com.tss.runakuna.domain.model.repository.jdbc.LicenciaEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoRepository;
import pe.com.tss.runakuna.domain.model.repository.jdbc.PeriodoEmpleadoTipoLicenciaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DocumentoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.LicenciaEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoTipoLicenciaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.TipoLicenciaJpaRepository;
import pe.com.tss.runakuna.dto.BusquedaLicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.DocumentoEmpleadoDto;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.dto.LicenciaEmpleadoDto;
import pe.com.tss.runakuna.dto.NotificacionDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoDto;
import pe.com.tss.runakuna.dto.PeriodoEmpleadoTipoLicenciaDto;
import pe.com.tss.runakuna.exception.GenericRestException;
import pe.com.tss.runakuna.service.LicenciaEmpleadoService;
import pe.com.tss.runakuna.util.FileUtils;

@Service
public class LicenciaEmpleadoServiceImpl implements LicenciaEmpleadoService{

	private static final Logger log = LoggerFactory.getLogger(LicenciaEmpleadoServiceImpl.class);
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	LicenciaEmpleadoRepository licenciaEmpleadoRepository;
	
	@Autowired
	LicenciaEmpleadoJpaRepository licenciaEmpleadoJpaRepository;
	
	@Autowired
	TipoLicenciaJpaRepository tipoLicenciaJpaRepository;
	
	@Autowired
	DocumentoEmpleadoJpaRepository documentoEmpleadoJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoRepository periodoEmpleadoRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository  periodoEmpleadoJpaRepository;
	
	@Autowired
	PeriodoEmpleadoTipoLicenciaRepository  periodoEmpleadoTipoLicenciaRepository;
	
	@Autowired
	PeriodoEmpleadoTipoLicenciaJpaRepository periodoEmpleadoTipoLicenciaJpaRepository;
	
	@Override
	public NotificacionDto registrarLicenciaEmpleado(LicenciaEmpleadoDto licenciaEmpleadoDto) {
		log.info("INICIO REGISTRAR LICENCIA");
		Licencia entity;
		if(licenciaEmpleadoDto.getIdLicencia() == null)
			entity = new Licencia();
		else entity = licenciaEmpleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdLicencia());
		
		NotificacionDto notificacion = new NotificacionDto();
		Empleado empleado=empleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdEmpleado());
		TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(licenciaEmpleadoDto.getIdTipoLicencia());
		try{
			entity.setEmpleado(empleado);
			entity.setComentario(licenciaEmpleadoDto.getComentario());
			entity.setFechaInicio(licenciaEmpleadoDto.getFechaInicio());
			entity.setFechaFin(licenciaEmpleadoDto.getFechaFin());
			entity.setDias(licenciaEmpleadoDto.getDias());
			entity.setEstado(licenciaEmpleadoDto.getEstado());
			entity.setTipoLicencia(tipoLicencia);
			entity.setDiaEntero(licenciaEmpleadoDto.getDiaEntero());
			entity.setHoraInicio(licenciaEmpleadoDto.getHoraInicio());
			entity.setHoraFin(licenciaEmpleadoDto.getHoraFin());
			entity.setDocumentosEmpleado(new ArrayList<>());
			if(licenciaEmpleadoDto.getDocumentos()!=null) {
				for (DocumentoEmpleadoDto documento : licenciaEmpleadoDto.getDocumentos()){
					DocumentoEmpleado documentoEntity;
					documentoEntity = mapper.map(documento, DocumentoEmpleado.class);
					documentoEntity.setLicencia(entity);
					
					File file=new File("d:/Oscar/pendientes_mejoras.txt");
					byte[] contenido=new byte[1024];
					FileUtils.writeToFile(file, contenido);
					documentoEntity.setContenidoArchivo(contenido);
					
					documentoEntity.setEmpleado(empleado);
					entity.getDocumentosEmpleado().add(documentoEntity);
				}
			}
			
			licenciaEmpleadoJpaRepository.save(entity);
			notificacion.setCodigo(1L);
			notificacion.setMensaje("Se registro correctamente");
		} catch(Exception e) {
			
			notificacion.setCodigo(0L);
			notificacion.setMensaje("No es posible registrar, "+e.getMessage());
		}
		log.info("FIN REGISTRAR LICENCIA");
			
		return notificacion;
	}

	@Override
	public NotificacionDto actualizarLicenciaEmpleado(LicenciaEmpleadoDto licenciaEmpleadoDto) {
		log.info("INICIO ACTUALIZAR LICENCIA");
		NotificacionDto notificacion = new NotificacionDto();
		Licencia  entity = licenciaEmpleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdLicencia());
		Empleado empleado=empleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdEmpleado());
		TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(licenciaEmpleadoDto.getIdTipoLicencia());
		try{
			entity.setEmpleado(empleado);
			entity.setComentario(licenciaEmpleadoDto.getComentario());
			entity.setFechaInicio(licenciaEmpleadoDto.getFechaInicio());
			entity.setFechaFin(licenciaEmpleadoDto.getFechaFin());
			entity.setDias(licenciaEmpleadoDto.getDias());
			entity.setEstado(licenciaEmpleadoDto.getEstado());
			entity.setTipoLicencia(tipoLicencia);
			entity.setDiaEntero(licenciaEmpleadoDto.getDiaEntero());
			entity.setHoraInicio(licenciaEmpleadoDto.getHoraInicio());
			entity.setHoraFin(licenciaEmpleadoDto.getHoraFin());
			
			if (entity.getDocumentosEmpleado() != null) {
	            final List<DocumentoEmpleado> documentosActuales = entity.getDocumentosEmpleado();
	            for (int i = 0; i < documentosActuales.size(); i++) {
	                if (documentoEmpleadoToDelete(licenciaEmpleadoDto.getDocumentos(),  documentosActuales.get(i).getIdDocumentoEmpleado()))
	                    entity.removeDocumentoEmpleado(documentosActuales.get(i));
	            }
			} else 
				entity.setDocumentosEmpleado(new ArrayList<>());
		
		if(licenciaEmpleadoDto.getDocumentos() !=null){
			for (DocumentoEmpleadoDto documento : licenciaEmpleadoDto.getDocumentos()) {
				DocumentoEmpleado documentoEntity = new DocumentoEmpleado();
				if(documento.getIdDocumentoEmpleado()!=null && documento.getIdDocumentoEmpleado().intValue() > 0){
					documentoEntity = documentoEmpleadoJpaRepository.findOne(documento.getIdDocumentoEmpleado());
				}else
					documentoEntity = new DocumentoEmpleado();
				
				mapper.map(documento, documentoEntity);
				documentoEntity.setLicencia(entity);
				documentoEntity.setEmpleado(empleado);
				
				/*File file=new File("d:/Oscar/pendientes_mejoras.txt");
				byte[] contenido=new byte[1024];
				FileUtils.writeToFile(file, contenido);
				documentoEntity.setContenidoArchivo(contenido);*/
				
				entity.getDocumentosEmpleado().add(documentoEntity);
			}
		}
			
			licenciaEmpleadoJpaRepository.save(entity);
			
			notificacion.setCodigo(1L);
			notificacion.setMensaje("Se actualizo correctamente");
		} catch(Exception e) {
			
			notificacion.setCodigo(0L);
			notificacion.setMensaje("No es posible actualizar, "+e.getMessage());
		}
		
		log.info("FIN ACTUALIZAR LICENCIA");
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
	
	

	@Override
	public Long eliminarLicenciaEmpleado(Long idLicencia) {
		if (licenciaEmpleadoJpaRepository.findOne(idLicencia) == null) {
			throw new GenericRestException("ERROR", "The record was changed by another user. Please re-enter the screen.");
		}
		try {
			licenciaEmpleadoJpaRepository.delete(idLicencia);
			licenciaEmpleadoJpaRepository.flush();
		} catch (Exception e) {
			throw new GenericRestException("ERR_001", "Restriction is Being used, Can't be deleted");
		}
		
		return idLicencia;
	}

	@Override
	public List<LicenciaEmpleadoDto> obtenerLicencias(BusquedaLicenciaEmpleadoDto busquedaLicenciaEmpleadoDto) {
		
		return licenciaEmpleadoRepository.obtenerLicencias(busquedaLicenciaEmpleadoDto);
	}

	@Override
	public LicenciaEmpleadoDto obtenerLicencia(Long idLicencia) {
		LicenciaEmpleadoDto dto=new LicenciaEmpleadoDto();
		dto.setDocumentos(new ArrayList<>());
		Licencia licencia=licenciaEmpleadoJpaRepository.findOne(idLicencia);
		dto.setIdLicencia(licencia.getIdLicencia());
		dto.setIdEmpleado(licencia.getEmpleado().getIdEmpleado());
		dto.setIdTipoLicencia(licencia.getTipoLicencia().getIdTipoLicencia());
		dto.setFechaInicio(licencia.getFechaInicio());
		dto.setFechaFin(licencia.getFechaFin());
		dto.setDias(licencia.getDias());
		dto.setEstado(licencia.getEstado());
		dto.setComentario(licencia.getComentario());
		dto.setDiaEntero(licencia.getDiaEntero());
		dto.setHoraInicio(licencia.getHoraInicio());
		dto.setHoraFin(licencia.getHoraFin());
		for(DocumentoEmpleado documentoEmpleado:licencia.getDocumentosEmpleado()){
			DocumentoEmpleadoDto docEmpdto=new DocumentoEmpleadoDto();
			mapper.map(documentoEmpleado, docEmpdto);
			dto.getDocumentos().add(docEmpdto);
		}
		
		return dto;
	}

	@Override
	public NotificacionDto aprobarLicenciaEmpleado(LicenciaEmpleadoDto licenciaEmpleadoDto) {
		licenciaEmpleadoDto.setEstado("A");
		NotificacionDto notificacionDto=actualizarLicenciaEmpleado(licenciaEmpleadoDto);
		try{
			Empleado empleado=empleadoJpaRepository.findOne(licenciaEmpleadoDto.getIdEmpleado());
			EmpleadoDto empleadoDto=new EmpleadoDto();
			empleadoDto.setIdEmpleado(empleado.getIdEmpleado());
			PeriodoEmpleadoDto periodoEmpleadoDto=	periodoEmpleadoRepository.obtenerPeriodoEmpleadoActual(empleadoDto);
			
			PeriodoEmpleadoTipoLicenciaDto periodoEmpleadoTipoLicenciaDto=new PeriodoEmpleadoTipoLicenciaDto();
			periodoEmpleadoTipoLicenciaDto.setIdPeriodoEmpleado(periodoEmpleadoDto.getIdPeriodoEmpleado());
			periodoEmpleadoTipoLicenciaDto.setIdTipoLicencia(licenciaEmpleadoDto.getIdTipoLicencia());
			List<PeriodoEmpleadoTipoLicenciaDto> periodoEmpleadoTipoLicenciaResultDto = periodoEmpleadoTipoLicenciaRepository.obtenerDiasPorPeriodoEmpleadoTipoLicencia(periodoEmpleadoTipoLicenciaDto);
			if(periodoEmpleadoTipoLicenciaResultDto!=null && periodoEmpleadoTipoLicenciaResultDto.size()>0 &&
					periodoEmpleadoTipoLicenciaResultDto.get(0).getDias().intValue()>0){
				PeriodoEmpleadoTipoLicencia entity=periodoEmpleadoTipoLicenciaJpaRepository.findOne(periodoEmpleadoTipoLicenciaResultDto.get(0).getIdPeriodoEmpleadoTipoLicencia());
				entity.setDiasLicencia(entity.getDiasLicencia()+licenciaEmpleadoDto.getDias());
				periodoEmpleadoTipoLicenciaJpaRepository.save(entity);
			} else{
				PeriodoEmpleadoTipoLicencia entity=new PeriodoEmpleadoTipoLicencia();
				PeriodoEmpleado periodoEmpleado=periodoEmpleadoJpaRepository.findOne(periodoEmpleadoDto.getIdPeriodoEmpleado());
				TipoLicencia tipoLicencia=tipoLicenciaJpaRepository.findOne(licenciaEmpleadoDto.getIdTipoLicencia());
				entity.setDiasLicencia(licenciaEmpleadoDto.getDias());
				entity.setTipoLicencia(tipoLicencia);
				entity.setPeriodoEmpleado(periodoEmpleado);
				periodoEmpleadoTipoLicenciaJpaRepository.save(entity);
			}
			
			notificacionDto.setCodigo(1L);
			notificacionDto.setMensaje("Se aprobo correctamente");
		} catch (Exception e) {
			notificacionDto.setCodigo(0L);
			notificacionDto.setMensaje("No se pudo aprobar "+e.getMessage());
		}
		
		return notificacionDto;
	}

}
