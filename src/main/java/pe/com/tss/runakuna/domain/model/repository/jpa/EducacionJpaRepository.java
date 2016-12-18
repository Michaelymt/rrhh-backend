package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Educacion;

@Repository
public interface EducacionJpaRepository extends CrudRepository<Educacion, Long>, JpaRepository<Educacion, Long>{

}
