package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.appointment.AppointmentPUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.appointment.AppointmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.appointment.AppointmentResponseDTO;
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

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(service.getAppointmentById(id));
    }

    @GetMapping("/client/{clientId}")
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

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(
            @PathVariable Long id,
            @RequestBody AppointmentPUTRequestDTO requestDTO
    ) {
        return ResponseEntity.ok(service.updateAppointment(id, requestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> disableAppointment(
            @PathVariable Long id
    ) {
        service.disableAppointment(id);
        return ResponseEntity.noContent().build();
    }

}
