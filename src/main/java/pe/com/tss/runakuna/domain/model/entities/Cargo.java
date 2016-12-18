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
@Table(name = "Cargo")
@NamedQuery(name = "Cargo.findAll", query = "SELECT o FROM Cargo o")
public class Cargo extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idCargo;
	private Empresa empresa;
	private UnidadDeNegocio unidadDeNegocio;
	private DepartamentoArea departamentoArea;
	private String nombre;
	private String descripcion;
	
	public Cargo() { }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdCargo")
	public Long getIdCargo() {
		return idCargo;
	}

	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}

	@ManyToOne
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@ManyToOne
    @JoinColumn(name = "IdUnidadDeNegocio")
	public UnidadDeNegocio getUnidadDeNegocio() {
		return unidadDeNegocio;
	}

	public void setUnidadDeNegocio(UnidadDeNegocio unidadDeNegocio) {
		this.unidadDeNegocio = unidadDeNegocio;
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

}
