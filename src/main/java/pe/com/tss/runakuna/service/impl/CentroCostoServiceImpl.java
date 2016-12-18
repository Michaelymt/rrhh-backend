package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.CentroCosto;
import pe.com.tss.runakuna.domain.model.repository.jpa.CentroCostoJpaRepository;
import pe.com.tss.runakuna.dto.CentroCostoDto;
import pe.com.tss.runakuna.service.CentroCostoService;

@Service
public class CentroCostoServiceImpl implements CentroCostoService{

	@Autowired
    Mapper mapper;
	
	@Autowired
	CentroCostoJpaRepository centroCostoJpaRepository;	

	@Override
	public List<CentroCostoDto> obtenerCentrosCosto() {
		List<CentroCosto> entities;
		List<CentroCostoDto> items;
		
		entities = centroCostoJpaRepository.findAll();
		
		items = entities.stream().map(m -> mapper.map(m, CentroCostoDto.class)).collect(toList());
		
		return items;
	}



}
