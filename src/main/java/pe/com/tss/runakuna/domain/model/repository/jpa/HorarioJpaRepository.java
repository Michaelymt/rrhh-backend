package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Horario;

@Repository
public interface HorarioJpaRepository extends CrudRepository<Horario, Long>, JpaRepository<Horario, Long>{

	 @Query("SELECT e FROM Horario e WHERE LOWER(e.nombre) = LOWER(:nombre)")
	List<Horario> validarHorarioPorNombre(@Param("nombre") String nombre);
	 
	 @Modifying
	 @Query("update Horario e set e.porDefecto = 0 where e.empresa.idEmpresa = :idEmpresa and e.proyecto.idProyecto is null")
	 Integer updatePorDefectoPorEmpresa(@Param("idEmpresa") Long idEmpresa);
	 
	 @Modifying
	 @Query("update Horario e set e.porDefecto = 0 where e.empresa.idEmpresa = :idEmpresa and e.proyecto.idProyecto=:idProyecto")
	 Integer updatePorDefectoPorProyecto(@Param("idEmpresa") Long idEmpresa, @Param("idProyecto") Long idProyecto);
	
}
