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
@Table(name = "SolicitudCambioMarcacion")
@NamedQuery(name = "SolicitudCambioMarcacion.findAll", query = "SELECT s FROM SolicitudCambioMarcacion s")
public class SolicitudCambioMarcacion extends AuditingEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idSolicitudCambioMarcacion;
	private Integer cambiarIngreso;
	private Integer cambiarInicioAlmuerzo;
	private Integer cambiarFinAlmuerzo;
	private Integer cambiarSalida;
	
	private String horaIngreso;
	private String horaInicioAlmuerzo;
	private String horaFinAlmuerzo;
	private String horaSalida;
	
	private String razonCambioHoraIngreso;
	private String razonCambioHoraInicioAlmuerzo;
	private String razonCambioHoraFinAlmuerzo;
	private String razonCambioHoraSalida;
	private String estado;
	
	private Empleado empleado;
	
	private Marcacion marcacion;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdSolicitudCambioMarcacion")
	public Long getIdSolicitudCambioMarcacion() {
		return idSolicitudCambioMarcacion;
	}

	public void setIdSolicitudCambioMarcacion(Long idSolicitudCambioMarcacion) {
		this.idSolicitudCambioMarcacion = idSolicitudCambioMarcacion;
	}

	@Column(name = "CambiarIngreso")
	public Integer getCambiarIngreso() {
		return cambiarIngreso;
	}

	public void setCambiarIngreso(Integer cambiarIngreso) {
		this.cambiarIngreso = cambiarIngreso;
	}

	@Column(name = "CambiarInicioAlmuerzo")
	public Integer getCambiarInicioAlmuerzo() {
		return cambiarInicioAlmuerzo;
	}

	public void setCambiarInicioAlmuerzo(Integer cambiarInicioAlmuerzo) {
		this.cambiarInicioAlmuerzo = cambiarInicioAlmuerzo;
	}

	@Column(name = "CambiarFinAlmuerzo")
	public Integer getCambiarFinAlmuerzo() {
		return cambiarFinAlmuerzo;
	}

	public void setCambiarFinAlmuerzo(Integer cambiarFinAlmuerzo) {
		this.cambiarFinAlmuerzo = cambiarFinAlmuerzo;
	}

	@Column(name = "CambiarSalida")
	public Integer getCambiarSalida() {
		return cambiarSalida;
	}

	public void setCambiarSalida(Integer cambiarSalida) {
		this.cambiarSalida = cambiarSalida;
	}

	@Column(name = "HoraIngreso")
	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	@Column(name = "HoraInicioAlmuerzo")
	public String getHoraInicioAlmuerzo() {
		return horaInicioAlmuerzo;
	}

	public void setHoraInicioAlmuerzo(String horaInicioAlmuerzo) {
		this.horaInicioAlmuerzo = horaInicioAlmuerzo;
	}

	@Column(name = "HoraFinAlmuerzo")
	public String getHoraFinAlmuerzo() {
		return horaFinAlmuerzo;
	}

	public void setHoraFinAlmuerzo(String horaFinAlmuerzo) {
		this.horaFinAlmuerzo = horaFinAlmuerzo;
	}

	@Column(name = "HoraSalida")
	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	@Column(name = "RazonCambioHoraIngreso")
	public String getRazonCambioHoraIngreso() {
		return razonCambioHoraIngreso;
	}

	public void setRazonCambioHoraIngreso(String razonCambioHoraIngreso) {
		this.razonCambioHoraIngreso = razonCambioHoraIngreso;
	}

	@Column(name = "RazonCambioHoraInicioAlmuerzo")
	public String getRazonCambioHoraInicioAlmuerzo() {
		return razonCambioHoraInicioAlmuerzo;
	}

	public void setRazonCambioHoraInicioAlmuerzo(String razonCambioHoraInicioAlmuerzo) {
		this.razonCambioHoraInicioAlmuerzo = razonCambioHoraInicioAlmuerzo;
	}

	@Column(name = "RazonCambioHoraFinAlmuerzo")
	public String getRazonCambioHoraFinAlmuerzo() {
		return razonCambioHoraFinAlmuerzo;
	}

	public void setRazonCambioHoraFinAlmuerzo(String razonCambioHoraFinAlmuerzo) {
		this.razonCambioHoraFinAlmuerzo = razonCambioHoraFinAlmuerzo;
	}

	@Column(name = "RazonHoraSalida")
	public String getRazonCambioHoraSalida() {
		return razonCambioHoraSalida;
	}

	public void setRazonCambioHoraSalida(String razonCambioHoraSalida) {
		this.razonCambioHoraSalida = razonCambioHoraSalida;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdAtendidoPor")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdMarcacion")
	public Marcacion getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(Marcacion marcacion) {
		this.marcacion = marcacion;
	}
	

	
		
}