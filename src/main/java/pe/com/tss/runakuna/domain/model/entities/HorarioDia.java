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
import javax.persistence.Table;

import pe.com.tss.runakuna.domain.model.base.AuditingEntity;

@Entity
@Table(name = "HorarioDia")
@NamedQuery(name = "HorarioDia.findAll", query = "SELECT e FROM HorarioDia e")
public class HorarioDia extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idHorarioDia;
    private Horario horario;
    private String diaSemana;
    private byte[] laboral;
    private String entrada;
    private String salida;
    private Long tiempoAlmuerzo;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHorarioDia")
	public Long getIdHorarioDia() {
		return idHorarioDia;
	}
	public void setIdHorarioDia(Long idHorarioDia) {
		this.idHorarioDia = idHorarioDia;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdHorario")
	public Horario getHorario() {
		return horario;
	}
	public void setHorario(Horario horario) {
		this.horario = horario;
	}
	
	@Column(name = "DiaSemana")
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	
	@Column(name = "Laboral")
	public byte[] getLaboral() {
		return laboral;
	}
	public void setLaboral(byte[] laboral) {
		this.laboral = laboral;
	}
	
	@Column(name = "Entrada")
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	
	@Column(name = "Salida")
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	
	@Column(name = "TiempoAlmuerzo")
	public Long getTiempoAlmuerzo() {
		return tiempoAlmuerzo;
	}
	public void setTiempoAlmuerzo(Long tiempoAlmuerzo) {
		this.tiempoAlmuerzo = tiempoAlmuerzo;
	}
    
    

}
