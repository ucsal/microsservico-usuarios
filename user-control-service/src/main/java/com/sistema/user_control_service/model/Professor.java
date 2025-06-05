package com.sistema.user_control_service.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Entity
@Table(name = "professores")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Professor extends Usuario {


    @Column(nullable = false)
    private String escola;


    public Professor(Long id, String username, String email, String senha, Set<Role> role, boolean ativo) {
        super(id, username, email, senha, role, ativo);
    }
}
