package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "HistorialLaboral")
@NamedQuery(name = "HistorialLaboral.findAll", query = "SELECT e FROM HistorialLaboral e")
public class HistorialLaboral extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idHistorialLaboral;
	private Empleado empleado;
	private Proyecto proyecto;
	private Cargo cargo;
	private Date fechaInicio;
	private Date fechaFin;
	private BigDecimal salario;
	private Moneda moneda;
	
	private UnidadDeNegocio unidadDeNegocio;
	private DepartamentoArea departamentoArea;
	
	private String descripcion;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHistorialLaboral")
	public Long getIdHistorialLaboral() {
		return idHistorialLaboral;
	}
	public void setIdHistorialLaboral(Long idHistorialLaboral) {
		this.idHistorialLaboral = idHistorialLaboral;
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
    @JoinColumn(name = "IdProyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdCargo")
	public Cargo getCargo() {
		return cargo;
	}
	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
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
	
	@Column(name = "Salario")
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdMoneda")
	public Moneda getMoneda() {
		return moneda;
	}
	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}
	
	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdUnidadDeNegocio")
	public UnidadDeNegocio getUnidadDeNegocio() {
		return unidadDeNegocio;
	}
	public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdDepartamentoArea")
	public DepartamentoArea getDepartamentoArea() {
		return departamentoArea;
	}
	public void setDepartamentoArea(DepartamentoArea departamentoArea) {
		this.departamentoArea = departamentoArea;
	}
	
	
	

}
