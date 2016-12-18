package pe.com.tss.runakuna.service.impl;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.dozer.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.Cargo;
import pe.com.tss.runakuna.domain.model.entities.Departamento;
import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Pais;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;
import pe.com.tss.runakuna.domain.model.repository.jdbc.UndNegocioRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.CargoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoAreaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.DepartamentoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PaisJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.ProyectoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.UndNegocioJpaRepository;
import pe.com.tss.runakuna.dto.CargoDto;
import pe.com.tss.runakuna.dto.DepartamentoAreaDto;
import pe.com.tss.runakuna.dto.DepartamentoDto;
import pe.com.tss.runakuna.dto.ProyectoDto;
import pe.com.tss.runakuna.service.DepartamentoService;

@Service
public class DepartamentoServiceImpl implements DepartamentoService{

	final Logger LOG = LoggerFactory.getLogger(getClass());
	
	@Autowired
    Mapper mapper;
	
	@Autowired
	UndNegocioJpaRepository undNegocioJpaRepository;
	
	@Autowired
	PaisJpaRepository paisJpaRepository;
	
	@Autowired
	DepartamentoJpaRepository departamentoJpaRepository;
	
	@Autowired
	DepartamentoAreaJpaRepository departamentoAreaJpaRepository;
	
	@Autowired
	ProyectoJpaRepository proyectoJpaRepository;
	
	@Autowired
	CargoJpaRepository cargoJpaRepository;

//	@Override
//	public List<DepartamentoDto> getDepartamentos() {
//		return departamentoRepository.findDepartamentos();
//	}

	@Override
	public List<DepartamentoDto> obtenerDepartamentosPorPais(String codigoPais) {
		Pais pais = paisJpaRepository.findByCodigo(codigoPais);
		
		List<Departamento> entities;
		List<DepartamentoDto> items;
		
		entities = departamentoJpaRepository.findByPais(pais.getIdPais());
		
		items = entities.stream().map(m -> mapper.map(m, DepartamentoDto.class)).collect(toList());
		
		return items;
	
	}

	@Override
	public List<DepartamentoAreaDto> obtenerDepaAreaPorUndNegocio(Long idUnidadDeNegocio) {
		UnidadDeNegocio undNegocio = undNegocioJpaRepository.findByCodigo(idUnidadDeNegocio);
		
		List<DepartamentoArea> entities;
		List<DepartamentoAreaDto> items;
		
		entities = departamentoAreaJpaRepository.findByUndNegocio(undNegocio.getIdUnidadDeNegocio());
		items = entities.stream().map(m -> mapper.map(m, DepartamentoAreaDto.class)).collect(toList());
		return items;
	}

	@Override
	public List<ProyectoDto> obtenerProyPorDepaArea(Long idDepartamentoArea) {
		DepartamentoArea depaArea = departamentoAreaJpaRepository.findByCodigo(idDepartamentoArea);
		
		List<Proyecto> entities;
		List<ProyectoDto> items;
		
		entities = proyectoJpaRepository.findByProyPorDepartamentoArea(depaArea.getIdDepartamentoArea());
		items = entities.stream().map(m -> mapper.map(m, ProyectoDto.class)).collect(toList());
		return items;
	}

	@Override
	public List<CargoDto> obtenerCargoPorProy(Long idProyecto) {
		
		Proyecto proyecto = proyectoJpaRepository.findByIdProyecto(idProyecto);
		
		List<Cargo> entities;
		List<CargoDto> items=null;
		
		entities = cargoJpaRepository.findByCargoPorProyecto(proyecto.getDepartamentoArea().getIdDepartamentoArea());
		items = entities.stream().map(m -> mapper.map(m, CargoDto.class)).collect(toList());
		return items;
	}

	@Override
	public List<CargoDto> getListCargos() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<ProyectoDto> obtenerProyectos() {
		
		
		List<Proyecto> entities;
		List<ProyectoDto> items;
		
		entities = proyectoJpaRepository.findAll();
		
		items = entities.stream().map(m -> mapper.map(m, ProyectoDto.class)).collect(toList());
		return items;
	}

}
