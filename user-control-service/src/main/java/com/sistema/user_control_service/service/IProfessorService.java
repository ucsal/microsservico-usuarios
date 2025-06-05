package com.sistema.user_control_service.service;

import com.sistema.user_control_service.dto.request.ProfessorRequestDTO;
import com.sistema.user_control_service.dto.response.ProfessorResponseDTO;

import java.util.List;

public interface IProfessorService {
    ProfessorResponseDTO salvar(ProfessorRequestDTO dto);
    List<ProfessorResponseDTO> listarTodos();
    ProfessorResponseDTO buscarPorId(Long id);
    ProfessorResponseDTO atualizar(Long id, ProfessorRequestDTO dto);
    void deletar(Long id);
}
