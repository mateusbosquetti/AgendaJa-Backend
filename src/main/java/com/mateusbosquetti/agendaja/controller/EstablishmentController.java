package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentSummaryDTO;
import com.mateusbosquetti.agendaja.service.EstablishmentService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/establishments")
public class EstablishmentController {

    private final EstablishmentService service;

    //OK
    @PostMapping()
    public ResponseEntity<Void> createEstablishment(
            @RequestBody EstablishmentRequestDTO requestDTO
    ) {
        service.createEstablishment(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //OK
    @GetMapping
    public ResponseEntity<Page<EstablishmentSummaryDTO>> getEstablishments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(required = false) String name
    ) {
        return ResponseEntity.ok(service.getEstablishments(page, size, name));
    }

    //OK
    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponseDTO> getEstablishmentById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getEstablishmentById(id));
    }

    //OK
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateEstablishment(
            @PathVariable Long id,
            @RequestBody EstablishmentPUTRequestDTO requestDTO,
            Authentication authentication
    ) {
        service.updateEstablishment(id, requestDTO, authentication);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableEstablishment(
            @PathVariable Long id,
            Authentication authentication
    ) {
        service.disableEstablishment(id, authentication);
        return ResponseEntity.noContent().build();
    }

}
