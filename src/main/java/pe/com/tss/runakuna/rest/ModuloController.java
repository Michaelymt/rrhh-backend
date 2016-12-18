package pe.com.tss.runakuna.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pe.com.tss.runakuna.dto.ModuloDto;
import pe.com.tss.runakuna.dto.RolUsuarioDto;
import pe.com.tss.runakuna.service.intf.ModuloService;

import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
@RestController
@RequestMapping("/modulo")
public class ModuloController {


    @Autowired
    private ModuloService moduloService;

    @RequestMapping(value="/modulosPermitidos", method= RequestMethod.GET, produces="application/json")
    public List<ModuloDto> allowedModules(@RequestParam("cuentaUsuario") String cuentaUsuario) {
        return moduloService.getModulosPermitidosPorUsuario(cuentaUsuario);
    }
    
    @RequestMapping(value="/rolNamesUser", method= RequestMethod.GET, produces="application/json")
    public List<RolUsuarioDto> rolUsuario(@RequestParam("cuentaUsuario") String cuentaUsuario) {
        return moduloService.getRolPorUsuario(cuentaUsuario);
    }

}
