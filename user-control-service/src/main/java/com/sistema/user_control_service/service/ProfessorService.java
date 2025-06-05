package com.sistema.user_control_service.service;

import com.sistema.user_control_service.Utils.EncryptionUtils;
import com.sistema.user_control_service.dto.request.ProfessorRequestDTO;
import com.sistema.user_control_service.dto.response.ProfessorResponseDTO;
import com.sistema.user_control_service.dto.response.UsuarioAuthResponseDTO;
import com.sistema.user_control_service.model.Professor;
import com.sistema.user_control_service.model.Role;
import com.sistema.user_control_service.repository.ProfessorRepository;
import com.sistema.user_control_service.repository.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorService implements IProfessorService {

    private final ProfessorRepository repository;
    private final RoleRepository roleRepository;

    public ProfessorService(ProfessorRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    @Override
    public ProfessorResponseDTO salvar(ProfessorRequestDTO dto) {
        Professor professor = new Professor();
        professor.setUsername(dto.username());
        professor.setEmail(dto.email());
        professor.setEscola(dto.escola());

        String hashedPassword = EncryptionUtils.hashPassword(dto.senha());
        professor.setSenha(hashedPassword);

        // Aqui você associa a role "ROLE_PROFESSOR" no backend
        Role roleProfessor = roleRepository.findByNome("ROLE_PROFESSOR")
                .orElseThrow(() -> new RuntimeException("Role ROLE_PROFESSOR não encontrada"));

        professor.getRoles().add(roleProfessor);

        return toResponseDTO(repository.save(professor));
    }

    @Override
    public List<ProfessorResponseDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProfessorResponseDTO buscarPorId(Long id) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));
        return toResponseDTO(professor);
    }

    @Override
    public ProfessorResponseDTO atualizar(Long id, ProfessorRequestDTO dto) {
        Professor professor = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado"));

        professor.setUsername(dto.username());
        professor.setEmail(dto.email());
        professor.setEscola(dto.escola());

        if (dto.senha() != null && !dto.senha().isBlank()) {
            if (EncryptionUtils.checkPassword(dto.senha(), professor.getSenha())) {
                throw new IllegalArgumentException("A nova senha não pode ser igual à anterior.");
            }
            professor.setSenha(EncryptionUtils.hashPassword(dto.senha()));
        }

        return toResponseDTO(repository.save(professor));
    }

    public ProfessorResponseDTO buscarPorUsername(String username) {
        Professor professor = repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com username: " + username));
        return toResponseDTO(professor);
    }

    @Override
    public void deletar(Long id) {
        repository.deleteById(id);
    }

    private ProfessorResponseDTO toResponseDTO(Professor professor) {
        return new ProfessorResponseDTO(
                professor.getId(),
                professor.getUsername(),
                professor.getEmail(),
                professor.getEscola()
        );
    }

    public UsuarioAuthResponseDTO buscarAuthPorUsername(String username) {
        Professor professor = repository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Professor não encontrado com username: " + username));

        Set<String> roles = professor.getRoles()
                .stream()
                .map(Role::getNome)
                .collect(Collectors.toSet());

        return new UsuarioAuthResponseDTO(professor.getUsername(), professor.getSenha(), roles);
    }
}
