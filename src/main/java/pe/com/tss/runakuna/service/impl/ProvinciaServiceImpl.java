package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Departamento;
import pe.com.tss.runakuna.domain.model.entities.Provincia;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProvinciaJpaRepository;
import pe.com.tss.runakuna.dto.ProvinciaDto;
import pe.com.tss.runakuna.service.ProvinciaService;

@Service
public class ProvinciaServiceImpl implements ProvinciaService{

	@Autowired
    Mapper mapper;
	
	@Autowired
	DepartamentoJpaRepository departamentoJpaRepository;
	
	@Autowired
	ProvinciaJpaRepository provinciaJpaRepository;
	
	@Override
	public List<ProvinciaDto> obtenerProvinciasPorDepartamento(String codigoDepartamento) {
		Departamento dpto = departamentoJpaRepository.findByCodigo(codigoDepartamento);
		
		List<Provincia> entities;
		List<ProvinciaDto> items;
		
		entities = provinciaJpaRepository.findByDepartamento(dpto.getIdDepartamento());
		
		items = entities.stream().map(m -> mapper.map(m, ProvinciaDto.class)).collect(toList());
		
		return items;
	
	}



}
