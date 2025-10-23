package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentAllResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.service.EstablishmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/establishments")
public class EstablishmentController {

    private final EstablishmentService service;

    @PostMapping()
    public ResponseEntity<EstablishmentResponseDTO> createEstablishment(
            @RequestBody EstablishmentRequestDTO requestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEstablishment(requestDTO));
    }

    @GetMapping
    public ResponseEntity<Page<EstablishmentAllResponseDTO>> getEstablishments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(service.getEstablishments(page, size, name));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponseDTO> getEstablishmentById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getEstablishmentById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstablishmentResponseDTO> updateEstablishment(
            @PathVariable Long id,
            @RequestBody EstablishmentPUTRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(service.updateEstablishment(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableEstablishment(
            @PathVariable Long id
    ) {
        service.disableEstablishment(id);
        return ResponseEntity.noContent().build();
    }

}
