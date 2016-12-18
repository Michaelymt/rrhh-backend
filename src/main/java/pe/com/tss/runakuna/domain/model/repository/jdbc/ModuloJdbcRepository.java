package pe.com.tss.runakuna.domain.model.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import pe.com.tss.runakuna.dto.ModuloDto;
import pe.com.tss.runakuna.dto.ModuloPadreDto;
import pe.com.tss.runakuna.dto.RolUsuarioDto;
import pe.com.tss.runakuna.dto.RolUsuarioPadreDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by josediaz on 25/11/2016.
 */
@Repository
public class ModuloJdbcRepository implements ModuloRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(ModuloJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    ModuloJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    private static final String SEARCH_MODULES_VISIBLE_PER_USER = "select \n" +
            "  modprt.Codigo as \"codigoPadre\", modprt.Nombre as \"nombrePadre\",\n" +
            "  modprt.Url as \"urlPadre\",\n" +
            "  M.IdModulo AS \"IdModulo\", \n" +
            "  M.Codigo AS \"codigo\",\n" +
            "  M.HelpUrl AS \"helpUrl\",\n" +
            "  M.EtiquetaMenu AS \"etiquetaMenu\",\n" +
            "  M.Nombre AS \"nombre\",\n" +
            "  M.URL AS \"url\"\n" +
            "from Modulo m\n" +
            "inner join Modulo modprt on M.IdParent = modprt.IdModulo\n" +
            "inner join Permiso p on p.IdModulo = M.IdModulo\n" +
            "inner join Rol r on r.IdRol = p.IdRol\n" +
            "inner join UsuarioRol ur on ur.IdRol = r.IdRol\n" +
            "inner join Usuario u on u.IdUsuario = Ur.IdUsuario\n" +
            "where 1=1\n" +
            "and modprt.Visible = 1\n" +
            "and M.Visible = 1\n" +
            "and p.TipoPermiso != 'N'\n" +
            "and u.cuentaUsuario = :cuentaUsuario \n" +
            "group by  modprt.Codigo , modprt.Nombre , modprt.Url, M.IdModulo, M.Codigo, M.HelpUrl, M.EtiquetaMenu, M.Nombre, M.URL , modprt.Orden, M.Orden \n" +
            "order by modprt.Orden, modprt.Codigo, m.Orden";
    
    private static final String SEARCH_ROLES_AVAILABLE_PER_USER = "select \n" +
            "  r.Nombre AS \"rolName\",\n" +
            "  u.IdEmpleado AS \"idEmpleado\" \n" +
            "from Usuario u \n" +
            "inner join UsuarioRol ur on u.IdUsuario = ur.IdUsuario\n" +
            "inner join Rol r on r.IdRol = ur.IdRol\n" +
            "where 1=1\n" +
            "and u.cuentaUsuario = :cuentaUsuario";


    @Override
    public List<ModuloPadreDto> getModulosPermitidosPorUsuario(String cuentaUsuario) {
        LOGGER.info("Buscando modulos visibles con permisos por cuenta de Usuario: {}", cuentaUsuario);

        List<ModuloPadreDto> searchResults =encontrarModulos(cuentaUsuario, SEARCH_MODULES_VISIBLE_PER_USER);

        return searchResults;
    }

    private List<ModuloPadreDto> encontrarModulos(String cuentaUsuario, String query){
        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("cuentaUsuario", cuentaUsuario);

        List<ModuloPadreDto> searchResults = jdbcTemplate.query(query,
                queryParams,
                new BeanPropertyRowMapper<>(ModuloPadreDto.class)
        );

        LOGGER.info("Encontro {} modulos ", searchResults.size());

        return searchResults;
    }

	@Override
	public List<RolUsuarioPadreDto> getRolPorUsuario(String cuentaUsuario) {
		LOGGER.info("Rol por cuenta de Usuario: {}", cuentaUsuario);

		List<RolUsuarioPadreDto> searchResults = encontrarRoles(cuentaUsuario, SEARCH_ROLES_AVAILABLE_PER_USER);

		return searchResults;
	}

	private List<RolUsuarioPadreDto> encontrarRoles(String cuentaUsuario, String query) {
		Map<String, String> queryParams = new HashMap<>();
        queryParams.put("cuentaUsuario", cuentaUsuario);

        List<RolUsuarioPadreDto> searchResults = jdbcTemplate.query(query,
                queryParams,
                new BeanPropertyRowMapper<>(RolUsuarioPadreDto.class)
        );

        LOGGER.info("Encontro {} ROLES ", searchResults.size());

        return searchResults;
	}
}
