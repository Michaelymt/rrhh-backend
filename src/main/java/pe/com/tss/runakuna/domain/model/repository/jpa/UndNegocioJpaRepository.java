package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;

@Repository
public interface UndNegocioJpaRepository extends CrudRepository<UnidadDeNegocio, Long>,JpaRepository<UnidadDeNegocio, Long> {
	
	@Query("SELECT d FROM UnidadDeNegocio d WHERE d.idUnidadDeNegocio =:idUnidadDeNegocio")
	UnidadDeNegocio findByCodigo(@Param("idUnidadDeNegocio") Long idUnidadDeNegocio);
}
