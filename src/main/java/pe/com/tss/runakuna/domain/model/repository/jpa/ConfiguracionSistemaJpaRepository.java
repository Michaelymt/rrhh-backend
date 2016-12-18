package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.ConfiguracionSistema;

@Repository
public interface ConfiguracionSistemaJpaRepository extends CrudRepository<ConfiguracionSistema, Long>, JpaRepository<ConfiguracionSistema, Long> {

	@Query("SELECT d FROM ConfiguracionSistema d WHERE d.codigo =:codigo")
	ConfiguracionSistema obtenerConfiguracionSistemaPorCodigo(@Param("codigo") String codigo);
	
}
