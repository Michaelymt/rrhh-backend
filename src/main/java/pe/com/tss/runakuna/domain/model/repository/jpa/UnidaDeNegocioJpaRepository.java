package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.UnidadDeNegocio;

@Repository
public interface UnidaDeNegocioJpaRepository extends CrudRepository<UnidadDeNegocio, Long>, JpaRepository<UnidadDeNegocio, Long>{

}
