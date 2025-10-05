package com.mateusbosquetti.agendaja.model.dto.request;

public record EstablishmentRequestDTO(
        String name,
        String cnpj,
        Long ownerId,
        AddressRequestDTO address
) {
}
