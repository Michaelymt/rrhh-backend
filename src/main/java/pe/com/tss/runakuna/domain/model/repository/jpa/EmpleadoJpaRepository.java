package pe.com.tss.runakuna.domain.model.repository.jpa;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.com.tss.runakuna.domain.model.entities.Empleado;
import pe.com.tss.runakuna.dto.EmpleadoDto;

@Repository
public interface EmpleadoJpaRepository extends CrudRepository<Empleado, Long>, JpaRepository<Empleado, Long>{

	Empleado save(Empleado empleado);
	
    @Query("SELECT e FROM Empleado e WHERE LOWER(e.codigo) = LOWER(:codigo)")
    Empleado findByCodigoExacto(@Param("codigo") String codigo);
    
    @Query("SELECT e FROM Empleado e WHERE LOWER(e.codigo) = LOWER(:codigo)")
    List<Empleado> BuscarEmpleadoPorCodigo(@Param("codigo") String codigo);

    @Query("SELECT e FROM Empleado e WHERE e.fechaCese = :fechaCese")
	List<Empleado> findFechaCese(@Param("fechaCese") Date fechaCese);

    @Query("SELECT e FROM Empleado e WHERE e.estado = :estado")
    List<Empleado> buscarEmpleadosPorEstado(@Param("estado") String estado);
}
