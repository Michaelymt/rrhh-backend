package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.CentroCosto;

@Repository
public interface CentroCostoJpaRepository extends CrudRepository<CentroCosto, Long>,JpaRepository<CentroCosto,Long> {

    @Query("SELECT c FROM CentroCosto c WHERE LOWER(c.nombre) = LOWER(:nombre)")
    CentroCosto findByNombreExacto(@Param("nombre") String nombre);
}
