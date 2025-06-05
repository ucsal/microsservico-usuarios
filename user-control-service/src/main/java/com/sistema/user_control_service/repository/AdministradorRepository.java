package com.sistema.user_control_service.repository;


import com.sistema.user_control_service.model.Administrador;
import com.sistema.user_control_service.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long> {
    Optional<Administrador> findByUsername(String username);
}
