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
@Table(name = "Provincia")
@NamedQuery(name = "Provincia.findAll", query = "SELECT p FROM Provincia p")
public class Provincia extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idProvincia;
	private Departamento departamento;
	private String codigo;
	private String Nombre;
	
	public Provincia() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdProvincia")
	public Long getIdProvincia() {
		return idProvincia;
	}
	
	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	@ManyToOne
    @JoinColumn(name = "IdDepartamento")
	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
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
