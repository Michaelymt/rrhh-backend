package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Cargo;
import pe.com.tss.runakuna.domain.model.entities.Proyecto;

@Repository
public interface CargoJpaRepository extends CrudRepository<Proyecto, Long>, JpaRepository<Proyecto, Long>{

	@Query("SELECT d FROM Cargo d WHERE d.departamentoArea.idDepartamentoArea =:idDepartamentoArea")
	List<Cargo> findByCargoPorProyecto(@Param("idDepartamentoArea") Long idDepartamentoArea);
}
