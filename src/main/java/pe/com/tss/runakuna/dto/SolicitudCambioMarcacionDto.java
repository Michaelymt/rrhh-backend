package pe.com.tss.runakuna.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class SolicitudCambioMarcacionDto{

	private Long idSolicitudCambioMarcacion;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer cambiarIngreso;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer cambiarInicioAlmuerzo;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer cambiarFinAlmuerzo;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
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
	
	private MarcacionDto marcacion;

	public Long getIdSolicitudCambioMarcacion() {
		return idSolicitudCambioMarcacion;
	}

	public void setIdSolicitudCambioMarcacion(Long idSolicitudCambioMarcacion) {
		this.idSolicitudCambioMarcacion = idSolicitudCambioMarcacion;
	}

	public Integer getCambiarIngreso() {
		return cambiarIngreso;
	}

	public void setCambiarIngreso(Integer cambiarIngreso) {
		this.cambiarIngreso = cambiarIngreso;
	}

	public Integer getCambiarInicioAlmuerzo() {
		return cambiarInicioAlmuerzo;
	}

	public void setCambiarInicioAlmuerzo(Integer cambiarInicioAlmuerzo) {
		this.cambiarInicioAlmuerzo = cambiarInicioAlmuerzo;
	}

	public Integer getCambiarFinAlmuerzo() {
		return cambiarFinAlmuerzo;
	}

	public void setCambiarFinAlmuerzo(Integer cambiarFinAlmuerzo) {
		this.cambiarFinAlmuerzo = cambiarFinAlmuerzo;
	}

	public Integer getCambiarSalida() {
		return cambiarSalida;
	}

	public void setCambiarSalida(Integer cambiarSalida) {
		this.cambiarSalida = cambiarSalida;
	}

	public String getHoraIngreso() {
		return horaIngreso;
	}

	public void setHoraIngreso(String horaIngreso) {
		this.horaIngreso = horaIngreso;
	}

	public String getHoraInicioAlmuerzo() {
		return horaInicioAlmuerzo;
	}

	public void setHoraInicioAlmuerzo(String horaInicioAlmuerzo) {
		this.horaInicioAlmuerzo = horaInicioAlmuerzo;
	}

	public String getHoraFinAlmuerzo() {
		return horaFinAlmuerzo;
	}

	public void setHoraFinAlmuerzo(String horaFinAlmuerzo) {
		this.horaFinAlmuerzo = horaFinAlmuerzo;
	}

	public String getHoraSalida() {
		return horaSalida;
	}

	public void setHoraSalida(String horaSalida) {
		this.horaSalida = horaSalida;
	}

	public String getRazonCambioHoraIngreso() {
		return razonCambioHoraIngreso;
	}

	public void setRazonCambioHoraIngreso(String razonCambioHoraIngreso) {
		this.razonCambioHoraIngreso = razonCambioHoraIngreso;
	}

	public String getRazonCambioHoraInicioAlmuerzo() {
		return razonCambioHoraInicioAlmuerzo;
	}

	public void setRazonCambioHoraInicioAlmuerzo(String razonCambioHoraInicioAlmuerzo) {
		this.razonCambioHoraInicioAlmuerzo = razonCambioHoraInicioAlmuerzo;
	}

	public String getRazonCambioHoraFinAlmuerzo() {
		return razonCambioHoraFinAlmuerzo;
	}

	public void setRazonCambioHoraFinAlmuerzo(String razonCambioHoraFinAlmuerzo) {
		this.razonCambioHoraFinAlmuerzo = razonCambioHoraFinAlmuerzo;
	}

	public String getRazonCambioHoraSalida() {
		return razonCambioHoraSalida;
	}

	public void setRazonCambioHoraSalida(String razonCambioHoraSalida) {
		this.razonCambioHoraSalida = razonCambioHoraSalida;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public MarcacionDto getMarcacion() {
		return marcacion;
	}

	public void setMarcacion(MarcacionDto marcacion) {
		this.marcacion = marcacion;
	}
	
}