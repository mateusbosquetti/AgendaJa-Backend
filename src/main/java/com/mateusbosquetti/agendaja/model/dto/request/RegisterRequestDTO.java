package com.mateusbosquetti.agendaja.model.dto.request;

public record RegisterRequestDTO(
        String name, String email, String password, String cpf, String phone
) {
}
