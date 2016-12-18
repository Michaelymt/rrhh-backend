package pe.com.tss.runakuna.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.domain.model.entities.CentroCosto;
import pe.com.tss.runakuna.dto.importxls.XlsMessageDto;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EmpleadoDto extends PageableFilter implements XlsMessageDto<EmpleadoMessageRsl> {

	private Long idEmpleado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tipoDocumento;
	private String tipoDocumentoString;
	private String numeroDocumento;
	private String genero;
	private String generoString;
	private String estadoCivil;
	private String estadoCivilString;
	private String grupoSangre;
	private String grupoSangreString;

	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer discapacitado;
	
	private String discapacitadoString;

	private String foto;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaNacimiento;
	
	private String paisNacimiento;
	private String paisNacimientoString;
	private String departamentoNacimiento;
	private String departamentoNacimientoString;
	private String provinciaNacimiento;
	private String provinciaNacimientoString;
	private String distritoNacimiento;
	private String codigo;
	private String emailInterno;
	private String telefonoInterno;
	private String anexoInterno;
	private String contratoTrabajo;
	private String contratoTrabajoString;
	private String convenio;
	private String regimenHorario;
	private String regimenHorarioString;
	private String regimenLaboral;
	private String regimenLaboralString;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer esPersonalDeConfianza;
	
	private String situacionTrabajador;
	private String tipoTrabajador;
	private String tipoTrabajadorString;
	private String telefonoCasa;
	private String telefonoCelular;
	private String telefonoAdicional;
	private String emailPersonal;
	private String tipoDomicilio;
	private String tipoDomicilioString;
	private String direccionDomicilio;
	private String paisDomicilio;
	private String paisDomicilioString;
	private String departamentoDomicilio;
	private String departamentoDomicilioString;
	private String provinciaDomicilio;
	private String provinciaDomicilioString;
	private String distritoDomicilio;
	private String nombreContactoEmergencia;
	private String emailContactoEmergencia;
	private String telefonoContactoEmergencia;
	private String relacionContactoEmergencia;
	private String relacionContactoEmergenciaString;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaIngreso;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaRenuncia;
	
	private String motivoRenuncia;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaCese;
	private String estado;
	private String centroCostoString;
	private String estadoString;
	
	private Long idEmpresa;
	private Long idCentroCosto;
	
	private List<DocumentoEmpleadoDto> documentos;
	
	private List<EducacionDto> educaciones;
	
	private List<ExperienciaLaboralDto> experienciasLaborales;
	
	private List<EquipoEntregadoDto> equiposEntregados;
	
	private List<DependienteDto> dependientes;

	private CentroCosto centroCosto;
	
	private String nombreProyecto;
	private String nombreDepartamento;
	private String nombreUnidadNegocio;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer tieneEps;
	
	private String entidadBancariaSueldo;
	private String afp;
	private String eps;
	private String cts;
	
	private String entidadBancariaSueldoString;
	private String afpString;
	private String epsString;
	private String ctsString;

	public EmpleadoDto(){
		this.statusProcessDtoList = new ArrayList<>();
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getEstadoCivil() {
		return estadoCivil;
	}
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	public String getGrupoSangre() {
		return grupoSangre;
	}
	public void setGrupoSangre(String grupoSangre) {
		this.grupoSangre = grupoSangre;
	}
	public Integer getDiscapacitado() {
		return discapacitado;
	}
	public void setDiscapacitado(Integer discapacitado) {
		this.discapacitado = discapacitado;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}

	public String getPaisNacimiento() {
		return paisNacimiento;
	}
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}
	public String getDepartamentoNacimiento() {
		return departamentoNacimiento;
	}
	public void setDepartamentoNacimiento(String departamentoNacimiento) {
		this.departamentoNacimiento = departamentoNacimiento;
	}
	public String getProvinciaNacimiento() {
		return provinciaNacimiento;
	}
	public void setProvinciaNacimiento(String provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}
	public String getDistritoNacimiento() {
		return distritoNacimiento;
	}
	public void setDistritoNacimiento(String distritoNacimiento) {
		this.distritoNacimiento = distritoNacimiento;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getEmailInterno() {
		return emailInterno;
	}
	public void setEmailInterno(String emailInterno) {
		this.emailInterno = emailInterno;
	}
	public String getTelefonoInterno() {
		return telefonoInterno;
	}
	public void setTelefonoInterno(String telefonoInterno) {
		this.telefonoInterno = telefonoInterno;
	}
	public String getAnexoInterno() {
		return anexoInterno;
	}
	public void setAnexoInterno(String anexoInterno) {
		this.anexoInterno = anexoInterno;
	}
	public String getContratoTrabajo() {
		return contratoTrabajo;
	}
	public void setContratoTrabajo(String contratoTrabajo) {
		this.contratoTrabajo = contratoTrabajo;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public String getRegimenHorario() {
		return regimenHorario;
	}
	public void setRegimenHorario(String regimenHorario) {
		this.regimenHorario = regimenHorario;
	}
	public String getRegimenLaboral() {
		return regimenLaboral;
	}
	public void setRegimenLaboral(String regimenLaboral) {
		this.regimenLaboral = regimenLaboral;
	}
	public Integer getEsPersonalDeConfianza() {
		return esPersonalDeConfianza;
	}
	public void setEsPersonalDeConfianza(Integer esPersonalDeConfianza) {
		this.esPersonalDeConfianza = esPersonalDeConfianza;
	}
	public String getSituacionTrabajador() {
		return situacionTrabajador;
	}
	public void setSituacionTrabajador(String situacionTrabajador) {
		this.situacionTrabajador = situacionTrabajador;
	}
	public String getTipoTrabajador() {
		return tipoTrabajador;
	}
	public void setTipoTrabajador(String tipoTrabajador) {
		this.tipoTrabajador = tipoTrabajador;
	}
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}
	public String getEmailPersonal() {
		return emailPersonal;
	}
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	public String getTipoDomicilio() {
		return tipoDomicilio;
	}
	public void setTipoDomicilio(String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}
	public String getPaisDomicilio() {
		return paisDomicilio;
	}
	public void setPaisDomicilio(String paisDomicilio) {
		this.paisDomicilio = paisDomicilio;
	}
	public String getDepartamentoDomicilio() {
		return departamentoDomicilio;
	}
	public void setDepartamentoDomicilio(String departamentoDomicilio) {
		this.departamentoDomicilio = departamentoDomicilio;
	}
	public String getProvinciaDomicilio() {
		return provinciaDomicilio;
	}
	public void setProvinciaDomicilio(String provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}
	public String getDistritoDomicilio() {
		return distritoDomicilio;
	}
	public void setDistritoDomicilio(String distritoDomicilio) {
		this.distritoDomicilio = distritoDomicilio;
	}
	public String getNombreContactoEmergencia() {
		return nombreContactoEmergencia;
	}
	public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
		this.nombreContactoEmergencia = nombreContactoEmergencia;
	}
	public String getEmailContactoEmergencia() {
		return emailContactoEmergencia;
	}
	public void setEmailContactoEmergencia(String emailContactoEmergencia) {
		this.emailContactoEmergencia = emailContactoEmergencia;
	}
	public String getTelefonoContactoEmergencia() {
		return telefonoContactoEmergencia;
	}
	public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
		this.telefonoContactoEmergencia = telefonoContactoEmergencia;
	}
	public String getRelacionContactoEmergencia() {
		return relacionContactoEmergencia;
	}
	public void setRelacionContactoEmergencia(String relacionContactoEmergencia) {
		this.relacionContactoEmergencia = relacionContactoEmergencia;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaRenuncia() {
		return fechaRenuncia;
	}
	public void setFechaRenuncia(Date fechaRenuncia) {
		this.fechaRenuncia = fechaRenuncia;
	}
	public String getMotivoRenuncia() {
		return motivoRenuncia;
	}
	public void setMotivoRenuncia(String motivoRenuncia) {
		this.motivoRenuncia = motivoRenuncia;
	}
	public Date getFechaCese() {
		return fechaCese;
	}
	public void setFechaCese(Date fechaCese) {
		this.fechaCese = fechaCese;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public List<DocumentoEmpleadoDto> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentoEmpleadoDto> documentos) {
		this.documentos = documentos;
	}
	public List<EducacionDto> getEducaciones() {
		return educaciones;
	}
	public void setEducaciones(List<EducacionDto> educaciones) {
		this.educaciones = educaciones;
	}
	public List<ExperienciaLaboralDto> getExperienciasLaborales() {
		return experienciasLaborales;
	}
	public void setExperienciasLaborales(List<ExperienciaLaboralDto> experienciasLaborales) {
		this.experienciasLaborales = experienciasLaborales;
	}
	public List<EquipoEntregadoDto> getEquiposEntregados() {
		return equiposEntregados;
	}
	public void setEquiposEntregados(List<EquipoEntregadoDto> equiposEntregados) {
		this.equiposEntregados = equiposEntregados;
	}
	
	public List<DependienteDto> getDependientes() {
		return dependientes;
	}


	public void setDependientes(List<DependienteDto> dependientes) {
		this.dependientes = dependientes;
	}


	public Long getIdCentroCosto() {
		return idCentroCosto;
	}
	public void setIdCentroCosto(Long idCentroCosto) {
		this.idCentroCosto = idCentroCosto;
	}


	public String getTipoDocumentoString() {
		return tipoDocumentoString;
	}

	public void setTipoDocumentoString(String tipoDocumentoString) {
		this.tipoDocumentoString = tipoDocumentoString;
	}

	public String getCentroCostoString() {
		return centroCostoString;
	}

	public void setCentroCostoString(String centroCostoString) {
		this.centroCostoString = centroCostoString;
	}

	public String getEstadoString() {
		return estadoString;
	}

	public void setEstadoString(String estadoString) {
		this.estadoString = estadoString;
	}

	public String getGeneroString() {
		return generoString;
	}

	public void setGeneroString(String generoString) {
		this.generoString = generoString;
	}

	public String getEstadoCivilString() {
		return estadoCivilString;
	}

	public void setEstadoCivilString(String estadoCivilString) {
		this.estadoCivilString = estadoCivilString;
	}

	public String getGrupoSangreString() {
		return grupoSangreString;
	}

	public void setGrupoSangreString(String grupoSangreString) {
		this.grupoSangreString = grupoSangreString;
	}

	public String getDiscapacitadoString() {
		return discapacitadoString;
	}

	public void setDiscapacitadoString(String discapacitadoString) {
		this.discapacitadoString = discapacitadoString;
	}

	public String getPaisNacimientoString() {
		return paisNacimientoString;
	}

	public void setPaisNacimientoString(String paisNacimientoString) {
		this.paisNacimientoString = paisNacimientoString;
	}

	public String getDepartamentoNacimientoString() {
		return departamentoNacimientoString;
	}

	public void setDepartamentoNacimientoString(String departamentoNacimientoString) {
		this.departamentoNacimientoString = departamentoNacimientoString;
	}

	public String getProvinciaNacimientoString() {
		return provinciaNacimientoString;
	}

	public void setProvinciaNacimientoString(String provinciaNacimientoString) {
		this.provinciaNacimientoString = provinciaNacimientoString;
	}

	public String getContratoTrabajoString() {
		return contratoTrabajoString;
	}

	public void setContratoTrabajoString(String contratoTrabajoString) {
		this.contratoTrabajoString = contratoTrabajoString;
	}

	public String getRegimenHorarioString() {
		return regimenHorarioString;
	}

	public void setRegimenHorarioString(String regimenHorarioString) {
		this.regimenHorarioString = regimenHorarioString;
	}

	public String getRegimenLaboralString() {
		return regimenLaboralString;
	}

	public void setRegimenLaboralString(String regimenLaboralString) {
		this.regimenLaboralString = regimenLaboralString;
	}

	public String getTipoTrabajadorString() {
		return tipoTrabajadorString;
	}

	public void setTipoTrabajadorString(String tipoTrabajadorString) {
		this.tipoTrabajadorString = tipoTrabajadorString;
	}

	public String getTipoDomicilioString() {
		return tipoDomicilioString;
	}

	public void setTipoDomicilioString(String tipoDomicilioString) {
		this.tipoDomicilioString = tipoDomicilioString;
	}

	public String getPaisDomicilioString() {
		return paisDomicilioString;
	}

	public void setPaisDomicilioString(String paisDomicilioString) {
		this.paisDomicilioString = paisDomicilioString;
	}

	public String getDepartamentoDomicilioString() {
		return departamentoDomicilioString;
	}

	public void setDepartamentoDomicilioString(String departamentoDomicilioString) {
		this.departamentoDomicilioString = departamentoDomicilioString;
	}

	public String getProvinciaDomicilioString() {
		return provinciaDomicilioString;
	}

	public void setProvinciaDomicilioString(String provinciaDomicilioString) {
		this.provinciaDomicilioString = provinciaDomicilioString;
	}

	public String getRelacionContactoEmergenciaString() {
		return relacionContactoEmergenciaString;
	}

	public void setRelacionContactoEmergenciaString(String relacionContactoEmergenciaString) {
		this.relacionContactoEmergenciaString = relacionContactoEmergenciaString;
	}

	public CentroCosto getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(CentroCosto centroCosto) {
		this.centroCosto = centroCosto;
	}
	
	public String getNombreProyecto() {
		return nombreProyecto;
	}

	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}

	public String getNombreDepartamento() {
		return nombreDepartamento;
	}

	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}

	public String getNombreUnidadNegocio() {
		return nombreUnidadNegocio;
	}

	public void setNombreUnidadNegocio(String nombreUnidadNegocio) {
		this.nombreUnidadNegocio = nombreUnidadNegocio;
	}
	
	public Integer getTieneEps() {
		return tieneEps;
	}


	public void setTieneEps(Integer tieneEps) {
		this.tieneEps = tieneEps;
	}


	public String getEntidadBancariaSueldo() {
		return entidadBancariaSueldo;
	}


	public void setEntidadBancariaSueldo(String entidadBancariaSueldo) {
		this.entidadBancariaSueldo = entidadBancariaSueldo;
	}


	public String getAfp() {
		return afp;
	}


	public void setAfp(String afp) {
		this.afp = afp;
	}


	public String getEps() {
		return eps;
	}


	public void setEps(String eps) {
		this.eps = eps;
	}


	public String getCts() {
		return cts;
	}


	public void setCts(String cts) {
		this.cts = cts;
	}

	public String getEntidadBancariaSueldoString() {
		return entidadBancariaSueldoString;
	}


	public void setEntidadBancariaSueldoString(String entidadBancariaSueldoString) {
		this.entidadBancariaSueldoString = entidadBancariaSueldoString;
	}


	public String getAfpString() {
		return afpString;
	}


	public void setAfpString(String afpString) {
		this.afpString = afpString;
	}


	public String getEpsString() {
		return epsString;
	}


	public void setEpsString(String epsString) {
		this.epsString = epsString;
	}


	public String getCtsString() {
		return ctsString;
	}


	public void setCtsString(String ctsString) {
		this.ctsString = ctsString;
	}



	private List<StatusProcessDto> statusProcessDtoList ;

	public List<StatusProcessDto> getStatusProcessDtoList() {
		return statusProcessDtoList;
	}

	public void addErrorStatus(String s) {

		StatusProcessDto statusProcessDto = new StatusProcessDto();
		statusProcessDto.setStatus("Error");
		statusProcessDto.setMessage(s);
		statusProcessDtoList.add(statusProcessDto);
	}

	private int empleadoOrden;
	List<EmpleadoMessageRsl> messageList = new ArrayList<>();

	@Override
	public Object getDtoReferenceId() {
		return idEmpleado;
	}

	@Override
	public void addError(String value) {
		String idEmpleado = (getIdEmpleado()==null)?null:getIdEmpleado().toString();
		if (idEmpleado==null) idEmpleado="";
		messageList.add(new EmpleadoMessageRsl(idEmpleado, value));
	}

	@Override
	public List<EmpleadoMessageRsl> getErrorList() {
		return messageList;
	}

	public int getEmpleadoOrden() {
		return empleadoOrden;
	}

	public void setEmpleadoOrden(int empleadoOrden) {
		this.empleadoOrden = empleadoOrden;
	}

	public boolean hasError() {
		return statusProcessDtoList.size()>0;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		EmpleadoDto that = (EmpleadoDto) o;

		return codigo.equals(that.codigo);

	}

	@Override
	public int hashCode() {
		return codigo.hashCode();
	}
}