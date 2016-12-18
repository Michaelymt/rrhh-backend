package pe.com.tss.runakuna.security;

import pe.com.tss.runakuna.domain.model.entities.Usuario;

import java.util.Optional;

/**
 * Created by josediaz on 23/11/2016.
 */
public interface UserService {
    public Optional<Usuario> getByUsername(String username);
}
