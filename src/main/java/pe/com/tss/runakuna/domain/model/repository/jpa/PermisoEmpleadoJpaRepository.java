package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.com.tss.runakuna.domain.model.entities.PermisoEmpleado;

/**
 * Created by josediaz on 9/11/2016.
 */
@Repository
public interface PermisoEmpleadoJpaRepository extends CrudRepository<PermisoEmpleado, Long>,JpaRepository<PermisoEmpleado,Long> {

	//@Query("SELECT p FROM PermisoEmpleado p WHERE p.fecha = :fecha AND p.horaInicio=:horaInicio AND p.horaFin=:horaFin AND p.estado != 'R'")
	//List<PermisoEmpleado> buscarPermisoPorFecha(@Param("fecha") Date fecha, @Param("horaInicio") String horaInicio, @Param("horaFin") String horaFin);
	
	@Query("SELECT p FROM PermisoEmpleado p WHERE p.fecha = :fecha AND p.estado != 'R'")
	List<PermisoEmpleado> buscarPermisoPorFecha(@Param("fecha") Date fecha);
	
	@Query("SELECT p FROM PermisoEmpleado p")
	List<PermisoEmpleado> findByCodigo(@Param("codigo") String codigo);
	
	@Query("SELECT p FROM PermisoEmpleado p WHERE (p.periodoEmpleado.fechaInicio <= :fecha AND p.periodoEmpleado.fechaFin >= :fecha) AND p.fecha = :fecha AND p.periodoEmpleado.empleado.idEmpleado = :idEmpleado")
	PermisoEmpleado obtenerPermisoPorFecha(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
	
	@Query("SELECT p FROM PermisoEmpleado p WHERE p.fecha = :fecha AND p.periodoEmpleado.idPeriodoEmpleado = :idPeriodoEmpleado")
	PermisoEmpleado obtenerPermisoPorFechayPeriodoEmpleado(@Param("fecha") Date fecha, @Param("idPeriodoEmpleado") Long idPeriodoEmpleado);
	
}
