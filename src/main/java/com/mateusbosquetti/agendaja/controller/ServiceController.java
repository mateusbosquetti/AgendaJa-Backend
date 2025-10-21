package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.service.ServicePUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.service.ServiceRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService service;

    @GetMapping
    public ResponseEntity<Page<ServiceResponseDTO>> getServices(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return new ResponseEntity<>(service.getServices(page, size), HttpStatus.OK);
    }

    @GetMapping("/establishment/{establishmentId}")
    public ResponseEntity<List<ServiceResponseDTO>> getServicesByEstablishment(
            @PathVariable Long establishmentId
    ) {
        return ResponseEntity.ok(service.getServicesByEstablishment(establishmentId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getServiceById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getServiceById(id));
    }

    @PostMapping()
    public ResponseEntity<ServiceResponseDTO> createService(
            @RequestBody ServiceRequestDTO requestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createService(requestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> updateService(
            @PathVariable Long id,
            @RequestBody ServicePUTRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(service.updateService(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableService(
            @PathVariable Long id
    ) {
        service.disableService(id);
        return ResponseEntity.noContent().build();
    }

}