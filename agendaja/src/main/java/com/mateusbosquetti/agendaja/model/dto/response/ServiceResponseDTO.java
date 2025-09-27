package com.mateusbosquetti.agendaja.model.dto.response;

import java.math.BigDecimal;

public record ServiceResponseDTO(
        Long id,
        String name,
        String description,
        Integer durationMinutes,
        BigDecimal price,
        Long establishmentId
) {
}
