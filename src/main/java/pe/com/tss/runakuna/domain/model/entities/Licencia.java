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
@Table(name = "Licencia")
@NamedQuery(name = "Licencia.findAll", query = "SELECT l FROM Licencia l")
public class Licencia extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idLicencia;
	
	private String comentario;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer dias;
	
	private Empleado empleado;
	private String estado;
	private TipoLicencia tipoLicencia;
	private List<DocumentoEmpleado> documentosEmpleado;
	private Integer diaEntero;
	private String horaInicio;
	private String horaFin;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdLicencia")
	public Long getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(Long idLicencia) {
		this.idLicencia = idLicencia;
	}

	

	@Column(name = "Comentario")
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Column(name = "FechaInicio")
	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Column(name = "FechaFin")
	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "Dias")
	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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
    @JoinColumn(name = "IdTipoLicencia")
	public TipoLicencia getTipoLicencia() {
		return tipoLicencia;
	}

	public void setTipoLicencia(TipoLicencia tipoLicencia) {
		this.tipoLicencia = tipoLicencia;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "licencia")
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
	
	public DocumentoEmpleado removeDocumentoEmpleado(DocumentoEmpleado documentoEmpleado) {
    	getDocumentosEmpleado().remove(documentoEmpleado);
    	documentoEmpleado.setLicencia(null);

        return documentoEmpleado;
    }
	@Column(name = "DiaEntero")
	public Integer getDiaEntero() {
		return diaEntero;
	}

	public void setDiaEntero(Integer diaEntero) {
		this.diaEntero = diaEntero;
	}
	
	@Column(name = "HoraInicio")
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	
	@Column(name = "HoraFin")
	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
}
