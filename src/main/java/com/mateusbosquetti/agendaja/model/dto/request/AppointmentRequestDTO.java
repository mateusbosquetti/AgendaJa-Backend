package com.mateusbosquetti.agendaja.model.dto.request;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        Long clientId,
        Long professionalId,
        Long serviceId,
        LocalDateTime time,
        String note //Opcional
) {
}
