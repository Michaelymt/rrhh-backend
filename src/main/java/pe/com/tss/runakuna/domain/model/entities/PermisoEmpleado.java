package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

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
@Table(name = "PermisoEmpleado")
@NamedQuery(name = "PermisoEmpleado.findAll", query = "SELECT e FROM PermisoEmpleado e")
public class PermisoEmpleado extends AuditingEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long idPermisoEmpleado;
	private String motivo;
	private String razon;
	private Date fecha;
	
	private String horaInicio;
	private String horaFin;
	private String estado;
	private BigDecimal horas;
    
    private Date fechaRecuperacion;
    
    private String horaInicioRecuperacion;
    private String horaFinRecuperacion;
    
    private PeriodoEmpleado periodoEmpleado;
    
	private Empleado empleado;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdPermisoEmpleado")
	public Long getIdPermisoEmpleado() {
		return idPermisoEmpleado;
	}

	public void setIdPermisoEmpleado(Long idPermisoEmpleado) {
		this.idPermisoEmpleado = idPermisoEmpleado;
	}
	
	@Column(name = "Motivo")
	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	@Column(name = "Razon")
	public String getRazon() {
		return razon;
	}

	public void setRazon(String razon) {
		this.razon = razon;
	}

	@Column(name = "Fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "HoraInicio")
	public String getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Column(name = "HoraFin")
	public String getHoraFin() {
		return horaFin;
	}

	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	@Column(name = "Estado")
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Column(name = "Horas")
	public BigDecimal getHoras() {
		return horas;
	}

	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}

	@Column(name = "FechaRecuperacion")
	public Date getFechaRecuperacion() {
		return fechaRecuperacion;
	}

	public void setFechaRecuperacion(Date fechaRecuperacion) {
		this.fechaRecuperacion = fechaRecuperacion;
	}

	@Column(name = "HoraInicioRecuperacion")
	public String getHoraInicioRecuperacion() {
		return horaInicioRecuperacion;
	}

	public void setHoraInicioRecuperacion(String horaInicioRecuperacion) {
		this.horaInicioRecuperacion = horaInicioRecuperacion;
	}

	@Column(name = "HoraFinRecuperacion")
	public String getHoraFinRecuperacion() {
		return horaFinRecuperacion;
	}

	public void setHoraFinRecuperacion(String horaFinRecuperacion) {
		this.horaFinRecuperacion = horaFinRecuperacion;
	}
	
	@ManyToOne()
    @JoinColumn(name = "IdPeriodoEmpleado")
	public PeriodoEmpleado getPeriodoEmpleado() {
		return periodoEmpleado;
	}

	public void setPeriodoEmpleado(PeriodoEmpleado periodoEmpleado) {
		this.periodoEmpleado = periodoEmpleado;
	}

	@ManyToOne()
    @JoinColumn(name = "IdAtendidoPor")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}