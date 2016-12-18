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
@Table(name = "Proyecto")
@NamedQuery(name = "Proyecto.findAll", query = "SELECT o FROM Proyecto o")
public class Proyecto extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idProyecto;
	private DepartamentoArea departamentoArea;
	private String codigo;
	private String nombre;
	private String descripcion;
	private String estado;
	
	public Proyecto() { }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProyecto")
	public Long getIdProyecto() {
		return idProyecto;
	}

	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	@ManyToOne
    @JoinColumn(name = "IdDepartamentoArea")
	public DepartamentoArea getDepartamentoArea() {
		return departamentoArea;
	}

	public void setDepartamentoArea(DepartamentoArea departamentoArea) {
		this.departamentoArea = departamentoArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
}
