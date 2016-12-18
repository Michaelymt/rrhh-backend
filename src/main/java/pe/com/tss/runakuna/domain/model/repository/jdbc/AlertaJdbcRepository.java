package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.dto.AlertaDto;
import pe.com.tss.runakuna.support.WhereParams;

/**
 * Created by josediaz on 14/12/2016.
 */
@Repository
public class AlertaJdbcRepository implements AlertaRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlertaJdbcRepository.class);

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    AlertaJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public AlertaDto obtenerAlerta(String codigo) {
        WhereParams params = new WhereParams();
        String sql = generarAlerta(codigo, params);

        return jdbcTemplate.queryForObject(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AlertaDto>(AlertaDto.class));
    }

    private String generarAlerta(String codigo, WhereParams params) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" A.IdAlerta AS idAlerta, ");
        sql.append(" A.IdEmpresa AS idEmpresa, ");
        sql.append(" A.Codigo as codigo, ");
        sql.append(" A.CorreoElectronico as correoElectronico, ");
        sql.append(" A.Dashboard as dashboard, ");
        sql.append(" A.PersonalAsunto as personalAsunto, ");
        sql.append(" A.PersonalCuerpo as personalCuerpo, ");
        sql.append(" A.GrupalAsunto as grupalAsunto, ");
        sql.append(" A.GrupalCuerpo as grupalCuerpo, ");
        sql.append(" A.JefeInmediato as jefeInmediato, ");
        sql.append(" A.Estado as estado ");
        sql.append(" FROM Alerta A ");

        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND A.Codigo = :codigo ",codigo));

        return sql.toString();
    }


	@Override
	public List<AlertaDto> obtenerAlertas(AlertaDto dto) {
		WhereParams params = new WhereParams();
        String sql = generarBusquedaAlerta(dto, params);

        return jdbcTemplate.query(sql.toString(),
                params.getParams(), new BeanPropertyRowMapper<AlertaDto>(AlertaDto.class));
	}


	private String generarBusquedaAlerta(AlertaDto dto, WhereParams params) {
		StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" A.IdAlerta AS idAlerta, ");
        sql.append(" A.IdEmpresa AS idEmpresa, ");
        sql.append(" A.TipoAlerta AS tipoAlerta, ");
        sql.append(" A.Codigo as codigo, ");
        sql.append(" A.CorreoElectronico as correoElectronico, ");
        sql.append(" A.Dashboard as dashboard, ");
        sql.append(" A.PersonalAsunto as personalAsunto, ");
        sql.append(" A.PersonalCuerpo as personalCuerpo, ");
        sql.append(" A.GrupalAsunto as grupalAsunto, ");
        sql.append(" A.GrupalCuerpo as grupalCuerpo, ");
        sql.append(" A.JefeInmediato as jefeInmediato, ");
        sql.append(" A.Estado as estado ");
        sql.append(" FROM Alerta A ");

        sql.append(" WHERE 1=1 ");

        sql.append(params.filter(" AND A.Codigo = :codigo ",dto.getCodigo()));
        sql.append(params.filter(" AND A.Estado = :estado ",dto.getEstado()));
        
        return sql.toString();
	}
}
