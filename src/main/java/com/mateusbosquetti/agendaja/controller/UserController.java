package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserResponseDTO;
import com.mateusbosquetti.agendaja.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    @GetMapping("/me")
    public ResponseEntity<UserResponseDTO> getCurrentUser(Authentication authentication) {
        String email = authentication.getName();
        return ResponseEntity.ok(service.getByEmail(email));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getUserById(id));
    }

    @GetMapping("/{id}/establishments")
    public List<UserEstablishmentResponseDTO> getEstablishmentRelationsByUserId(
            @PathVariable Long id
    ) {
        return service.getEstablishmentsByUserId(id);
    }

}
