package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.ServiceProfessionalRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.ServiceRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceProfessionalResponseDTO;
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
        return new ResponseEntity<>(service.getServicesByEstablishment(establishmentId), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceResponseDTO> getServiceById(
            @PathVariable Long id
    ) {
        return new ResponseEntity<>(service.getServiceById(id), HttpStatus.OK);
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<ServiceResponseDTO>> getServicesByProfessional(
            @PathVariable Long professionalId
    ) {
        return new ResponseEntity<>(service.getServicesByProfessional(professionalId), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<ServiceResponseDTO> createService(
            @RequestBody ServiceRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(service.createService(requestDTO), HttpStatus.CREATED);
    }

    @PostMapping("/associate-professional")
    public ResponseEntity<ServiceProfessionalResponseDTO> associateProfessionalToService(
            @RequestBody ServiceProfessionalRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(service.associateProfessionalToService(requestDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableService(
            @PathVariable Long id
    ) {
        service.disableService(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}