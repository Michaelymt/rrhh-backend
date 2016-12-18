package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleado;

@Repository
public interface HorarioEmpleadoJpaRepository extends CrudRepository<HorarioEmpleado, Long>, JpaRepository<HorarioEmpleado, Long>{

	@Query("SELECT e FROM HorarioEmpleado e WHERE e.empleado.idEmpleado = :idEmpleado")
	HorarioEmpleado findByIdEmpleado(@Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.finVigencia = :finVigencia")
	HorarioEmpleado findByFechaFin(@Param("finVigencia") Date finVigencia);
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE e.inicioVigencia = :inicioVigencia AND e.empleado.idEmpleado = :idEmpleado")
	HorarioEmpleado findByFechaInicio(@Param("inicioVigencia") Date inicioVigencia,@Param("idEmpleado") Long idEmpleado);
	
	
	@Query("SELECT e FROM HorarioEmpleado e WHERE ((e.inicioVigencia<=:fechaVigente AND e.finVigencia IS NULL) OR (e.inicioVigencia<=:fechaVigente AND e.finVigencia IS NOT NULL AND e.finVigencia>=:fechaVigente)) AND e.empleado.idEmpleado = :idEmpleado")
	HorarioEmpleado obtenerHorarioPorFechaVigente(@Param("fechaVigente") Date fechaVigente, @Param("idEmpleado") Long idEmpleado);
}
