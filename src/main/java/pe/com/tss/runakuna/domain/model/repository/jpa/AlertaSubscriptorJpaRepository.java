package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.AlertaSubscriptor;

@Repository
public interface AlertaSubscriptorJpaRepository extends CrudRepository<AlertaSubscriptor, Long>, JpaRepository<AlertaSubscriptor, Long>{

	
}
