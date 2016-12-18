package pe.com.tss.runakuna.domain.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "Moneda")
@NamedQuery(name = "Moneda.findAll", query = "SELECT e FROM Moneda e")
public class Moneda {
	
	private static final long serialVersionUID = 1L;
	
	private Long idMoneda;
	private String codigo;
	private String nombre;
	private String sigla;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMoneda")
	public Long getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(Long idMoneda) {
		this.idMoneda = idMoneda;
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
	
	@Column(name = "Sigla")
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
	

}
