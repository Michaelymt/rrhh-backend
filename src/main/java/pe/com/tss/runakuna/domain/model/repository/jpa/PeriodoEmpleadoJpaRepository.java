package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.PeriodoEmpleado;

@Repository
public interface PeriodoEmpleadoJpaRepository extends CrudRepository<PeriodoEmpleado, Long>,JpaRepository<PeriodoEmpleado,Long> {
	
	@Query("SELECT p FROM PeriodoEmpleado p WHERE (p.fechaInicio <= :fecha AND p.fechaFin >= :fecha) AND p.empleado.idEmpleado = :idEmpleado")
	PeriodoEmpleado obtenerPeriodoEmpleadoPorFechayEmpleado(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
	
}
