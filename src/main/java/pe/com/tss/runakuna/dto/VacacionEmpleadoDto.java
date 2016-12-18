package pe.com.tss.runakuna.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class VacacionEmpleadoDto {
	
	private Long idVacacion;
	private Long idPeriodoEmpleado;
	private Long idAtendidoPor;
	private Long idEmpleado;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	private Integer diasCalendarios;
	private Integer diasHabiles;
	private String estado;
	private String nombreEmpleado;
	private String jefeInmediato;
	private String nombreEstado;
	private String estadoString;
	private String nombreJefeInmediato;
	
	//TablaPeriodo
	private Integer maxDiasVacaciones;
	private Integer diasVacacionesDisponibles;
	private String periodo;
	
	public Long getIdVacacion() {
		return idVacacion;
	}
	public void setIdVacacion(Long idVacacion) {
		this.idVacacion = idVacacion;
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
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Integer getDiasCalendarios() {
		return diasCalendarios;
	}
	public void setDiasCalendarios(Integer diasCalendarios) {
		this.diasCalendarios = diasCalendarios;
	}
	public Integer getDiasHabiles() {
		return diasHabiles;
	}
	public void setDiasHabiles(Integer diasHabiles) {
		this.diasHabiles = diasHabiles;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getMaxDiasVacaciones() {
		return maxDiasVacaciones;
	}
	public void setMaxDiasVacaciones(Integer maxDiasVacaciones) {
		this.maxDiasVacaciones = maxDiasVacaciones;
	}
	public Integer getDiasVacacionesDisponibles() {
		return diasVacacionesDisponibles;
	}
	public void setDiasVacacionesDisponibles(Integer diasVacacionesDisponibles) {
		this.diasVacacionesDisponibles = diasVacacionesDisponibles;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}
	public String getNombreEstado() {
		return nombreEstado;
	}
	public void setNombreEstado(String nombreEstado) {
		this.nombreEstado = nombreEstado;
	}
	public String getEstadoString() {
		return estadoString;
	}
	public void setEstadoString(String estadoString) {
		this.estadoString = estadoString;
	}
	public String getNombreJefeInmediato() {
		return nombreJefeInmediato;
	}
	public void setNombreJefeInmediato(String nombreJefeInmediato) {
		this.nombreJefeInmediato = nombreJefeInmediato;
	}
	
	
	

}
