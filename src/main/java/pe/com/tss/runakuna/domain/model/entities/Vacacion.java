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
@Table(name = "Vacacion")
@NamedQuery(name = "Vacacion.findAll", query = "SELECT e FROM Vacacion e")
public class Vacacion extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idVacacion;
	private PeriodoEmpleado periodoEmpleado;
	private Empleado empleado;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer diasCalendarios;
	private Integer diasHabiles;
	private String Estado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdVacacion")
	public Long getIdVacacion() {
		return idVacacion;
	}
	public void setIdVacacion(Long idVacacion) {
		this.idVacacion = idVacacion;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdPeriodoEmpleado")
	public PeriodoEmpleado getPeriodoEmpleado() {
		return periodoEmpleado;
	}
	public void setPeriodoEmpleado(PeriodoEmpleado periodoEmpleado) {
		this.periodoEmpleado = periodoEmpleado;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdAtendidoPor")
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
	
	@Column(name = "DiasCalendarios")
	public Integer getDiasCalendarios() {
		return diasCalendarios;
	}
	public void setDiasCalendarios(Integer diasCalendarios) {
		this.diasCalendarios = diasCalendarios;
	}
	
	@Column(name = "DiasHabiles")
	public Integer getDiasHabiles() {
		return diasHabiles;
	}
	public void setDiasHabiles(Integer diasHabiles) {
		this.diasHabiles = diasHabiles;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return Estado;
	}
	public void setEstado(String estado) {
		Estado = estado;
	}
	
	
		      

}
