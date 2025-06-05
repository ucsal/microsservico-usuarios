package com.sistema.user_control_service.dto.response;

import java.util.Set;

public record UsuarioAuthResponseDTO(
        String username,
        String senha,
        Set<String> roles
) {}
