package com.mateusbosquetti.agendaja.model.dto.response.userEstablishment;

import com.mateusbosquetti.agendaja.model.enums.FunctionRole;

public record UserInEstablishmentResponseDTO(
        Long userId,
        String userName,
        FunctionRole role
) {
}
