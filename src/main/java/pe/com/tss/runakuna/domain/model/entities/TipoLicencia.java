package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "TipoLicencia")
@NamedQuery(name = "TipoLicencia.findAll", query = "SELECT l FROM TipoLicencia l")
public class TipoLicencia extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idTipoLicencia;
	private String codigo;
	private String nombre;
	private Integer limiteMensual;
	private Integer limiteAnual;
	private Integer activaParaESS;
	private String estado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idTipoLicencia")
	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}

	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	
	@Column(name = "ActivaParaESS")
	public Integer getActivaParaESS() {
		return activaParaESS;
	}

	public void setActivaParaESS(Integer activaParaESS) {
		this.activaParaESS = activaParaESS;
	}
	
	@Column(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "LimiteMensual")
	public Integer getLimiteMensual() {
		return limiteMensual;
	}

	public void setLimiteMensual(Integer limiteMensual) {
		this.limiteMensual = limiteMensual;
	}

	@Column(name = "LimiteAnual")
	public Integer getLimiteAnual() {
		return limiteAnual;
	}

	public void setLimiteAnual(Integer limiteAnual) {
		this.limiteAnual = limiteAnual;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}


	
	
}
