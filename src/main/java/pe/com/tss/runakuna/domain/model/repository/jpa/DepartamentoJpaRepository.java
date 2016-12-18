package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Departamento;

@Repository
public interface DepartamentoJpaRepository extends CrudRepository<Departamento, Long>,JpaRepository<Departamento,Long> {
	
	@Query("SELECT d FROM Departamento d WHERE d.pais.idPais =:idPais")
    List<Departamento> findByPais(@Param("idPais") Long idPais);
	
	@Query("SELECT d FROM Departamento d WHERE d.codigo =:codigo")
    Departamento findByCodigo(@Param("codigo") String codigo);
	
}
