package com.mateusbosquetti.agendaja.model.dto.response.userEstablishment;

import com.mateusbosquetti.agendaja.model.enums.FunctionRole;

public record UserEstablishmentResponseDTO(
        Long establishmentId,
        Long userId,
        String userName,
        FunctionRole role
) {
}
