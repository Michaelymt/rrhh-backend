package pe.com.tss.runakuna.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import pe.com.tss.runakuna.json.JsonVarbinarySerializer;
import pe.com.tss.runakuna.json.JsonVarbinaryDeserializer;

public class DocumentoEmpleadoDto {

	private Long idDocumentoEmpleado;
	private String nombre;
	
	@JsonSerialize(using=JsonVarbinarySerializer.class)
	@JsonDeserialize(using=JsonVarbinaryDeserializer.class)
	private byte[] contenidoArchivo;
	
	private String nombreArchivo;
	private String tipoArchivo;
	private String tipoDocumento;
	
	public Long getIdDocumentoEmpleado() {
		return idDocumentoEmpleado;
	}
	public void setIdDocumentoEmpleado(Long idDocumentoEmpleado) {
		this.idDocumentoEmpleado = idDocumentoEmpleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public byte[] getContenidoArchivo() {
		return this.contenidoArchivo;
	}
	public void setContenidoArchivo(byte[] contenidoArchivo) {
		this.contenidoArchivo = contenidoArchivo;
	}
	public String getNombreArchivo() {
		return nombreArchivo;
	}
	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}
	public String getTipoArchivo() {
		return tipoArchivo;
	}
	public void setTipoArchivo(String tipoArchivo) {
		this.tipoArchivo = tipoArchivo;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
		
}
