package com.sistema.user_control_service.service;

import com.sistema.user_control_service.Utils.EncryptionUtils;
import com.sistema.user_control_service.dto.request.AdministradorRequestDTO;
import com.sistema.user_control_service.dto.response.AdministradorResponseDTO;
import com.sistema.user_control_service.dto.response.UsuarioAuthResponseDTO;
import com.sistema.user_control_service.model.Administrador;
import com.sistema.user_control_service.model.Role;
import com.sistema.user_control_service.repository.AdministradorRepository;
import com.sistema.user_control_service.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AdministradorService implements IAdministradorService {

    private final AdministradorRepository administradorRepository;
    private final RoleRepository roleRepository;

    public AdministradorService(AdministradorRepository administradorRepository, RoleRepository roleRepository) {
        this.administradorRepository = administradorRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public AdministradorResponseDTO salvar(AdministradorRequestDTO dto) {
        Administrador admin = new Administrador();
        admin.setUsername(dto.username());
        admin.setEmail(dto.email());

        // Criptografando a senha
        String hashedPassword = EncryptionUtils.hashPassword(dto.senha());
        admin.setSenha(hashedPassword);

        Role roleAdmin = roleRepository.findByNome("ROLE_ADMIN")
                .orElseThrow(() -> new RuntimeException("Role ROLE_ADMIN não encontrada"));
        admin.getRoles().add(roleAdmin);

        return toResponseDTO(administradorRepository.save(admin));
    }

    @Override
    public List<AdministradorResponseDTO> listarTodos() {
        return administradorRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AdministradorResponseDTO buscarPorId(Long id) {
        Administrador admin = administradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Administrador não encontrado"));
        return toResponseDTO(admin);
    }

    @Override
    public AdministradorResponseDTO atualizar(Long id, AdministradorRequestDTO dto) {
        Administrador admin = administradorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Administrador não encontrado"));

        admin.setUsername(dto.username());
        admin.setEmail(dto.email());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            if (EncryptionUtils.checkPassword(dto.senha(), admin.getSenha())) {
                throw new IllegalArgumentException("A nova senha não pode ser igual à anterior.");
            }
            admin.setSenha(EncryptionUtils.hashPassword(dto.senha()));
        }
        return toResponseDTO(administradorRepository.save(admin));
    }

    @Override
    public void deletar(Long id) {
        administradorRepository.deleteById(id);
    }

    private AdministradorResponseDTO toResponseDTO(Administrador admin) {
        return new AdministradorResponseDTO(
                admin.getId(),
                admin.getUsername(),
                admin.getEmail()
        );
    }

    public UsuarioAuthResponseDTO buscarAuthPorUsername(String username) {
        Administrador admin = administradorRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Administrador não encontrado com username: " + username));

        Set<String> roles = admin.getRoles()
                .stream()
                .map(Role::getNome)
                .collect(Collectors.toSet());

        return new UsuarioAuthResponseDTO(admin.getUsername(), admin.getSenha(), roles);
    }
}
