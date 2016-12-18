package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "Horario")
@NamedQuery(name = "Horario.findAll", query = "SELECT e FROM Horario e")
public class Horario extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idHorario;
	private Empresa empresa;
	private Proyecto proyecto;
	private Integer horasSemanal;
	private Integer porDefecto;
	private String nombre;
	
	private String estado;
	private String tipoHorario;
	
	private List<HorarioDia> horariosDias;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHorario")
	public Long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpresa")
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdProyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}
	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	@Column(name = "HorasSemanal")
	public Integer getHorasSemanal() {
		return horasSemanal;
	}
	public void setHorasSemanal(Integer horasSemanal) {
		this.horasSemanal = horasSemanal;
	}
	
	@Column(name = "Nombre")
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name = "PorDefecto")
	public Integer getPorDefecto() {
		return porDefecto;
	}
	
	public void setPorDefecto(Integer porDefecto) {
		this.porDefecto = porDefecto;
	}
	
	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}
	
	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	@Column(name = "TipoHorario")
	public String getTipoHorario() {
		return tipoHorario;
	}
	
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "horario")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<HorarioDia> getHorariosDias() {
		return horariosDias;
	}
	
	public void setHorariosDias(List<HorarioDia> horariosDias) {
		this.horariosDias = horariosDias;
	}
	
	public HorarioDia addHorarioDia(HorarioDia horarioDia) {
		getHorariosDias().add(horarioDia);
		horarioDia.setHorario(this);
        return horarioDia;
    }
	
	public HorarioDia removeHorarioDia(HorarioDia horarioDia) {
		getHorariosDias().remove(horarioDia);
		horarioDia.setHorario(null);
        return horarioDia;
    }
	
}
