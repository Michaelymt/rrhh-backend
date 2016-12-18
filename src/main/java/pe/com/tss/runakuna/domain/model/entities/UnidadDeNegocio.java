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
@Table(name = "UnidadDeNegocio")
@NamedQuery(name = "UnidadDeNegocio.findAll", query = "SELECT u FROM UnidadDeNegocio u")
public class UnidadDeNegocio extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idUnidadDeNegocio;
	private Empresa empresa;
	private String nombre;
	private String estado;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdUnidadDeNegocio")
	public Long getIdUnidadDeNegocio() {
		return idUnidadDeNegocio;
	}

	public void setIdUnidadDeNegocio(Long idUnidadDeNegocio) {
		this.idUnidadDeNegocio = idUnidadDeNegocio;
	}

	@ManyToOne
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
	
}
