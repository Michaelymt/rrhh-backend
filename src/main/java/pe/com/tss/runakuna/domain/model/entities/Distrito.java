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
@Table(name = "Distrito")
@NamedQuery(name = "Distrito.findAll", query = "SELECT d FROM Distrito d")
public class Distrito extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idDistrito;
	private Provincia provincia;
	private String Nombre;
	private String zip;
	
	
	public Distrito() {}
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdDitrito")
	public Long getIdDistrito() {
		return idDistrito;
	}
	
	public void setIdDistrito(Long idDistrito) {
		this.idDistrito = idDistrito;
	}

	@ManyToOne
    @JoinColumn(name = "IdProvincia")
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}
	
	@JoinColumn(name = "Nombre")
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@JoinColumn(name = "Zip")
	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}
	

}
