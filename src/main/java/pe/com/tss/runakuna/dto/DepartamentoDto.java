package pe.com.tss.runakuna.dto;

public class DepartamentoDto {
	
	private Long idDepartamento;
//	private Long idPais;
	private String codigo;
	private String nombre;
	
	public Long getIdDepartamento() {
		return idDepartamento;
	}
	public void setIdDepartamento(Long idDepartamento) {
		this.idDepartamento = idDepartamento;
	}
//	public Long getIdPais() {
//		return idPais;
//	}
//	public void setIdPais(Long idPais) {
//		this.idPais = idPais;
//	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	

}
