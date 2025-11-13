package com.mateusbosquetti.agendaja.model.dto.response.establishment;

import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.userEstablishment.UserEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;

import java.util.List;

public record EstablishmentResponseDTO(
        Long id,
        String name,
        String description,
        String cnpj,
        Address address,
        String logoUrl,
        List<ServiceResponseDTO> services,
        List<UserEstablishmentResponseDTO> usersRelated
) {
}
