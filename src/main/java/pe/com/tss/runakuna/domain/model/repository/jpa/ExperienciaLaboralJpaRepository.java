package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.ExperienciaLaboral;

@Repository
public interface ExperienciaLaboralJpaRepository extends CrudRepository<ExperienciaLaboral, Long>, JpaRepository<ExperienciaLaboral, Long>{

	@Query("SELECT e FROM ExperienciaLaboral e WHERE e.empleado.idEmpleado =:idEmpleado")
	List<ExperienciaLaboral> buscarExperienciaLaboralPorEmpleado(@Param("idEmpleado") Long idEmpleado);
	
}
