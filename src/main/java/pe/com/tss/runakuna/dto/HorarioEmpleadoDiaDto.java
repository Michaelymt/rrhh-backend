package pe.com.tss.runakuna.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonStringIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonStringIntegerSerializer;

public class HorarioEmpleadoDiaDto {

	private Long idHorarioEmpleadoDia;
	private String diaSemana;
	private String entrada;
	private String salida;
	private Integer tiempoAlmuerzo;
	private String nombreDiaSemana;
	
	@JsonSerialize(using=JsonStringIntegerSerializer.class)
	@JsonDeserialize(using=JsonStringIntegerDeserializer.class)
	private byte[] laboral;
	
	public Long getIdHorarioEmpleadoDia() {
		return idHorarioEmpleadoDia;
	}
	public void setIdHorarioEmpleadoDia(Long idHorarioEmpleadoDia) {
		this.idHorarioEmpleadoDia = idHorarioEmpleadoDia;
	}
	public String getDiaSemana() {
		return diaSemana;
	}
	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}
	public String getEntrada() {
		return entrada;
	}
	public void setEntrada(String entrada) {
		this.entrada = entrada;
	}
	public String getSalida() {
		return salida;
	}
	public void setSalida(String salida) {
		this.salida = salida;
	}
	public Integer getTiempoAlmuerzo() {
		return tiempoAlmuerzo;
	}
	public void setTiempoAlmuerzo(Integer tiempoAlmuerzo) {
		this.tiempoAlmuerzo = tiempoAlmuerzo;
	}
	public String getNombreDiaSemana() {
		return nombreDiaSemana;
	}
	public void setNombreDiaSemana(String nombreDiaSemana) {
		this.nombreDiaSemana = nombreDiaSemana;
	}
	public byte[] getLaboral() {
		return laboral;
	}
	public void setLaboral(byte[] laboral) {
		this.laboral = laboral;
	}
	
}
