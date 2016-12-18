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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Departamento")
@NamedQuery(name = "Departamento.findAll", query = "SELECT o FROM Departamento o")
public class Departamento extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idDepartamento;
	private Pais pais;
	private String codigo;
	private String Nombre;
	
	public Departamento() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDepartamento")
	public Long getIdDepartamento() {
		return idDepartamento;
	}
	
	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}

	@ManyToOne
    @JoinColumn(name = "IdPais")
	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	@JoinColumn(name = "Codigo")
	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	@JoinColumn(name = "Nombre")
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	
	

}
