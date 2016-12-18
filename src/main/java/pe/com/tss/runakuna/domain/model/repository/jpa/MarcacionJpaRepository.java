package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Marcacion;

@Repository
public interface MarcacionJpaRepository extends CrudRepository<Marcacion, Long>, JpaRepository<Marcacion, Long>{

	@Query("SELECT m FROM Marcacion m WHERE m.empleado.idEmpleado =:idEmpleado AND m.fecha =:fecha")
	Marcacion obtenerMarcacionPorIdEmpleadoyDate(@Param("idEmpleado") Long idEmpleado, @Param("fecha") Date fecha);
	
}
