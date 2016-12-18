package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "DocumentoEmpleado")
@NamedQuery(name = "DocumentoEmpleado.findAll", query = "SELECT d FROM DocumentoEmpleado d")
public class DocumentoEmpleado extends AuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long idDocumentoEmpleado;
	private String nombre;
	private byte[] contenidoArchivo;
	private String nombreArchivo;
	private String tipoArchivo;
	private String tipoDocumento;
	
	private Empleado empleado;
	private Licencia licencia;
	
	public DocumentoEmpleado() {}

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDocumentoEmpleado")
	public Long getIdDocumentoEmpleado() {
		return idDocumentoEmpleado;
	}

	public void setIdDocumentoEmpleado(Long idDocumentoEmpleado) {
		this.idDocumentoEmpleado = idDocumentoEmpleado;
	}

	 @Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	 @Column(name = "ContenidoArchivo")
	public byte[] getContenidoArchivo() {
		return this.contenidoArchivo;
	}

	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}

	@Column(name = "NombreArchivo")
	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	@Column(name = "TipoArchivo")
	public String getTipoArchivo() {
		return tipoArchivo;
	}

	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}

	@Column(name = "TipoDocumento")
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdLicencia")
	public Licencia getLicencia() {
		return licencia;
	}

	public void setLicencia(Licencia licencia) {
		this.licencia = licencia;
	}

}
