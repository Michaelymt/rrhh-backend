package pe.com.tss.runakuna.service.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.repository.jdbc.UsuarioRepository;
import pe.com.tss.runakuna.dto.UsuarioDto;
import pe.com.tss.runakuna.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

	private final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
	UsuarioRepository usuarioRepository;
	 
	@Override
	public UsuarioDto cargarUsuarioPorcuentaUsuario(String cuentaUsuario) {

		UsuarioDto result = usuarioRepository.buscarUsuarioPorcuentaUsuario(cuentaUsuario);
        

        return result;
	}

}
