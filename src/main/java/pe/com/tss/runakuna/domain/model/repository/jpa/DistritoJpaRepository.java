package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Distrito;

@Repository
public interface DistritoJpaRepository extends CrudRepository<Distrito, Long>,JpaRepository<Distrito,Long> {
	
	@Query("SELECT d FROM Distrito d WHERE d.provincia.idProvincia =:idProvincia")
    List<Distrito> findByProvincia(@Param("idProvincia") Long idProvincia);
	
}
