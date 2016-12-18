package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.MonedaDto;

public interface MonedaRepository {

	List<MonedaDto> findMoneda();
}
