package pe.com.tss.runakuna.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

import java.math.BigDecimal;
import java.util.Date;

public class PermisoEmpleadoDto {

	private Long idPermisoEmpleado;
	private Long idEmpleado;
	private String motivo;
	private String nombreMotivo;
	private String razon;
	private String jefeInmediato;
	private String periodo;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fecha;
	
	private String horaInicio;
	private String horaFin;
	private String estado;
	private String nombreEstado;
	private BigDecimal horas;
	
    private String codigo;
    private Long idPeriodoEmpleado;
    private Long idAtendidoPor;
    
    @JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
    private Date fechaRecuperacion;
    
    private String horaInicioRecuperacion;
    private String horaFinRecuperacion;
    private String estadoString;
    
    private PeriodoEmpleadoDto periodoEmpleado;

	private String nombreEmpleado;
	
	public Long getIdPermisoEmpleado() {
		return idPermisoEmpleado;
	}
	public void setIdPermisoEmpleado(Long idPermisoEmpleado) {
		this.idPermisoEmpleado = idPermisoEmpleado;
	}
	public String getMotivo() {
		return motivo;
	}
	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
	public String getNombreMotivo() {
		return nombreMotivo;
	}
	public void setNombreMotivo(String nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}
	public String getRazon() {
		return razon;
	}
	public void setRazon(String razon) {
		this.razon = razon;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getHoraInicio() {
		return horaInicio;
	}
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}
	public String getHoraFin() {
		return horaFin;
	}
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public BigDecimal getHoras() {
		return horas;
	}
	public void setHoras(BigDecimal horas) {
		this.horas = horas;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Long getIdPeriodoEmpleado() {
		return idPeriodoEmpleado;
	}
	public void setIdPeriodoEmpleado(Long idPeriodoEmpleado) {
		this.idPeriodoEmpleado = idPeriodoEmpleado;
	}
	public Long getIdAtendidoPor() {
		return idAtendidoPor;
	}
	public void setIdAtendidoPor(Long idAtendidoPor) {
		this.idAtendidoPor = idAtendidoPor;
	}
	public Date getFechaRecuperacion() {
		return fechaRecuperacion;
	}
	public void setFechaRecuperacion(Date fechaRecuperacion) {
		this.fechaRecuperacion = fechaRecuperacion;
	}
	public String getHoraInicioRecuperacion() {
		return horaInicioRecuperacion;
	}
	public void setHoraInicioRecuperacion(String horaInicioRecuperacion) {
		this.horaInicioRecuperacion = horaInicioRecuperacion;
	}
	public String getHoraFinRecuperacion() {
		return horaFinRecuperacion;
	}
	public void setHoraFinRecuperacion(String horaFinRecuperacion) {
		this.horaFinRecuperacion = horaFinRecuperacion;
	}
	public String getEstadoString() {
		return estadoString;
	}
	public void setEstadoString(String estadoString) {
		this.estadoString = estadoString;
	}
	public PeriodoEmpleadoDto getPeriodoEmpleado() {
		return periodoEmpleado;
	}
	public void setPeriodoEmpleado(PeriodoEmpleadoDto periodoEmpleado) {
		this.periodoEmpleado = periodoEmpleado;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	
	
}