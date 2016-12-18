package pe.com.tss.runakuna.dto;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonDateSimpleDeserializer;
import pe.com.tss.runakuna.json.JsonDateSimpleSerializer;

public class LicenciaDto {

	private Long idLicencia;
	private String motivo;
	private String comentario;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaInicio;
	
	@JsonSerialize(using=JsonDateSimpleSerializer.class)
	@JsonDeserialize(using=JsonDateSimpleDeserializer.class)
	private Date fechaFin;
	
	private Integer dias;
	
	private String nombreMotivo;
	
	private List<DocumentoEmpleadoDto> documentos;

	public Long getIdLicencia() {
		return idLicencia;
	}

	public void setIdLicencia(Long idLicencia) {
		this.idLicencia = idLicencia;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
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

	public Integer getDias() {
		return dias;
	}

	public void setDias(Integer dias) {
		this.dias = dias;
	}

	public String getNombreMotivo() {
		return nombreMotivo;
	}

	public void setNombreMotivo(String nombreMotivo) {
		this.nombreMotivo = nombreMotivo;
	}
	
	public List<DocumentoEmpleadoDto> getDocumentos() {
		return documentos;
	}
	public void setDocumentos(List<DocumentoEmpleadoDto> documentos) {
		this.documentos = documentos;
	}
	
}
