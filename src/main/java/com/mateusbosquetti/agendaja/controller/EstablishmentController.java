package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.UserResponseDTO;
import com.mateusbosquetti.agendaja.service.EstablishmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/establishments")
public class EstablishmentController {

    private final EstablishmentService service;

    @GetMapping
    public ResponseEntity<Page<EstablishmentResponseDTO>> getEstablishments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return ResponseEntity.ok(service.getEstablishments(page, size));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponseDTO> getEstablishmentById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getEstablishmentById(id));
    }

    @GetMapping("/{id}/employees")
    public ResponseEntity<List<UserResponseDTO>> getEmployeesByEstablishment(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getEmployeesByEstablishment(id));
    }

    @PostMapping()
    public ResponseEntity<EstablishmentResponseDTO> createEstablishment(
            @RequestBody EstablishmentRequestDTO requestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEstablishment(requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableEstablishment(
            @PathVariable Long id
    ) {
        service.disableEstablishment(id);
        return ResponseEntity.noContent().build();
    }

}
