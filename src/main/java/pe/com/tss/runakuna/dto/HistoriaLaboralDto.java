package pe.com.tss.runakuna.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class HistoriaLaboralDto {

	private Long   idHistorialLaboral;
	private Long idUnidaDeNegocio;
	private Long idEmpleado;
	private String unidadNegocio;
	private String departamentoArea;
	private Long idDepartamentoArea;
	private String proyecto;
	private String cargo;
	private String jefeInmediato;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date desdeFecha;
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date hastaFecha;
	private BigDecimal salario;
	private Long   idProyecto;
	private Long   idCargo;
	private String descripcion;
	private Long   idMoneda;
	private Integer horasSemanal;
	private Integer horasSemanalHorario;
	
	//HorarioEmpleado
	private Long idHorarioEmpleado;
	private String tipoHorario;
	private Long idHorario;
	
	//HorarioEmpleadoDia
	private Long idHorarioEmpleadoDia;
	private String diaSemana;
	private byte[] laboral;
	private String entrada;
	private String salida;
	private Long tiempoAlmuerzo;
	
	private HorarioEmpleadoDto horarioEmpleado;
	
	//----------------
	public Long getIdHistorialLaboral() {
		return idHistorialLaboral;
	}
	public void setIdHistorialLaboral(Long idHistorialLaboral) {
		this.idHistorialLaboral = idHistorialLaboral;
	}
	public String getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(String unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public Long getIdEmpleado() {
		return idEmpleado;
	}
	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
	public String getDepartamentoArea() {
		return departamentoArea;
	}
	public void setDepartamentoArea(String departamentoArea) {
		this.departamentoArea = departamentoArea;
	}
	
	public Long getIdDepartamentoArea() {
		return idDepartamentoArea;
	}
	public void setIdDepartamentoArea(Long idDepartamentoArea) {
		this.idDepartamentoArea = idDepartamentoArea;
	}
	public String getProyecto() {
		return proyecto;
	}
	public void setProyecto(String proyecto) {
		this.proyecto = proyecto;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
	public String getJefeInmediato() {
		return jefeInmediato;
	}
	public void setJefeInmediato(String jefeInmediato) {
		this.jefeInmediato = jefeInmediato;
	}

	public Date getDesdeFecha() {
		return desdeFecha;
	}
	public void setDesdeFecha(Date desdeFecha) {
		this.desdeFecha = desdeFecha;
	}
	public Date getHastaFecha() {
		return hastaFecha;
	}
	public void setHastaFecha(Date hastaFecha) {
		this.hastaFecha = hastaFecha;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public Long getIdProyecto() {
		return idProyecto;
	}
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}
	public Long getIdCargo() {
		return idCargo;
	}
	public void setIdCargo(Long idCargo) {
		this.idCargo = idCargo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Long getIdMoneda() {
		return idMoneda;
	}
	public void setIdMoneda(Long idMoneda) {
		this.idMoneda = idMoneda;
	}
	
	public Integer getHorasSemanal() {
		return horasSemanal;
	}
	public void setHorasSemanal(Integer horasSemanal) {
		this.horasSemanal = horasSemanal;
	}
	
	//Horario Empleado
	public Long getIdHorarioEmpleado() {
		return idHorarioEmpleado;
	}
	public void setIdHorarioEmpleado(Long idHorarioEmpleado) {
		this.idHorarioEmpleado = idHorarioEmpleado;
	}
	public String getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	public Long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
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
	public byte[] getLaboral() {
		return laboral;
	}
	public void setLaboral(byte[] laboral) {
		this.laboral = laboral;
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
	public Long getTiempoAlmuerzo() {
		return tiempoAlmuerzo;
	}
	public void setTiempoAlmuerzo(Long tiempoAlmuerzo) {
		this.tiempoAlmuerzo = tiempoAlmuerzo;
	}
	public Long getIdUnidaDeNegocio() {
		return idUnidaDeNegocio;
	}
	public void setIdUnidaDeNegocio(Long idUnidaDeNegocio) {
		this.idUnidaDeNegocio = idUnidaDeNegocio;
	}
	public Integer getHorasSemanalHorario() {
		return horasSemanalHorario;
	}
	public void setHorasSemanalHorario(Integer horasSemanalHorario) {
		this.horasSemanalHorario = horasSemanalHorario;
	}
	public HorarioEmpleadoDto getHorarioEmpleado() {
		return horarioEmpleado;
	}
	public void setHorarioEmpleado(HorarioEmpleadoDto horarioEmpleado) {
		this.horarioEmpleado = horarioEmpleado;
	}
	
}
