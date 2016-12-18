package pe.com.tss.runakuna.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.com.tss.runakuna.dto.UsuarioDto;
import pe.com.tss.runakuna.service.UsuarioService;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UsuarioController {
	
	@Autowired
	UsuarioService usuarioService;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public LoginResponse login(@RequestBody final UsuarioDto login){
		return null;
		
	}
	
	@RequestMapping(value = "/buscarUsuarioPorCuentaUsuario", method = RequestMethod.GET, produces = "application/json")
    public UsuarioDto buscarUsuarioPorCuentaUsuario(@RequestParam String cuentaUsuario) {
        return usuarioService.cargarUsuarioPorcuentaUsuario(cuentaUsuario);
    }
	
	@SuppressWarnings("unused")
    private static class LoginResponse {
        public String token;

        public LoginResponse(final String token) {
            this.token = token;
        }
    }

}
