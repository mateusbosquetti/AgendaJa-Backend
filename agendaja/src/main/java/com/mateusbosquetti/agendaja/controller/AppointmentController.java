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
        return new ResponseEntity<>(service.createAppointment(appointmentRequestDTO), HttpStatus.CREATED);
    }

    @GetMapping("/user/{clientId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByClient(
            @PathVariable Long clientId
    ) {
        return new ResponseEntity<>(service.getAppointmentsByClient(clientId), HttpStatus.OK);
    }

    @GetMapping("/establishment/{establishmentId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByEstablishment(
            @PathVariable Long establishmentId
    ) {
        return new ResponseEntity<>(service.getAppointmentsByEstablishment(establishmentId), HttpStatus.OK);
    }

    @GetMapping("/professional/{professionalId}")
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointmentsByProfessional(
            @PathVariable Long professionalId
    ) {
        return new ResponseEntity<>(service.getAppointmentsByProfessional(professionalId), HttpStatus.OK);
    }

}
