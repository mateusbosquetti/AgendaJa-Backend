package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.AppointmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.AppointmentResponseDTO;
import com.mateusbosquetti.agendaja.service.AppointmentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentService service;

    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(
            @RequestBody AppointmentRequestDTO appointmentRequestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createAppointment(appointmentRequestDTO));
    }

    @GetMapping("/user/{clientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByClient(
            @PathVariable Long clientId
    ) {
        return ResponseEntity.ok(service.getAppointmentsByClient(clientId));
    }

    @GetMapping("/establishment/{establishmentId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByEstablishment(
            @PathVariable Long establishmentId
    ) {
        return ResponseEntity.ok(service.getAppointmentsByEstablishment(establishmentId));
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByProfessional(
            @PathVariable Long professionalId
    ) {
        return ResponseEntity.ok(service.getAppointmentsByProfessional(professionalId));
    }

}
