package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "HorarioEmpleado")
@NamedQuery(name = "HorarioEmpleado.findAll", query = "SELECT e FROM HorarioEmpleado e")
public class HorarioEmpleado extends AuditingEntity implements Serializable{

private static final long serialVersionUID = 1L;
	
	private Long idHorarioEmpleado;
	private Empleado empleado;
	private Date inicioVigencia;
	private Date finVigencia;
	private String tipoHorario;
	private Horario horario;
	private Integer horasSemanal;
	
	private List<HorarioEmpleadoDia> horarioEmpleadoDias; 
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHorarioEmpleado")
	public Long getIdHorarioEmpleado() {
		return idHorarioEmpleado;
	}
	public void setIdHorarioEmpleado(Long idHorarioEmpleado) {
		this.idHorarioEmpleado = idHorarioEmpleado;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	@Column(name = "InicioVigencia")
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	
	@Column(name = "FinVigencia")
	public Date getFinVigencia() {
		return finVigencia;
	}
	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	
	@Column(name = "TipoHorario")
	public String getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdHorario")
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	@Column(name = "HorasSemanal")
	public Integer getHorasSemanal() {
		return horasSemanal;
	}
	public void setHorasSemanal(Integer horasSemanal) {
		this.horasSemanal = horasSemanal;
	}
	
	@OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH},orphanRemoval=true, mappedBy = "horarioEmpleado")
	@Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE,
			org.hibernate.annotations.CascadeType.DELETE,
			org.hibernate.annotations.CascadeType.MERGE,
			org.hibernate.annotations.CascadeType.PERSIST
	})
	public List<HorarioEmpleadoDia> getHorarioEmpleadoDias() {
		return horarioEmpleadoDias;
	}
	public void setHorarioEmpleadoDias(List<HorarioEmpleadoDia> horarioEmpleadoDias) {
		this.horarioEmpleadoDias = horarioEmpleadoDias;
	}
	public HorarioEmpleadoDia addHorarioEmpleadoDia(HorarioEmpleadoDia horarioEmpleadoDia) {
		getHorarioEmpleadoDias().add(horarioEmpleadoDia);
		horarioEmpleadoDia.setHorarioEmpleado(this);
        return horarioEmpleadoDia;
    }
	
	public HorarioEmpleadoDia removeHorarioEmpleadoDia(HorarioEmpleadoDia horarioEmpleadoDia) {
		getHorarioEmpleadoDias().remove(horarioEmpleadoDia);
		horarioEmpleadoDia.setHorarioEmpleado(null);
        return horarioEmpleadoDia;
    }
	
	
	
	
}
