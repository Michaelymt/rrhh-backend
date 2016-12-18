package pe.com.tss.runakuna.dto;

public class BusquedaHorarioDto extends PageableFilter {

    private Long idHorario;
    private String nombreEmpleado;

    private Long unidadNegocio;
    private Long departamento;
    private Long proyecto;

    private String estado;
    private Integer porDefecto;
    private String tipoHorario;
    
	public Long getIdHorario() {
		return idHorario;
	}
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	public String getNombreEmpleado() {
		return nombreEmpleado;
	}
	public void setNombreEmpleado(String nombreEmpleado) {
		this.nombreEmpleado = nombreEmpleado;
	}
	public Long getUnidadNegocio() {
		return unidadNegocio;
	}
	public void setUnidadNegocio(Long unidadNegocio) {
		this.unidadNegocio = unidadNegocio;
	}
	public Long getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Long departamento) {
		this.departamento = departamento;
	}
	public Long getProyecto() {
		return proyecto;
	}
	public void setProyecto(Long proyecto) {
		this.proyecto = proyecto;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public Integer getPorDefecto() {
		return porDefecto;
	}
	public void setPorDefecto(Integer porDefecto) {
		this.porDefecto = porDefecto;
	}
	public String getTipoHorario() {
		return tipoHorario;
	}
	public void setTipoHorario(String tipoHorario) {
		this.tipoHorario = tipoHorario;
	}
	
}
