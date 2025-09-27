package com.mateusbosquetti.agendaja.model.dto.response;

import com.mateusbosquetti.agendaja.model.entity.Address;

import java.util.List;

public record EstablishmentResponseDTO(
        Long id,
        String name,
        String cnpj,
        Address address,
        List<ServiceResponseDTO> services
) {
}
