package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.DocumentoEmpleado;

@Repository
public interface DocumentoEmpleadoJpaRepository extends CrudRepository<DocumentoEmpleado, Long>, JpaRepository<DocumentoEmpleado, Long>{

	@Query("SELECT d FROM DocumentoEmpleado d WHERE d.empleado.idEmpleado =:idEmpleado")
	List<DocumentoEmpleado> buscarDocumentosPorEmpleado(@Param("idEmpleado") Long idEmpleado);
	
}
