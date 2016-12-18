package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.DistritoDto;

public interface DistritoService {

	List<DistritoDto> obtenerDistritosPorProvincia(String codigoProvincia);
}
