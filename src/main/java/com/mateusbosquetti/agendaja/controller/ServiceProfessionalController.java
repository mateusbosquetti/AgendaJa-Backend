package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.ServiceProfessionalRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceProfessionalResponseDTO;
import com.mateusbosquetti.agendaja.service.ServiceProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/service-professionals")
public class ServiceProfessionalController {

    private final ServiceProfessionalService service;

    @PostMapping
    public ResponseEntity<ServiceProfessionalResponseDTO> associateProfessionalToService(
            @RequestBody ServiceProfessionalRequestDTO requestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.associateProfessionalToService(requestDTO));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<ServiceProfessionalResponseDTO>> getServicesByProfessional(
            @PathVariable Long professionalId
    ) {
        return ResponseEntity.ok(service.getServicesByProfessional(professionalId));
    }

    @DeleteMapping("/service/{serviceId}/professional/{professionalId}")
    public ResponseEntity<Void> removeProfessionalFromService(
            @PathVariable Long serviceId,
            @PathVariable Long professionalId
    ) {
        service.removeProfessionalFromService(serviceId, professionalId);
        return ResponseEntity.noContent().build();
    }

}