package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.HistorialLaboral;

@Repository
public interface HistorialLaboralJpaRepository extends CrudRepository<HistorialLaboral, Long>, JpaRepository<HistorialLaboral, Long>{

	@Query("SELECT e FROM HistorialLaboral e WHERE e.empleado.idEmpleado = :idEmpleado")
	HistorialLaboral findByIdEmpleado(@Param("idEmpleado") Long idEmpleado);
	
}
