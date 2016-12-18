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
@Table(name = "TablaGeneral")
@NamedQuery(name = "TablaGeneral.findAll", query = "SELECT t FROM TablaGeneral t")
public class TablaGeneral extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idTablaGeneral;
	private String codigo;
	private String nombre;
	private String grupo;
	private String estado;
	
	private Empresa empresa;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdTablaGeneral")
	public Long getIdTablaGeneral() {
		return idTablaGeneral;
	}

	public void setIdTablaGeneral(Long idTablaGeneral) {
		this.idTablaGeneral = idTablaGeneral;
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

	@Column(name = "Grupo")
	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
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
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	

}
