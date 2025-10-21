package com.mateusbosquetti.agendaja.model.dto.request.appointment;

import java.time.LocalDateTime;

public record AppointmentPUTRequestDTO(
        Long professionalId,
        Long serviceId,
        LocalDateTime time,
        String note
) {
}
