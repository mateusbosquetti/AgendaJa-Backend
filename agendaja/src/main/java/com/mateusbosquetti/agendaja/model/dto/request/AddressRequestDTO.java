package com.mateusbosquetti.agendaja.model.dto.request;

import java.math.BigDecimal;

public record AddressRequestDTO(
        Long number,
        String street,
        String city,
        String stateProvince,
        String countryCode,
        String postalCode,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
