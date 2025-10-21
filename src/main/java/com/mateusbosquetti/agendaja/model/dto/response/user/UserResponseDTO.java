package com.mateusbosquetti.agendaja.model.dto.response.user;

public record UserResponseDTO(
        Long id,
        String name,
        String phone,
        String cpf
) {
}
