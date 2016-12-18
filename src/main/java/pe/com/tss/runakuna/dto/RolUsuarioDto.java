package pe.com.tss.runakuna.dto;

import java.util.List;

public class RolUsuarioDto {
	
	private Long idEmpleado;
	private String rolName;
	private List<RolUsuarioDto> rolNames;
	
	public RolUsuarioDto(){}
	
	public RolUsuarioDto(Long idEmpleado, String rolName){
		this.idEmpleado = idEmpleado;
		this.rolName 	= rolName;
	}

	public Long getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(Long idEmpleado) {
		this.idEmpleado = idEmpleado;
	}

	public List<RolUsuarioDto> getRolNames() {
		return rolNames;
	}

	public void setRolNames(List<RolUsuarioDto> rolNames) {
		this.rolNames = rolNames;
	}

	public String getRolName() {
		return rolName;
	}

	public void setRolName(String rolName) {
		this.rolName = rolName;
	}
	
	

}
