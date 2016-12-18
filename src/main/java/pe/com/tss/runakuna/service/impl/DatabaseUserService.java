package pe.com.tss.runakuna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.com.tss.runakuna.domain.model.entities.Usuario;
import pe.com.tss.runakuna.domain.model.repository.jpa.UsuarioRepository;
import pe.com.tss.runakuna.security.UserService;

import java.util.Optional;

/**
 * Created by josediaz on 23/11/2016.
 */
@Service
public class DatabaseUserService implements UserService {
    private final UsuarioRepository userRepository;

    @Autowired
    public DatabaseUserService(UsuarioRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsuarioRepository getUserRepository() {
        return userRepository;
    }

    @Override
    public Optional<Usuario> getByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }
}
