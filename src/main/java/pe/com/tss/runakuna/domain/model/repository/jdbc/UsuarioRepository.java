package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.dto.UsuarioDto;

public interface UsuarioRepository {

	UsuarioDto buscarUsuarioPorcuentaUsuario(String cuentaUsuario);
	
}
