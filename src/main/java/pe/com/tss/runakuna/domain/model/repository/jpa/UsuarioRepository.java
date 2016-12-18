package pe.com.tss.runakuna.domain.model.repository.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.com.tss.runakuna.domain.model.entities.Usuario;

import java.util.Optional;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    @Query("select u from Usuario u left join fetch u.roles r where u.cuentaUsuario=:username")
    public Optional<Usuario> findByUsername(@Param("username") String username);
}
