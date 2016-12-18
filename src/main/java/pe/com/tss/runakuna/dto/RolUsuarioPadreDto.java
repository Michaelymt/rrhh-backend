package pe.com.tss.runakuna.dto;

public class RolUsuarioPadreDto extends RolUsuarioDto {
	
	private String rolesNames;
	
	public RolUsuarioPadreDto(){
		
	}

	public RolUsuarioPadreDto(String rolesNames,Long idEmpleado, String rolName) {
		super(idEmpleado, rolName);
		this.rolesNames = rolesNames;
	}

	public String getRolesNames() {
		return rolesNames;
	}

	public void setRolesNames(String rolesNames) {
		this.rolesNames = rolesNames;
	}

	

}
