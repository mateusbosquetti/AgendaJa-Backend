package com.mateusbosquetti.agendaja.model.dto.request.establishment;

import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;

public record EstablishmentPUTRequestDTO(
        String name,
        String cnpj,
        AddressRequestDTO address
) {
}
