package com.sistema.user_control_service.controller;

import com.sistema.user_control_service.dto.request.ProfessorRequestDTO;
import com.sistema.user_control_service.dto.response.ProfessorResponseDTO;
import com.sistema.user_control_service.dto.response.UsuarioAuthResponseDTO;
import com.sistema.user_control_service.service.ProfessorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/professores")
public class ProfessorController {

    private final ProfessorService service;

    public ProfessorController(ProfessorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<ProfessorResponseDTO> criar(@RequestBody @Valid ProfessorRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public List<ProfessorResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfessorResponseDTO> atualizar(@PathVariable Long id, @RequestBody ProfessorRequestDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<ProfessorResponseDTO> buscarPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.buscarPorUsername(username));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/internal/auth/{username}")
    public ResponseEntity<UsuarioAuthResponseDTO> buscarAuthPorUsername(@PathVariable String username) {
        return ResponseEntity.ok(service.buscarAuthPorUsername(username));
    }
}
