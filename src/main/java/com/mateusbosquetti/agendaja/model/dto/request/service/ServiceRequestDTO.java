package com.mateusbosquetti.agendaja.model.dto.request.service;

import java.math.BigDecimal;

public record ServiceRequestDTO(
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price,
        Long establishmentId
) {
}
