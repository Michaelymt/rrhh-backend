package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.DepartamentoAreaDto;
import pe.com.tss.runakuna.dto.DepartamentoDto;
import pe.com.tss.runakuna.dto.ProyectoDto;

public interface DepartamentoService {

	List<DepartamentoDto> obtenerDepartamentosPorPais(String codigoPais);
	
	List<DepartamentoAreaDto> obtenerDepaAreaPorUndNegocio(Long idUnidadDeNegocio);
	
	List<ProyectoDto> obtenerProyPorDepaArea(Long idDepartamentoArea);
	
	List<CargoDto> obtenerCargoPorProy(Long idProyecto);
	
	List<CargoDto> getListCargos();
	
	List<ProyectoDto> obtenerProyectos();
	
}
