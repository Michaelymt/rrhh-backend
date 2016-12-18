package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Pais;

@Repository
public interface PaisJpaRepository extends CrudRepository<Pais, Long>,JpaRepository<Pais,Long> {
	
	@Query("SELECT p FROM Pais p WHERE p.codigo =:codigo")
    Pais findByCodigo(@Param("codigo") String codigo);
	
}
