package com.mateusbosquetti.agendaja.model.dto.response.user;

import com.mateusbosquetti.agendaja.model.enums.ThemeEnum;

public record UserResponseDTO(
        Long id,
        String name,
        String phone,
        String cpf,
        ThemeEnum theme,
        String photoKey
) {
}
