package com.mateusbosquetti.agendaja.model.dto.request;

import com.mateusbosquetti.agendaja.model.enums.FunctionRole;

public record UserEstablishmentRequestDTO(
        Long establishmentId,
        Long userId,
        FunctionRole role
) {
}
