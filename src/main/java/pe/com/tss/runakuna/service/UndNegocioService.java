package pe.com.tss.runakuna.service;

import java.util.List;

import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.UndNegocioDto;

public interface UndNegocioService {
	
	List<UndNegocioDto> getUndNegocio();
	
	List<CargoDto> getListCargos();

}
