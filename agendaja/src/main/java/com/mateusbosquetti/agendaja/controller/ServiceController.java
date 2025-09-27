package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.ServiceRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.service.ServiceService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/services")
public class ServiceController {

    private final ServiceService service;

    //TODO: Pagination
    @GetMapping
    public ResponseEntity<List<ServiceResponseDTO>> getServices(
    ) {
        return new ResponseEntity<>(service.getServices(), HttpStatus.OK);
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

    @PostMapping()
    public ResponseEntity<ServiceResponseDTO> createService(
            @RequestBody ServiceRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(service.createService(requestDTO), HttpStatus.CREATED);
    }

}
