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
@Table(name = "CentroCosto")
@NamedQuery(name = "CentroCosto.findAll", query = "SELECT c FROM CentroCosto c")
public class CentroCosto extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idCentroCosto;
	private String nombre;
	private String descripcion;
	private String estado;
	
	private Empresa Empresa;
	
	private UnidadDeNegocio unidadDeNegocio;
	
	private DepartamentoArea departamentoArea;
	
	private Proyecto proyecto;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdCentroCosto")
	public Long getIdCentroCosto() {
		return idCentroCosto;
	}

	public void setIdCentroCosto(Long idCentroCosto) {
		this.idCentroCosto = idCentroCosto;
	}

	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return Empresa;
	}

	public void setEmpresa(Empresa empresa) {
		Empresa = empresa;
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

	@ManyToOne()
    @JoinColumn(name = "IdProyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	} 
		
}