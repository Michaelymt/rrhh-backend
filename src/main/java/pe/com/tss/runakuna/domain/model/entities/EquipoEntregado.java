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
@Table(name = "EquipoEntregado")
@NamedQuery(name = "EquipoEntregado.findAll", query = "SELECT e FROM EquipoEntregado e")
public class EquipoEntregado extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idEquipoEntregado;
	private String tipoEquipo;
	private String descripcion;
	private Date fechaEntrega;
	private Date fechaDevolucion;
	private String estado;
	
	private Empleado empleado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEquipoEntregado")
	public Long getIdEquipoEntregado() {
		return idEquipoEntregado;
	}
	
	public void setIdEquipoEntregado(Long idEquipoEntregado) {
		this.idEquipoEntregado = idEquipoEntregado;
	}
	
	@Column(name = "TipoEquipo")
	public String getTipoEquipo() {
		return tipoEquipo;
	}
	
	public void setTipoEquipo(String tipoEquipo) {
		this.tipoEquipo = tipoEquipo;
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

	@Column(name = "FechaEntrega")
	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	@Column(name = "FechaDevolucion")
	public Date getFechaDevolucion() {
		return fechaDevolucion;
	}

	public void setFechaDevolucion(Date fechaDevolucion) {
		this.fechaDevolucion = fechaDevolucion;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
