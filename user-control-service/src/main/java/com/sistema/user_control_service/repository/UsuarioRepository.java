package com.sistema.user_control_service.repository;

import com.sistema.user_control_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository<T extends Usuario> extends JpaRepository<T, Long> {
    Optional<T> findByUsername(String username);
    boolean existsByUsername(String username);
}