package pe.com.tss.runakuna.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import pe.com.tss.runakuna.domain.model.entities.TablaGeneral;
import pe.com.tss.runakuna.dto.HorarioDiaDto;
import pe.com.tss.runakuna.dto.TablaGeneralDto;

public interface TablaGeneralService {

	List<TablaGeneralDto> obtenerTiposDocumento();
	List<TablaGeneralDto> obtenerEstadosCivil();
	List<TablaGeneralDto> obtenerGruposSanguineo();
	List<TablaGeneralDto> obtenerGeneros();
	List<TablaGeneralDto> obtenerTiposDomicilio();
	List<TablaGeneralDto> obtenerRegimenHorario();
	List<TablaGeneralDto> obtenerTipoHorario();
	List<TablaGeneralDto> obtenerContratoTrabajo();
	List<TablaGeneralDto> obtenerTipoTrabajo();
	List<TablaGeneralDto> obtenerRegimenLaboral();
	List<TablaGeneralDto> obtenerNivelEducacion();
	List<HorarioDiaDto> obtenerDias();
	List<TablaGeneralDto> obtenerTipoEquipo();
	List<TablaGeneralDto> obtenerEmpleadoEstado();
	List<TablaGeneralDto> obtenerEstadoTipoEquipo();
	List<TablaGeneralDto> obtenerRelacionContactoEmergencia();
	List<TablaGeneralDto> obtenerMotivosPermiso();
	List<TablaGeneralDto> obtenerPermisoEstado();
	List<TablaGeneralDto> obtenerRelacionDependiente();
	List<TablaGeneralDto> obtenerVacacionesEstados();
	List<TablaGeneralDto> obtenerEstados();
	List<TablaGeneralDto> obtenerMotivoRenuncia();
	
	List<TablaGeneralDto> obtenerEntidadFinanciera();
	List<TablaGeneralDto> obtenerAFP() ;
	List<TablaGeneralDto> obtenerEPS();
	
}
