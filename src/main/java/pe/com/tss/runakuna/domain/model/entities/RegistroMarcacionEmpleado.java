package pe.com.tss.runakuna.domain.model.entities;

import java.io.Serializable;
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

@Entity
@Table(name = "RegistroMarcacionEmpleado")
@NamedQuery(name = "RegistroMarcacionEmpleado.findAll", query = "SELECT r FROM RegistroMarcacionEmpleado r")
public class RegistroMarcacionEmpleado implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idRegistroMarcacionEmpleado;
	private String codigoEmpleado;
	private String dni;
	private Date fecha;
	private String hora;
	private String tipo;
	
	private String creador;

	private Date fechaCreacion;
		
	private Empleado empleado;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdRegistroMarcacionEmpleado")
	public Long getIdRegistroMarcacionEmpleado() {
		return idRegistroMarcacionEmpleado;
	}

	public void setIdRegistroMarcacionEmpleado(Long idRegistroMarcacionEmpleado) {
		this.idRegistroMarcacionEmpleado = idRegistroMarcacionEmpleado;
	}

	@Column(name = "CodigoEmpleado")
	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	@Column(name = "DNI")
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	@Column(name = "Fecha")
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	@Column(name = "Hora")
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	@Column(name = "Tipo")
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@Column(name = "Creador")
    public String getCreador() {
        return this.creador;
    }

    public void setCreador(String creador) {
        this.creador = creador;
    }

    @Column(name = "FechaCreacion")
    public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@ManyToOne()
    @JoinColumn(name = "IdEmpleado")
	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}
	
}