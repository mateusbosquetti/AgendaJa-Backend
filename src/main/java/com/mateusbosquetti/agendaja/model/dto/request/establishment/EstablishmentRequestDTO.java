package com.mateusbosquetti.agendaja.model.dto.request.establishment;

import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;

public record EstablishmentRequestDTO(
        String name,
        String cnpj,
        Long ownerId,
        AddressRequestDTO address
) {
}
