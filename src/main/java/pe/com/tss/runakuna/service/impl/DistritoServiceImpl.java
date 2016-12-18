package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Distrito;
import pe.com.tss.runakuna.domain.model.entities.Provincia;
import pe.com.tss.runakuna.domain.model.repository.jpa.DistritoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProvinciaJpaRepository;
import pe.com.tss.runakuna.dto.DistritoDto;
import pe.com.tss.runakuna.service.DistritoService;

@Service
public class DistritoServiceImpl implements DistritoService{

	@Autowired
    Mapper mapper;
	
	@Autowired
	ProvinciaJpaRepository provinciaJpaRepository;
	

	@Autowired
	DistritoJpaRepository distritoJpaRepository;
	
	

	@Override
	public List<DistritoDto> obtenerDistritosPorProvincia(String codigoProvincia) {
		
		Provincia provincia = provinciaJpaRepository.findByCodigo(codigoProvincia);
		
		List<Distrito> entities;
		List<DistritoDto> items;
		
		entities = distritoJpaRepository.findByProvincia(provincia.getIdProvincia());
		
		items = entities.stream().map(m -> mapper.map(m, DistritoDto.class)).collect(toList());
		
		return items;
	}



}
