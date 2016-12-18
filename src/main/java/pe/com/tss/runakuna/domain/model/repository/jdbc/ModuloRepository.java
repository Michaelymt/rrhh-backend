package pe.com.tss.runakuna.domain.model.repository.jdbc;

import pe.com.tss.runakuna.dto.ModuloPadreDto;
import pe.com.tss.runakuna.dto.RolUsuarioDto;
import pe.com.tss.runakuna.dto.RolUsuarioPadreDto;

import java.util.List;

/**
 * Created by josediaz on 25/11/2016.
 */
public interface ModuloRepository {

    public List<ModuloPadreDto> getModulosPermitidosPorUsuario(String cuentaUsuario);

	public List<RolUsuarioPadreDto> getRolPorUsuario(String cuentaUsuario);
}
