package com.mateusbosquetti.agendaja.model.dto.response.establishment;

import com.mateusbosquetti.agendaja.model.entity.Address;

public record EstablishmentSummaryDTO (
        Long id,
        String name,
        String cnpj,
        Address address,
        String logoUrl
) {
}
