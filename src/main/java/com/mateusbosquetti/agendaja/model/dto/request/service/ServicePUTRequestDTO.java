package com.mateusbosquetti.agendaja.model.dto.request.service;

import java.math.BigDecimal;

public record ServicePUTRequestDTO(
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price
) {
}
