package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.SolicitudCambioMarcacion;

@Repository
public interface SolicitudCambioMarcacionJpaRepository extends CrudRepository<SolicitudCambioMarcacion, Long>, JpaRepository<SolicitudCambioMarcacion, Long>{

	
}
