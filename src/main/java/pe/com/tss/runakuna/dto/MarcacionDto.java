package pe.com.tss.runakuna.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class MarcacionDto {

	private Long idMarcacion;
	private Long idEmpleado;
	private String horaIngreso;
	private String horaInicioAlmuerzo;
	private String horaFinAlmuerzo;
	private String horaSalida;
	private String nombreCompletoEmpleado;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fecha;
	
	private String tardanza;
	private String solicitudCambio;
	private String inasistencia;
	
	private String nombreProyecto;
	private String nombreDepartamento;
	private String nombreUnidadNegocio;
	
	private String horaIngresoHorario;
	private String horaSalidaHorario;
	
	private String nombreEmpleado;
	private String apelPaternoEmpleado;
	private String apelMaternoEmpleado;
	
	private String codigoEmpleado;
	
	private BigDecimal horasTrabajoHorario;
	private BigDecimal demoraEntrada;
	private BigDecimal demoraAlmuerzo;
	private BigDecimal demoraSalida;
	
	private BigDecimal horasTrabajoReal;
	
	private BigDecimal horasPermiso;
	
	private BigDecimal horasExtra;
	
	private BigDecimal horasTrabajoPendiente;

	public Long getIdMarcacion() {
		return idMarcacion;
	}

	public void setIdMarcacion(Long idMarcacion) {
		this.idMarcacion = idMarcacion;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
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

	public String getNombreCompletoEmpleado() {
		return nombreCompletoEmpleado;
	}

	public void setNombreCompletoEmpleado(String nombreCompletoEmpleado) {
		this.nombreCompletoEmpleado = nombreCompletoEmpleado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getTardanza() {
		return tardanza;
	}

	public void setTardanza(String tardanza) {
		this.tardanza = tardanza;
	}

	public String getSolicitudCambio() {
		return solicitudCambio;
	}

	public void setSolicitudCambio(String solicitudCambio) {
		this.solicitudCambio = solicitudCambio;
	}

	public String getInasistencia() {
		return inasistencia;
	}

	public void setInasistencia(String inasistencia) {
		this.inasistencia = inasistencia;
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

	public String getHoraIngresoHorario() {
		return horaIngresoHorario;
	}

	public void setHoraIngresoHorario(String horaIngresoHorario) {
		this.horaIngresoHorario = horaIngresoHorario;
	}

	public String getHoraSalidaHorario() {
		return horaSalidaHorario;
	}

	public void setHoraSalidaHorario(String horaSalidaHorario) {
		this.horaSalidaHorario = horaSalidaHorario;
	}

	public String getNombreEmpleado() {
		return nombreEmpleado;
	}

	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}

	public String getApelPaternoEmpleado() {
		return apelPaternoEmpleado;
	}

	public void setApelPaternoEmpleado(String apelPaternoEmpleado) {
		this.apelPaternoEmpleado = apelPaternoEmpleado;
	}

	public String getApelMaternoEmpleado() {
		return apelMaternoEmpleado;
	}

	public void setApelMaternoEmpleado(String apelMaternoEmpleado) {
		this.apelMaternoEmpleado = apelMaternoEmpleado;
	}

	public String getCodigoEmpleado() {
		return codigoEmpleado;
	}

	public void setCodigoEmpleado(String codigoEmpleado) {
		this.codigoEmpleado = codigoEmpleado;
	}

	public BigDecimal getHorasTrabajoHorario() {
		return horasTrabajoHorario;
	}

	public void setHorasTrabajoHorario(BigDecimal horasTrabajoHorario) {
		this.horasTrabajoHorario = horasTrabajoHorario;
	}

	public BigDecimal getDemoraEntrada() {
		return demoraEntrada;
	}

	public void setDemoraEntrada(BigDecimal demoraEntrada) {
		this.demoraEntrada = demoraEntrada;
	}

	public BigDecimal getDemoraAlmuerzo() {
		return demoraAlmuerzo;
	}

	public void setDemoraAlmuerzo(BigDecimal demoraAlmuerzo) {
		this.demoraAlmuerzo = demoraAlmuerzo;
	}

	public BigDecimal getDemoraSalida() {
		return demoraSalida;
	}

	public void setDemoraSalida(BigDecimal demoraSalida) {
		this.demoraSalida = demoraSalida;
	}

	public BigDecimal getHorasTrabajoReal() {
		return horasTrabajoReal;
	}

	public void setHorasTrabajoReal(BigDecimal horasTrabajoReal) {
		this.horasTrabajoReal = horasTrabajoReal;
	}

	public BigDecimal getHorasPermiso() {
		return horasPermiso;
	}

	public void setHorasPermiso(BigDecimal horasPermiso) {
		this.horasPermiso = horasPermiso;
	}

	public BigDecimal getHorasExtra() {
		return horasExtra;
	}

	public void setHorasExtra(BigDecimal horasExtra) {
		this.horasExtra = horasExtra;
	}

	public BigDecimal getHorasTrabajoPendiente() {
		return horasTrabajoPendiente;
	}

	public void setHorasTrabajoPendiente(BigDecimal horasTrabajoPendiente) {
		this.horasTrabajoPendiente = horasTrabajoPendiente;
	}
	
}
