package pe.com.tss.runakuna.dto;

import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonFlagIntegerDeserializer;
import pe.com.tss.runakuna.json.JsonFlagIntegerSerializer;

public class HorarioDto {
	
	private Long idHorario;
	private Long idEmpresa;
	private Long idProyecto;
	private Long horasSemanal;
	private String nombre;
	
	@JsonSerialize(using=JsonFlagIntegerSerializer.class)
	@JsonDeserialize(using=JsonFlagIntegerDeserializer.class)
	private Integer porDefecto;
	
	private String nombrePorDefecto;
	
	private String nombreProyecto;
	private String nombreDepartamento;
	private String nombreUnidadNegocio;
	private Integer diasSemana;
	private String tipoHorario;
	private String nombreTipoHorario;
	private String estado;
	private String nombreEstado;
	
	public List<HorarioDiaDto> horarioDias;
	
	public Long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	public Long getIdEmpresa() {
		return idEmpresa;
	}
	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Long getHorasSemanal() {
		return horasSemanal;
	}
	public void setHorasSemanal(Long horasSemanal) {
		this.horasSemanal = horasSemanal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Integer getPorDefecto() {
		return porDefecto;
	}
	public void setPorDefecto(Integer porDefecto) {
		this.porDefecto = porDefecto;
	}
	public String getNombrePorDefecto() {
		return nombrePorDefecto;
	}
	public void setNombrePorDefecto(String nombrePorDefecto) {
		this.nombrePorDefecto = nombrePorDefecto;
	}
	public String getNombreProyecto() {
		return nombreProyecto;
	}
	public void setNombreProyecto(String nombreProyecto) {
		this.nombreProyecto = nombreProyecto;
	}
	public String getNombreDepartamento() {
		return nombreDepartamento;
	}
	public void setNombreDepartamento(String nombreDepartamento) {
		this.nombreDepartamento = nombreDepartamento;
	}
	public String getNombreUnidadNegocio() {
		return nombreUnidadNegocio;
	}
	public void setNombreUnidadNegocio(String nombreUnidadNegocio) {
		this.nombreUnidadNegocio = nombreUnidadNegocio;
	}
	public Integer getDiasSemana() {
		return diasSemana;
	}
	public void setDiasSemana(Integer diasSemana) {
		this.diasSemana = diasSemana;
	}
	public String getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	public List<HorarioDiaDto> getHorarioDias() {
		return horarioDias;
	}
	public void setHorarioDias(List<HorarioDiaDto> horarioDias) {
		this.horarioDias = horarioDias;
	}
	public String getNombreTipoHorario() {
		return nombreTipoHorario;
	}
	public void setNombreTipoHorario(String nombreTipoHorario) {
		this.nombreTipoHorario = nombreTipoHorario;
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
	
}
