package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.DepartamentoArea;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;
import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;

@Repository
public interface ProyectoJpaRepository extends CrudRepository<Proyecto, Long>, JpaRepository<Proyecto, Long>{
	
	@Query("SELECT d FROM Proyecto d WHERE d.departamentoArea.idDepartamentoArea =:idDepartamentoArea")
	List<Proyecto> findByProyPorDepartamentoArea(@Param("idDepartamentoArea") Long idDepartamentoArea);
	
	@Query("SELECT d FROM DepartamentoArea d WHERE d.idDepartamentoArea =:idDepartamentoArea")
	DepartamentoArea findByCodigo(@Param("idDepartamentoArea") Long idDepartamentoArea);
	
	@Query("SELECT d FROM Proyecto d WHERE d.idProyecto =:idProyecto")
	Proyecto findByIdProyecto(@Param("idProyecto") Long idProyecto);

}
