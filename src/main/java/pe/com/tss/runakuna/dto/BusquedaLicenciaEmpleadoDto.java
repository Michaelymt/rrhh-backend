package pe.com.tss.runakuna.dto;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class BusquedaLicenciaEmpleadoDto {

	
	private Long idEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	private String estado;
	
	private Long idUnidadNegocio;
	private Long idDepartamentoArea;
	private Long idProyecto;
	private Long idJefe;
	private Long idTipoLicencia;
	private Long idPeriodoEmpleado;
	
	public Long getIdUnidadNegocio() {
		return idUnidadNegocio;
	}
	public void setIdUnidadNegocio(Long idUnidadNegocio) {
		this.idUnidadNegocio = idUnidadNegocio;
	}
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}
	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Long getIdJefe() {
		return idJefe;
	}
	public void setIdJefe(Long idJefe) {
		this.idJefe = idJefe;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
	
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Long getIdTipoLicencia() {
		return idTipoLicencia;
	}
	public void setIdTipoLicencia(Long idTipoLicencia) {
		this.idTipoLicencia = idTipoLicencia;
	}
	public Long getIdPeriodoEmpleado() {
		return idPeriodoEmpleado;
	}
	public void setIdPeriodoEmpleado(Long idPeriodoEmpleado) {
		this.idPeriodoEmpleado = idPeriodoEmpleado;
	}
	

}
