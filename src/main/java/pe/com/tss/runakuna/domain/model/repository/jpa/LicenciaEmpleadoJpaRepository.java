package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Licencia;

@Repository
public interface LicenciaEmpleadoJpaRepository extends CrudRepository<Licencia, Long>, JpaRepository<Licencia, Long>{

	@Query("SELECT l FROM Licencia l WHERE (l.fechaInicio <= :fecha AND l.fechaFin >= :fecha) AND l.empleado.idEmpleado = :idEmpleado")
	Licencia obtenerLicencia(@Param("fecha") Date fecha, @Param("idEmpleado") Long idEmpleado);
	
}
