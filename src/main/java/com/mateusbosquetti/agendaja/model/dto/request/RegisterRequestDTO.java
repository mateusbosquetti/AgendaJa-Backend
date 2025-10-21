package com.mateusbosquetti.agendaja.model.dto.request;

public record RegisterRequestDTO(
        String name,
        String email,
        String password,
        //TODO: Opcional
        String cpf,
        //TODO: Opcional
        String phone
) {
}
