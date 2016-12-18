package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Provincia;

@Repository
public interface ProvinciaJpaRepository extends CrudRepository<Provincia, Long>,JpaRepository<Provincia,Long> {
	
	@Query("SELECT p FROM Provincia p WHERE p.departamento.idDepartamento =:idDepartamento")
    List<Provincia> findByDepartamento(@Param("idDepartamento") Long idDepartamento);
	
	@Query("SELECT p FROM Provincia p WHERE p.codigo =:codigo")
	Provincia findByCodigo(@Param("codigo") String codigo);
	
}
