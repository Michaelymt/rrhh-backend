package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Empleado")
@NamedQuery(name = "Empleado.findAll", query = "SELECT e FROM Empleado e")
public class Empleado extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idEmpleado;
	private String nombre;
	private String apellidoPaterno;
	private String apellidoMaterno;
	private String tipoDocumento;
	private String numeroDocumento;
	private String genero;
	private String estadoCivil;
	private String grupoSangre;
	private Integer discapacitado;
	//private String foto;
	private Date fechaNacimiento;
	private String paisNacimiento;
	private String departamentoNacimiento;
	private String provinciaNacimiento;
	private String distritoNacimiento;
	private String codigo;
	private String emailInterno;
	private String telefonoInterno;
	private String anexoInterno;
	private String contratoTrabajo;
	//private String convenio;
	private String regimenHorario;
	private String regimenLaboral;
	private Integer esPersonalDeConfianza;
	//private String situacionTrabajador;
	private String tipoTrabajador;
	private String telefonoCasa;
	private String telefonoCelular;
	private String telefonoAdicional;
	private String emailPersonal;
	private String tipoDomicilio;
	private String direccionDomicilio;
	private String paisDomicilio;
	private String departamentoDomicilio;
	private String provinciaDomicilio;
	private String distritoDomicilio;
	private String nombreContactoEmergencia;
	private String emailContactoEmergencia;
	private String telefonoContactoEmergencia;
	private String relacionContactoEmergencia;
	private Date fechaIngreso;
	private Date fechaRenuncia;
	private String motivoRenuncia;
	private Date fechaCese;
	private String estado;
	
	private Empresa empresa;
	private CentroCosto centroCosto;
	private List<DocumentoEmpleado> documentosEmpleado;
	private List<Educacion> educacionesEmpleado;
	private List<ExperienciaLaboral> experienciasLaboralesEmpleado;
	private List<EquipoEntregado> equiposEntregadoEmpleado;
	private List<Dependiente> dependientesEmpleado;
	private List<Licencia> licenciasEmpleado;
	
	private Integer tieneEps;
	
	private String entidadBancariaSueldo;
	private String afp;
	private String eps;
	private String cts;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpleado")
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column(name = "ApellidoPaterno")
	public String getApellidoPaterno() {
		return apellidoPaterno;
	}
	
	public void setApellidoPaterno(String apellidoPaterno) {
		this.apellidoPaterno = apellidoPaterno;
	}
	
	@Column(name = "ApellidoMaterno")
	public String getApellidoMaterno() {
		return apellidoMaterno;
	}
	
	public void setApellidoMaterno(String apellidoMaterno) {
		this.apellidoMaterno = apellidoMaterno;
	}
	
	@Column(name = "TipoDocumento")
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	@Column(name = "NumeroDocumento")
	public String getNumeroDocumento() {
		return numeroDocumento;
	}
	
	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	
	@Column(name = "Genero")
	public String getGenero() {
		return genero;
	}
	
	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Column(name = "EstadoCivil")
	public String getEstadoCivil() {
		return estadoCivil;
	}
	
	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}
	
	@Column(name = "GrupoSangre")
	public String getGrupoSangre() {
		return grupoSangre;
	}
	
	public void setGrupoSangre(String grupoSangre) {
		this.grupoSangre = grupoSangre;
	}
	
	@Column(name = "Discapacitado")
	public Integer getDiscapacitado() {
		return discapacitado;
	}
	
	public void setDiscapacitado(Integer discapacitado) {
		this.discapacitado = discapacitado;
	}
	
	/*@Column(name = "Foto")
	public String getFoto() {
		return foto;
	}
	
	public void setFoto(String foto) {
		this.foto = foto;
	}*/
	
	@Column(name = "FechaNacimiento")
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	@Column(name = "PaisNacimiento")
	public String getPaisNacimiento() {
		return paisNacimiento;
	}
	
	public void setPaisNacimiento(String paisNacimiento) {
		this.paisNacimiento = paisNacimiento;
	}
	
	@Column(name = "DepartamentoNacimiento")
	public String getDepartamentoNacimiento() {
		return departamentoNacimiento;
	}
	
	public void setDepartamentoNacimiento(String departamentoNacimiento) {
		this.departamentoNacimiento = departamentoNacimiento;
	}
	
	@Column(name = "ProvinciaNacimiento")
	public String getProvinciaNacimiento() {
		return provinciaNacimiento;
	}
	
	public void setProvinciaNacimiento(String provinciaNacimiento) {
		this.provinciaNacimiento = provinciaNacimiento;
	}
	
	@Column(name = "DistritoNacimiento")
	public String getDistritoNacimiento() {
		return distritoNacimiento;
	}
	
	public void setDistritoNacimiento(String distritoNacimiento) {
		this.distritoNacimiento = distritoNacimiento;
	}
	
	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}
	
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	@Column(name = "EmailInterno")
	public String getEmailInterno() {
		return emailInterno;
	}
	
	public void setEmailInterno(String emailInterno) {
		this.emailInterno = emailInterno;
	}
	
	@Column(name = "TelefonoInterno")
	public String getTelefonoInterno() {
		return telefonoInterno;
	}
	
	public void setTelefonoInterno(String telefonoInterno) {
		this.telefonoInterno = telefonoInterno;
	}
	
	@Column(name = "AnexoInterno")
	public String getAnexoInterno() {
		return anexoInterno;
	}
	
	public void setAnexoInterno(String anexoInterno) {
		this.anexoInterno = anexoInterno;
	}
	
	@Column(name = "ContratoTrabajo")
	public String getContratoTrabajo() {
		return contratoTrabajo;
	}
	
	public void setContratoTrabajo(String contratoTrabajo) {
		this.contratoTrabajo = contratoTrabajo;
	}
	
	/*@Column(name = "Convenio")
	public String getConvenio() {
		return convenio;
	}
	
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}*/
	
	@Column(name = "RegimenHorario")
	public String getRegimenHorario() {
		return regimenHorario;
	}
	
	public void setRegimenHorario(String regimenHorario) {
		this.regimenHorario = regimenHorario;
	}
	
	@Column(name = "RegimenLaboral")
	public String getRegimenLaboral() {
		return regimenLaboral;
	}
	
	public void setRegimenLaboral(String regimenLaboral) {
		this.regimenLaboral = regimenLaboral;
	}
	
	@Column(name = "EsPersonalDeConfianza")
	public Integer getEsPersonalDeConfianza() {
		return esPersonalDeConfianza;
	}
	
	public void setEsPersonalDeConfianza(Integer esPersonalDeConfianza) {
		this.esPersonalDeConfianza = esPersonalDeConfianza;
	}
	
	/*@Column(name = "SituacionTrabajador")
	public String getSituacionTrabajador() {
		return situacionTrabajador;
	}
	
	public void setSituacionTrabajador(String situacionTrabajador) {
		this.situacionTrabajador = situacionTrabajador;
	}*/
	
	@Column(name = "TipoTrabajador")
	public String getTipoTrabajador() {
		return tipoTrabajador;
	}
	
	public void setTipoTrabajador(String tipoTrabajador) {
		this.tipoTrabajador = tipoTrabajador;
	}
	
	@Column(name = "TelefonoCasa")
	public String getTelefonoCasa() {
		return telefonoCasa;
	}
	
	public void setTelefonoCasa(String telefonoCasa) {
		this.telefonoCasa = telefonoCasa;
	}
	
	@Column(name = "TelefonoCelular")
	public String getTelefonoCelular() {
		return telefonoCelular;
	}
	
	public void setTelefonoCelular(String telefonoCelular) {
		this.telefonoCelular = telefonoCelular;
	}
	
	@Column(name = "TelefonoAdicional")
	public String getTelefonoAdicional() {
		return telefonoAdicional;
	}
	
	public void setTelefonoAdicional(String telefonoAdicional) {
		this.telefonoAdicional = telefonoAdicional;
	}
	
	@Column(name = "EmailPersonal")
	public String getEmailPersonal() {
		return emailPersonal;
	}
	
	public void setEmailPersonal(String emailPersonal) {
		this.emailPersonal = emailPersonal;
	}
	
	@Column(name = "TipoDomicilio")
	public String getTipoDomicilio() {
		return tipoDomicilio;
	}
	
	public void setTipoDomicilio(String tipoDomicilio) {
		this.tipoDomicilio = tipoDomicilio;
	}
	
	@Column(name = "DireccionDomicilio")
	public String getDireccionDomicilio() {
		return direccionDomicilio;
	}
	
	public void setDireccionDomicilio(String direccionDomicilio) {
		this.direccionDomicilio = direccionDomicilio;
	}
	
	@Column(name = "PaisDomicilio")
	public String getPaisDomicilio() {
		return paisDomicilio;
	}
	
	public void setPaisDomicilio(String paisDomicilio) {
		this.paisDomicilio = paisDomicilio;
	}
	
	@Column(name = "DepartamentoDomicilio")
	public String getDepartamentoDomicilio() {
		return departamentoDomicilio;
	}
	
	public void setDepartamentoDomicilio(String departamentoDomicilio) {
		this.departamentoDomicilio = departamentoDomicilio;
	}
	
	@Column(name = "ProvinciaDomicilio")
	public String getProvinciaDomicilio() {
		return provinciaDomicilio;
	}
	
	public void setProvinciaDomicilio(String provinciaDomicilio) {
		this.provinciaDomicilio = provinciaDomicilio;
	}
	
	@Column(name = "DistritoDomicilio")
	public String getDistritoDomicilio() {
		return distritoDomicilio;
	}
	
	public void setDistritoDomicilio(String distritoDomicilio) {
		this.distritoDomicilio = distritoDomicilio;
	}
	
	@Column(name = "NombreContactoEmergencia")
	public String getNombreContactoEmergencia() {
		return nombreContactoEmergencia;
	}
	
	public void setNombreContactoEmergencia(String nombreContactoEmergencia) {
		this.nombreContactoEmergencia = nombreContactoEmergencia;
	}
	
	@Column(name = "EmailContactoEmergencia")
	public String getEmailContactoEmergencia() {
		return emailContactoEmergencia;
	}
	
	public void setEmailContactoEmergencia(String emailContactoEmergencia) {
		this.emailContactoEmergencia = emailContactoEmergencia;
	}
	
	@Column(name = "TelefonoContactoEmergencia")
	public String getTelefonoContactoEmergencia() {
		return telefonoContactoEmergencia;
	}
	
	public void setTelefonoContactoEmergencia(String telefonoContactoEmergencia) {
		this.telefonoContactoEmergencia = telefonoContactoEmergencia;
	}
	
	@Column(name = "RelacionContactoEmergencia")
	public String getRelacionContactoEmergencia() {
		return relacionContactoEmergencia;
	}
	
	public void setRelacionContactoEmergencia(String relacionContactoEmergencia) {
		this.relacionContactoEmergencia = relacionContactoEmergencia;
	}
	
	@Column(name = "FechaIngreso")
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	
	@Column(name = "FechaRenuncia")
	public Date getFechaRenuncia() {
		return fechaRenuncia;
	}
	
	public void setFechaRenuncia(Date fechaRenuncia) {
		this.fechaRenuncia = fechaRenuncia;
	}
	
	@Column(name = "MotivoRenuncia")
	public String getMotivoRenuncia() {
		return motivoRenuncia;
	}
	
	public void setMotivoRenuncia(String motivoRenuncia) {
		this.motivoRenuncia = motivoRenuncia;
	}
	
	@Column(name = "FechaCese")
	public Date getFechaCese() {
		return fechaCese;
	}
	
	public void setFechaCese(Date fechaCese) {
		this.fechaCese = fechaCese;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne()
    @JoinColumn(name = "IdCentroCosto")
	public CentroCosto getCentroCosto() {
		return centroCosto;
	}

	public void setCentroCosto(CentroCosto centroCosto) {
		this.centroCosto = centroCosto;
	}

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<DocumentoEmpleado> getDocumentosEmpleado() {
		return documentosEmpleado;
	}

	public void setDocumentosEmpleado(List<DocumentoEmpleado> documentosEmpleado) {
		this.documentosEmpleado = documentosEmpleado;
	}
	
	public DocumentoEmpleado addDocumentoEmpleado(DocumentoEmpleado documentoEmpleado) {
		getDocumentosEmpleado().add(documentoEmpleado);
		documentoEmpleado.setEmpleado(this);

        return documentoEmpleado;
    }

    public DocumentoEmpleado removeDocumentoEmpleado(DocumentoEmpleado documentoEmpleado) {
    	getDocumentosEmpleado().remove(documentoEmpleado);
    	documentoEmpleado.setEmpleado(null);

        return documentoEmpleado;
    }

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<Educacion> getEducacionesEmpleado() {
		return educacionesEmpleado;
	}

	public void setEducacionesEmpleado(List<Educacion> educacionesEmpleado) {
		this.educacionesEmpleado = educacionesEmpleado;
	}
	
	public Educacion addEducacion(Educacion educacion) {
		getEducacionesEmpleado().add(educacion);
		educacion.setEmpleado(this);

        return educacion;
    }

    public Educacion removeEducacion(Educacion educacion) {
    	getEducacionesEmpleado().remove(educacion);
    	educacion.setEmpleado(null);

        return educacion;
    }

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<ExperienciaLaboral> getExperienciasLaboralesEmpleado() {
		return experienciasLaboralesEmpleado;
	}

	public void setExperienciasLaboralesEmpleado(List<ExperienciaLaboral> experienciasLaboralesEmpleado) {
		this.experienciasLaboralesEmpleado = experienciasLaboralesEmpleado;
	}
	
	public ExperienciaLaboral addExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
		getExperienciasLaboralesEmpleado().add(experienciaLaboral);
		experienciaLaboral.setEmpleado(this);

        return experienciaLaboral;
    }

    public ExperienciaLaboral removeExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
    	getExperienciasLaboralesEmpleado().remove(experienciaLaboral);
    	experienciaLaboral.setEmpleado(null);

        return experienciaLaboral;
    }
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<EquipoEntregado> getEquiposEntregadoEmpleado() {
		return equiposEntregadoEmpleado;
	}

	public void setEquiposEntregadoEmpleado(List<EquipoEntregado> equiposEntregadoEmpleado) {
		this.equiposEntregadoEmpleado = equiposEntregadoEmpleado;
	}
	
	public EquipoEntregado addEquipoEntregado(EquipoEntregado equipoEntregado) {
		getEquiposEntregadoEmpleado().add(equipoEntregado);
		equipoEntregado.setEmpleado(this);

        return equipoEntregado;
    }

    public EquipoEntregado removeEquipoEntregado(EquipoEntregado equipoEntregado) {
    	getEquiposEntregadoEmpleado().remove(equipoEntregado);
    	equipoEntregado.setEmpleado(null);

        return equipoEntregado;
    }

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<Dependiente> getDependientesEmpleado() {
		return dependientesEmpleado;
	}

	public void setDependientesEmpleado(List<Dependiente> dependientesEmpleado) {
		this.dependientesEmpleado = dependientesEmpleado;
	}
	
	public Dependiente addDependiente(Dependiente dependiente) {
		getDependientesEmpleado().add(dependiente);
		dependiente.setEmpleado(this);

        return dependiente;
    }

    public Dependiente removeDependiente(Dependiente dependiente) {
    	getDependientesEmpleado().remove(dependiente);
    	dependiente.setEmpleado(null);

        return dependiente;
    }

	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "empleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<Licencia> getLicenciasEmpleado() {
		return licenciasEmpleado;
	}

	public void setLicenciasEmpleado(List<Licencia> licenciasEmpleado) {
		this.licenciasEmpleado = licenciasEmpleado;
	}

	@Column(name = "TieneEPS")
	public Integer getTieneEps() {
		return tieneEps;
	}

	public void setTieneEps(Integer tieneEps) {
		this.tieneEps = tieneEps;
	}

	@Column(name = "EntidadBancariaSueldo")
	public String getEntidadBancariaSueldo() {
		return entidadBancariaSueldo;
	}

	public void setEntidadBancariaSueldo(String entidadBancariaSueldo) {
		this.entidadBancariaSueldo = entidadBancariaSueldo;
	}

	@Column(name = "AFP")
	public String getAfp() {
		return afp;
	}

	public void setAfp(String afp) {
		this.afp = afp;
	}

	@Column(name = "EPS")
	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	@Column(name = "CTS")
	public String getCts() {
		return cts;
	}

	public void setCts(String cts) {
		this.cts = cts;
	}
		
}