package pe.com.tss.runakuna.rest;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.HorarioDiaDto;
import pe.com.tss.runakuna.dto.TablaGeneralDto;
import pe.com.tss.runakuna.service.TablaGeneralService;

@RestController
@RequestMapping(value="/tablaGeneral")
@CrossOrigin()
public class TablaGeneralController {

	@Autowired
	TablaGeneralService tablaGeneralService;
	
	private List<TablaGeneralDto> resultList = new ArrayList<>();
	private List<HorarioDiaDto> resultListDia = new ArrayList<>();

	@RequestMapping(value = "/obtenerTipoDocumento", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerTipoDocumento() {
		
		resultList = tablaGeneralService.obtenerTiposDocumento();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerEstadoCivil", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerEstadoCivil() {
		
		resultList = tablaGeneralService.obtenerEstadosCivil();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerGrupoSanguineo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerGrupoSanguineo() {
		
		resultList = tablaGeneralService.obtenerGruposSanguineo();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerGenero", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerGenero() {
		
		resultList = tablaGeneralService.obtenerGeneros();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerTipoDomicilio", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerTipoDomicilio() {
		
		resultList = tablaGeneralService.obtenerTiposDomicilio();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerRegimenHorario", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerRegimenHorario() {
		
		resultList = tablaGeneralService.obtenerRegimenHorario();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerTipoHorario", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerTipoHorario() {
		
		resultList = tablaGeneralService.obtenerTipoHorario();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerContratoTrabajo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerContratoTrabajo() {
		
		resultList = tablaGeneralService.obtenerContratoTrabajo();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerTipoTrabajo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerTipoTrabajo() {
		
		resultList = tablaGeneralService.obtenerTipoTrabajo();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerRegimenLaboral", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerRegimenLaboral() {
		
		resultList = tablaGeneralService.obtenerRegimenLaboral();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerNivelEducacion", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerNivelEducacion() {
		
		resultList = tablaGeneralService.obtenerNivelEducacion();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerDias", method = RequestMethod.GET)
	public @ResponseBody List<HorarioDiaDto> obtenerDias() {
		
		resultListDia = tablaGeneralService.obtenerDias();
		return resultListDia;
	}
	
	@RequestMapping(value = "/obtenerTipoEquipo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerTipoEquipo() {
		
		resultList = tablaGeneralService.obtenerTipoEquipo();
		return resultList;
	}

	@RequestMapping(value = "/obtenerEmpleadoEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerEmpleadoEstado() {

		resultList = tablaGeneralService.obtenerEmpleadoEstado();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerEstadoTipoEquipo", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerEstadoTipoEquipo() {
		
		resultList = tablaGeneralService.obtenerEstadoTipoEquipo();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerRelacionContactoEmergencia", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerRelacionContactoEmergencia() {
		
		resultList = tablaGeneralService.obtenerRelacionContactoEmergencia();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerMotivosPermiso", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerMotivosPermiso() {
		
		resultList = tablaGeneralService.obtenerMotivosPermiso();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerPermisoEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerPermisoEstado() {

		resultList = tablaGeneralService.obtenerPermisoEstado();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerVacacionesEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerVacacionesEstados() {

		resultList = tablaGeneralService.obtenerVacacionesEstados();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerMotivoRenuncia", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerMotivoRenuncia() {

		resultList = tablaGeneralService.obtenerMotivoRenuncia();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerRelacionDependiente", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerRelacionDependiente() {
		
		resultList = tablaGeneralService.obtenerRelacionDependiente();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerEstados", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerEstados() {
		
		resultList = tablaGeneralService.obtenerEstados();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerEntidadFinanciera", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerEntidadFinanciera() {

		resultList = tablaGeneralService.obtenerEntidadFinanciera();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerAFP", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerAFP() {
		
		resultList = tablaGeneralService.obtenerAFP();
		return resultList;
	}
	
	@RequestMapping(value = "/obtenerEPS", method = RequestMethod.GET)
	public @ResponseBody List<TablaGeneralDto> obtenerEPS() {
		
		resultList = tablaGeneralService.obtenerEPS();
		return resultList;
	}
	
}
