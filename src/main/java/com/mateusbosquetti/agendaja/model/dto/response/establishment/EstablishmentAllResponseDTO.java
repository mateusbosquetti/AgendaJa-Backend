package com.mateusbosquetti.agendaja.model.dto.response.establishment;

import com.mateusbosquetti.agendaja.model.entity.Address;

public record EstablishmentAllResponseDTO(
        Long id,
        String name,
        String cnpj,
        Address address
) {
}
