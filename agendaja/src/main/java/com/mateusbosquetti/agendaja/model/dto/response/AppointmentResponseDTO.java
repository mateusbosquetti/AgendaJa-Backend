package com.mateusbosquetti.agendaja.model.dto.response;

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
