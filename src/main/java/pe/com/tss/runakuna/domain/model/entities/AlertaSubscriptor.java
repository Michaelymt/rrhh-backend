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
@Table(name = "AlertaSubscriptor")
@NamedQuery(name = "AlertaSubscriptor.findAll", query = "SELECT e FROM AlertaSubscriptor e")
public class AlertaSubscriptor extends AuditingEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long idAlertaSubscriptor;
	private Alerta alerta;
	private Empleado empleado;
	
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdAlertaSubscriptor")
	public Long getIdAlertaSubscriptor() {
		return idAlertaSubscriptor;
	}
	public void setIdAlertaSubscriptor(Long idAlertaSubscriptor) {
		this.idAlertaSubscriptor = idAlertaSubscriptor;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdAlerta")
	public Alerta getAlerta() {
		return alerta;
	}
	public void setAlerta(Alerta alerta) {
		this.alerta = alerta;
	}
	
	
	
	
	
	
		      

}
