package com.mateusbosquetti.agendaja.model.dto.response.appointment;

import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;

import java.time.LocalDateTime;

public record AppointmentResponseDTO(
        Long id,
        UserResponseDTO client,
        UserResponseDTO professional,
        ServiceResponseDTO service,
        LocalDateTime time,
        String note
) {
}
