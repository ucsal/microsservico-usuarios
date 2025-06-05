package com.sistema.user_control_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ProfessorRequestDTO(
        @NotBlank(message = "O username é obrigatório")
        String username,

        @NotBlank(message = "O email é obrigatório")
        @jakarta.validation.constraints.Email(message = "Email inválido")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotBlank(message = "A escola é obrigatória")
        String escola
) {}

