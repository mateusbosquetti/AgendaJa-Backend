package com.mateusbosquetti.agendaja.model.dto.request.appointment;

import java.time.LocalDateTime;

public record AppointmentRequestDTO(
        Long clientId,
        Long professionalId,
        Long serviceId,
        LocalDateTime time,
        String note //TODO: Opcional
) {
}
