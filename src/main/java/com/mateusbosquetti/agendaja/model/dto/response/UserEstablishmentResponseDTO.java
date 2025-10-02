package com.mateusbosquetti.agendaja.model.dto.response;

import com.mateusbosquetti.agendaja.model.enums.FunctionRole;

public record UserEstablishmentResponseDTO(
        Long establishmentId,
        Long userId,
        FunctionRole role
) {
}
