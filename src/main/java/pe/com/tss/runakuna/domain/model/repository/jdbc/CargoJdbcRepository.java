package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.DepartamentoDto;
import pe.com.tss.runakuna.dto.HorarioDto;

@Repository
public class CargoJdbcRepository implements CargoRepository{
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CargoJdbcRepository.class);
	
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	@Autowired
	CargoJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	private static final String SEARCH_ALL_CARGOS = "select * from Cargo";
	private static final String SEARCH_NOMBRE_HORARIO = "select * from Horario";
	
	@Override
	public List<CargoDto> findCargo() {
		List<CargoDto> searchResults = jdbcTemplate.query(SEARCH_ALL_CARGOS,
                new BeanPropertyRowMapper<>(CargoDto.class)
        );
        LOGGER.info("Found {} cargos", searchResults.size());

        return searchResults;
	}

	@Override
	public List<HorarioDto> obtenerNombreHorario() {
		List<HorarioDto> searchResults = jdbcTemplate.query(SEARCH_NOMBRE_HORARIO,
                new BeanPropertyRowMapper<>(HorarioDto.class)
        );
        LOGGER.info("Found {} nombre Horario", searchResults.size());

        return searchResults;
	}

}
