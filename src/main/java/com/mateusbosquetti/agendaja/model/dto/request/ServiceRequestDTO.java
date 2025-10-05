package com.mateusbosquetti.agendaja.model.dto.request;

import java.math.BigDecimal;

public record ServiceRequestDTO(
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price,
        Long establishmentId
) {
}
