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

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "ExperienciaLaboral")
@NamedQuery(name = "ExperienciaLaboral.findAll", query = "SELECT e FROM ExperienciaLaboral e")
public class ExperienciaLaboral extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idExperienciaLaboral;
	private String razonSocial;
	private String departamento;
	private String cargo;
	private String descripcion;
	private Date fechaInicio;
	private Date fechaFin;
	
	private Empleado empleado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdExperienciaLaboral")
	public Long getIdExperienciaLaboral() {
		return idExperienciaLaboral;
	}
	
	public void setIdExperienciaLaboral(Long idExperienciaLaboral) {
		this.idExperienciaLaboral = idExperienciaLaboral;
	}
	
	@Column(name = "RazonSocial")
	public String getRazonSocial() {
		return razonSocial;
	}
	
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	
	@Column(name = "Departamento")
	public String getDepartamento() {
		return departamento;
	}
	
	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}
	
	@Column(name = "Cargo")
	public String getCargo() {
		return cargo;
	}
	
	public void setCargo(String cargo) {
		this.cargo = cargo;
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

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	
}
