package com.mateusbosquetti.agendaja.model.dto.response.establishment;

import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.userEstablishment.UserInEstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;

import java.util.List;

public record EstablishmentResponseDTO(
        Long id,
        String name,
        String cnpj,
        Address address,
        List<ServiceResponseDTO> services,
        List<UserInEstablishmentResponseDTO> usersRelated
) {
}
