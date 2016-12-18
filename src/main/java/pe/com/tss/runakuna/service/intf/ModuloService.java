package pe.com.tss.runakuna.service.intf;

import pe.com.tss.runakuna.dto.ModuloDto;
import pe.com.tss.runakuna.dto.RolUsuarioDto;

import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface ModuloService {

    public List<ModuloDto> getModulosPermitidosPorUsuario(String cuentaUsuario) ;

	public List<RolUsuarioDto> getRolPorUsuario(String cuentaUsuario);
}
