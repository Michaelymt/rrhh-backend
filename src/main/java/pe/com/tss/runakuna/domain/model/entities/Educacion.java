package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "Educacion")
@NamedQuery(name = "Educacion.findAll", query = "SELECT e FROM Educacion e")
public class Educacion extends AuditingEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idEducacion;
	private String  nivelEducacion;
	private String institucion;
	private String titulo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	
	private Empleado empleado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEducacion")
	public Long getIdEducacion() {
		return idEducacion;
	}
	
	public void setIdEducacion(Long idEducacion) {
		this.idEducacion = idEducacion;
	}
	
	@Column(name = "NivelEducacion")
	public String getNivelEducacion() {
		return nivelEducacion;
	}
	
	public void setNivelEducacion(String nivelEducacion) {
		this.nivelEducacion = nivelEducacion;
	}
	
	@Column(name = "Institucion")
	public String getInstitucion() {
		return institucion;
	}
	
	public void setInstitucion(String institucion) {
		this.institucion = institucion;
	}
	
	@Column(name = "Titulo")
	public String getTitulo() {
		return titulo;
	}
	
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
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
	
	
	
}
