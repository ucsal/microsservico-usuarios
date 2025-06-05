package com.sistema.user_control_service.dto.response;

public record ProfessorResponseDTO(
        Long id,
        String username,
        String email,
        String escola
) {}
