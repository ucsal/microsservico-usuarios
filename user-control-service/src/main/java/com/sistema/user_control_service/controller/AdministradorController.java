package com.sistema.user_control_service.controller;


import com.sistema.user_control_service.dto.request.AdministradorRequestDTO;
import com.sistema.user_control_service.dto.response.AdministradorResponseDTO;
import com.sistema.user_control_service.dto.response.UsuarioAuthResponseDTO;
import com.sistema.user_control_service.service.AdministradorService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/administradores")
public class AdministradorController {

    private final AdministradorService service;

    public AdministradorController(AdministradorService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AdministradorResponseDTO> criar(@RequestBody @Valid AdministradorRequestDTO dto) {
        return ResponseEntity.ok(service.salvar(dto));
    }

    @GetMapping
    public List<AdministradorResponseDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AdministradorResponseDTO> atualizar(
            @PathVariable Long id,
            @RequestBody AdministradorRequestDTO dto) {
        AdministradorResponseDTO atualizado = service.atualizar(id, dto);
        return ResponseEntity.ok(atualizado);
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
