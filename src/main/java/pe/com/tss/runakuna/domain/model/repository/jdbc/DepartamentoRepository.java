package pe.com.tss.runakuna.domain.model.repository.jdbc;

import java.util.List;

import pe.com.tss.runakuna.dto.DepartamentoDto;

public interface DepartamentoRepository {

	List<DepartamentoDto> findDepartamentos();
}
