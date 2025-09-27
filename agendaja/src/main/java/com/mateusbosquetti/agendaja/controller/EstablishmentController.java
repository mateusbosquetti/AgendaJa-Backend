package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.service.EstablishmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/establishments")
public class EstablishmentController {

    private final EstablishmentService service;

    @GetMapping("/{id}")
    public ResponseEntity<EstablishmentResponseDTO> getEstablishmentById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(service.getEstablishmentById(id), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<EstablishmentResponseDTO> createEstablishment(
            @RequestBody EstablishmentRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(service.createEstablishment(requestDTO), HttpStatus.CREATED);
    }


}
