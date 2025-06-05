package com.sistema.user_control_service.service;

import com.sistema.user_control_service.dto.request.AdministradorRequestDTO;
import com.sistema.user_control_service.dto.response.AdministradorResponseDTO;

import java.util.List;

public interface IAdministradorService {
    AdministradorResponseDTO salvar(AdministradorRequestDTO dto);
    List<AdministradorResponseDTO> listarTodos();
    AdministradorResponseDTO buscarPorId(Long id);
    AdministradorResponseDTO atualizar(Long id, AdministradorRequestDTO dto);
    void deletar(Long id);
}
