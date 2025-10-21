package com.mateusbosquetti.agendaja.model.dto.response;

import com.mateusbosquetti.agendaja.model.dto.response.user.UserMeResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;

import java.util.List;

public record EstablishmentAllResponseDTO(
        Long id,
        String name,
        String cnpj,
        Address address,
        List<UserMeResponseDTO> users,
        List<ServiceResponseDTO> services
) {
}
