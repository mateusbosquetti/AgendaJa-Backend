package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.UserEstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.service.UserEstablishmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user-establishments")
public class UserEstablishmentController {

    private final UserEstablishmentService service;

    @PostMapping("/add-user")
    public ResponseEntity<UserEstablishmentResponseDTO> addUserToEstablishment(
            @RequestBody UserEstablishmentRequestDTO requestDTO,
            Authentication authentication
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.linkUserTOEstablishment(authentication, requestDTO));
    }

    @DeleteMapping("/{establishmentId}/disable-professional/{userId}")
    public ResponseEntity<Void> deleteUserAtEstablishment(
            @PathVariable Long establishmentId,
            @PathVariable Long userId,
            Authentication authentication
    ) {
        service.deleteUserAtEstablishment(establishmentId, userId, authentication);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{userId}/establishments")
    public List<UserEstablishmentResponseDTO> getEstablishmentRelationsByUserId(
            @PathVariable Long userId
    ) {
        return service.getUserEstablishmentsByUserId(userId);
    }

    @GetMapping("/{establishmentId}/users")
    public List<UserEstablishmentResponseDTO> getEstablishmentRelationsByEstablishmentId(
            @PathVariable Long establishmentId
    ) {
        return service.getUserEstablishmentsByEstablishmentId(establishmentId);
    }

}
