package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.ProvinciaDto;

public interface ProvinciaService {

	List<ProvinciaDto> obtenerProvinciasPorDepartamento(String codigoDepartamento);
}
