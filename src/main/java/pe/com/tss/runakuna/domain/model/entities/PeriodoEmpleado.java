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
@Table(name = "PeriodoEmpleado")
@NamedQuery(name = "PeriodoEmpleado.findAll", query = "SELECT e FROM PeriodoEmpleado e")
public class PeriodoEmpleado extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPeriodoEmpleado;
	private String periodo;
	private Integer maxPermisos;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer maxDiasVacaciones;
	private Integer permisosDisponibles;
	private Integer diasVacacionesDisponibles;

	private Empleado empleado;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPeriodoEmpleado")
	public Long getIdPeriodoEmpleado() {
		return idPeriodoEmpleado;
	}

	public void setIdPeriodoEmpleado(Long idPeriodoEmpleado) {
		this.idPeriodoEmpleado = idPeriodoEmpleado;
	}

	@Column(name = "Periodo")
	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
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

	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@Column(name = "MaxPermisos")
	public Integer getMaxPermisos() {
		return maxPermisos;
	}

	public void setMaxPermisos(Integer maxPermisos) {
		this.maxPermisos = maxPermisos;
	}
	//
	@Column(name = "MaxDiasVacaciones")
	public Integer getMaxDiasVacaciones() {
		return maxDiasVacaciones;
	}

	public void setMaxDiasVacaciones(Integer maxDiasVacaciones) {
		this.maxDiasVacaciones = maxDiasVacaciones;
	}
	@Column(name = "PermisosDisponibles")
	public Integer getPermisosDisponibles() {
		return permisosDisponibles;
	}

	public void setPermisosDisponibles(Integer permisosDisponibles) {
		this.permisosDisponibles = permisosDisponibles;
	}
	
	@Column(name = "DiasVacacionesDisponibles")
	public Integer getDiasVacacionesDisponibles() {
		return diasVacacionesDisponibles;
	}

	public void setDiasVacacionesDisponibles(Integer diasVacacionesDisponibles) {
		this.diasVacacionesDisponibles = diasVacacionesDisponibles;
	}
	
	
	
}
