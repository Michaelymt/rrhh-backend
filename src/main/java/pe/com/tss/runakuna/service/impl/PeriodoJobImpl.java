package pe.com.tss.runakuna.service.impl;

import java.util.List;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import pe.com.tss.runakuna.domain.model.entities.ConfiguracionSistema;
import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;
import pe.com.tss.runakuna.domain.model.repository.jpa.ConfiguracionSistemaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.EmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoDiaJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.HorarioEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.MarcacionJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PeriodoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.PermisoEmpleadoJpaRepository;
import pe.com.tss.runakuna.domain.model.repository.jpa.RegistroMarcacionEmpleadoJpaRepository;
import pe.com.tss.runakuna.dto.EmpleadoDto;
import pe.com.tss.runakuna.service.PeriodoEmpleadoService;

@Service
public class PeriodoJobImpl{
	
	@Autowired
	EmpleadoJpaRepository empleadoJpaRepository;
	
	@Autowired
	RegistroMarcacionEmpleadoJpaRepository registroMarcacionEmpleadoJpaRepository;
	
	@Autowired
	MarcacionJpaRepository marcacionJpaRepository;
	
	@Autowired
	HorarioEmpleadoDiaJpaRepository horarioEmpleadoDiaJpaRepository;
	
	@Autowired
	HorarioEmpleadoJpaRepository horarioEmpleadoJpaRepository;
	
	@Autowired
	PermisoEmpleadoJpaRepository permisoEmpleadoJpaRepository;
	
	@Autowired
	ConfiguracionSistemaJpaRepository configuracionSistemaJpaRepository;
	
	@Autowired
	PeriodoEmpleadoJpaRepository periodoEmpleadoJpaRepository;
	
	@Autowired
	Mapper mapper;
	
	@Autowired
	PeriodoEmpleadoService periodoEmpleadoService;

	@Scheduled(cron="0 0/5 * 1/1 * ?")
	public void integrarMarcacionesSistemaAsistencia(){
		ConfiguracionSistema cadenaMaxPermi = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("");
		
		ConfiguracionSistema cadenaMaxVarcacion = configuracionSistemaJpaRepository.obtenerConfiguracionSistemaPorCodigo("'GestionDeTiempo.PermisosPermitidos'");	
		
		
		PeriodoEmpleado periodoEmpleado = new PeriodoEmpleado();
		
		//obtener la lista de empleados vigentes sin periodo futuro
		
	}
	
	@Scheduled(cron="0 59 23 * * *")
	public void crearNuevoPeriodoEmpleado() {
			
		EmpleadoDto emp=null;
		/*emp=new EmpleadoDto();
		emp.setIdEmpleado(new Long(1080));
		periodoEmpleadoService.registrarPeriodoEmpleado(emp);*/
		List<Empleado> empleados=empleadoJpaRepository.buscarEmpleadosPorEstado("A");
		  for(Empleado empleado :empleados) {
			emp=new EmpleadoDto();
			emp.setIdEmpleado(empleado.getIdEmpleado());
			periodoEmpleadoService.registrarPeriodoEmpleado(emp);
		}
		
		
	}
	
	
}
