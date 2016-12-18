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
@Table(name = "HorarioEmpleadoDia")
@NamedQuery(name = "HorarioEmpleadoDia.findAll", query = "SELECT e FROM HorarioEmpleadoDia e")
public class HorarioEmpleadoDia extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long IdHorarioEmpleadoDia;
	private HorarioEmpleado horarioEmpleado;
	private String diaSemana;
	private byte[] laboral;
	private String entrada;
	private String salida;
	private Long tiempoAlmuerzo;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdHorarioEmpleadoDia")
	public Long getIdHorarioEmpleadoDia() {
		return IdHorarioEmpleadoDia;
	}
	public void setIdHorarioEmpleadoDia(Long idHorarioEmpleadoDia) {
		IdHorarioEmpleadoDia = idHorarioEmpleadoDia;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdHorarioEmpleado")
	public HorarioEmpleado getHorarioEmpleado() {
		return horarioEmpleado;
	}
	public void setHorarioEmpleado(HorarioEmpleado horarioEmpleado) {
		this.horarioEmpleado = horarioEmpleado;
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
