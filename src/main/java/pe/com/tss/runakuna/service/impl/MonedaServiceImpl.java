package pe.com.tss.runakuna.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.repository.jdbc.MonedaRepository;
import pe.com.tss.runakuna.dto.MonedaDto;
import pe.com.tss.runakuna.service.MonedaService;

@Service
public class MonedaServiceImpl implements MonedaService{
	
	final Logger LOG = LoggerFactory.getLogger(getClass());

	@Autowired
	MonedaRepository monedaRepository;

	@Override
	public List<MonedaDto> getMoneda() {
		return monedaRepository.findMoneda();
	}

}
