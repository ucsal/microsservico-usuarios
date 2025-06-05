package com.sistema.user_control_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "administradores")
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Administrador extends Usuario {

    // Construtor completo manual, chamando super
    public Administrador(Long id, String username, String email, String senha, Set<Role> role, boolean ativo) {
        super(id, username, email, senha, role, ativo);
    }
}


