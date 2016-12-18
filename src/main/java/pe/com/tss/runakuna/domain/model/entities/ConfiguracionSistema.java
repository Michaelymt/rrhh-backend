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
@Table(name = "ConfiguracionSistema")
@NamedQuery(name = "ConfiguracionSistema.findAll", query = "SELECT o FROM ConfiguracionSistema o")
public class ConfiguracionSistema extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idConfiguracionSistema;
	private Empresa empresa;
	private String codigo;
	private String valor;
	private String descripcion;
	
	public ConfiguracionSistema() { }

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdConfiguracionSistema")
	public Long getIdConfiguracionSistema() {
		return idConfiguracionSistema;
	}

	public void setIdConfiguracionSistema(Long idConfiguracionSistema) {
		this.idConfiguracionSistema = idConfiguracionSistema;
	}

	@ManyToOne
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	@Column(name = "Descripcion")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Valor")
	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

}
