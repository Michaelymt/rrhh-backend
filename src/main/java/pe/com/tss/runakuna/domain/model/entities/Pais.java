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
@Table(name = "Pais")
@NamedQuery(name = "Pais.findAll", query = "SELECT p FROM Pais p")
public class Pais extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idPais;
	private String codigo;
	private String Nombre;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPais")
	public Long getIdPais() {
		return idPais;
	}
	
	public void setIdPais(Long idPais) {
		this.idPais = idPais;
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
		return Nombre;
	}
	
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
}
