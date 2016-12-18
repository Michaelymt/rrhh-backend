package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.HorarioEmpleadoDia;

@Repository
public interface HorarioEmpleadoDiaJpaRepository extends CrudRepository<HorarioEmpleadoDia, Long>, JpaRepository<HorarioEmpleadoDia, Long>{

	@Query("SELECT h FROM HorarioEmpleadoDia h WHERE h.horarioEmpleado.idHorarioEmpleado =:idHorarioEmpleado AND h.diaSemana=:diaSemana")
	HorarioEmpleadoDia obtenerHorarioDiaPorDiaDeSemana(@Param("idHorarioEmpleado") Long idHorarioEmpleado,@Param("diaSemana") String diaSemana);
	
}
