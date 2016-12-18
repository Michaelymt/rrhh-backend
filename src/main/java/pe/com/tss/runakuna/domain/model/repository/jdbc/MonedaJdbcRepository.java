package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.dto.MonedaDto;

@Repository
public class MonedaJdbcRepository implements MonedaRepository{

	private static final Logger LOGGER = LoggerFactory.getLogger(MonedaJdbcRepository.class);
			
	private final NamedParameterJdbcTemplate jdbcTemplate;
	
	private static final String SEARCH_ALL_MONEDAS = "select * from Moneda";
	
	@Autowired
	MonedaJdbcRepository(NamedParameterJdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public List<MonedaDto> findMoneda() {
		List<MonedaDto> searchResults = jdbcTemplate.query(SEARCH_ALL_MONEDAS,
                new BeanPropertyRowMapper<>(MonedaDto.class)
        );
        LOGGER.info("Found {} monedas", searchResults.size());

        return searchResults;
	}

}
