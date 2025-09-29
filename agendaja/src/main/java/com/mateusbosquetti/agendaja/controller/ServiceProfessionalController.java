package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.ServiceProfessionalRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceProfessionalResponseDTO;
import com.mateusbosquetti.agendaja.service.ServiceProfessionalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/service-professionals")
public class ServiceProfessionalController {

    private final ServiceProfessionalService service;

    @PostMapping
    public ResponseEntity<ServiceProfessionalResponseDTO> createServiceProfessional(
            @RequestBody ServiceProfessionalRequestDTO requestDTO
    ) {
        return new ResponseEntity<>(service.createServiceProfessional(requestDTO), HttpStatus.CREATED);
    }

}
