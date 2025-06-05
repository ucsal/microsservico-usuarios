package com.sistema.user_control_service.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AdministradorRequestDTO(
        @NotBlank(message = "O username é obrigatório")
        String username,

        @Email(message = "Email inválido")
        @NotBlank(message = "O email é obrigatório")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha
) {}

