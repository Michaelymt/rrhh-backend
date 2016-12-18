package pe.com.tss.runakuna.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class HorarioEmpleadoDto {

	private Long idHorarioEmpleado;
	private Long idEmpleado;
	private Long idHorario;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date inicioVigencia;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date finVigencia;
	
	private String tipoHorario;
	private String nombreTipoHorario;
	private String nombreHorario;
	private Integer horasSemanal;
	private Integer horasSemanalHorario;
	
	private List<HorarioEmpleadoDiaDto> horariosEmpleadoDia = new ArrayList<>();
	
	public Long getIdHorarioEmpleado() {
		return idHorarioEmpleado;
	}
	public void setIdHorarioEmpleado(Long idHorarioEmpleado) {
		this.idHorarioEmpleado = idHorarioEmpleado;
	}
	public Date getInicioVigencia() {
		return inicioVigencia;
	}
	public void setInicioVigencia(Date inicioVigencia) {
		this.inicioVigencia = inicioVigencia;
	}
	public Date getFinVigencia() {
		return finVigencia;
	}
	public void setFinVigencia(Date finVigencia) {
		this.finVigencia = finVigencia;
	}
	public String getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	public String getNombreTipoHorario() {
		return nombreTipoHorario;
	}
	public void setNombreTipoHorario(String nombreTipoHorario) {
		this.nombreTipoHorario = nombreTipoHorario;
	}
	public String getNombreHorario() {
		return nombreHorario;
	}
	public void setNombreHorario(String nombreHorario) {
		this.nombreHorario = nombreHorario;
	}
	public Integer getHorasSemanal() {
		return horasSemanal;
	}
	public void setHorasSemanal(Integer horasSemanal) {
		this.horasSemanal = horasSemanal;
	}
	public List<HorarioEmpleadoDiaDto> getHorariosEmpleadoDia() {
		return horariosEmpleadoDia;
	}
	public void setHorariosEmpleadoDia(List<HorarioEmpleadoDiaDto> horariosEmpleadoDia) {
		this.horariosEmpleadoDia = horariosEmpleadoDia;
	}
	public Integer getHorasSemanalHorario() {
		return horasSemanalHorario;
	}
	public void setHorasSemanalHorario(Integer horasSemanalHorario) {
		this.horasSemanalHorario = horasSemanalHorario;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public Long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	
}
