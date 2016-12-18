package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.com.tss.runakuna.dto.UsuarioDto;

@Repository
public class UsuarioJdbcRepository implements UsuarioRepository{

	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    UsuarioJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    private static final String SEARCH_USER =
            "select * from Usuario where cuentaUsuario = :cuentaUsuario";
                    /*"  user_id as \"id\",\n" +
                    "  user_name as \"userName\",\n" +
                    "  password as \"password\",\n" +
                    "  First_Name as \"firstName\",\n" +
                    "  status as \"status\",\n" +
                    "  Last_Name as \"lastName,\n"  +
                    "  IdEmpleado as  \"idEmpleado\n"  +
                    " from Usuario " +
                    " where cuentaUsuario = :cuentaUsuario";*/
    
	@Transactional(readOnly = true)
    @Override
	public UsuarioDto buscarUsuarioPorcuentaUsuario(String cuentaUsuario) {
		LOGGER.info("Buscando usuarios para la autenticacion ", cuentaUsuario);

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("cuentaUsuario", cuentaUsuario);

        List<UsuarioDto> searchResults = jdbcTemplate.query(SEARCH_USER,
                queryParams,
                new BeanPropertyRowMapper<>(UsuarioDto.class)
        );

        LOGGER.info("Found usuario {}", searchResults);

        if (searchResults.size() > 0) {
            return searchResults.get(0);
        }

        return null;
	}

}
