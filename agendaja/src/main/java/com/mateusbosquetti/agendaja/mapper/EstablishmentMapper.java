package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;

import java.util.List;

public class EstablishmentMapper {

    public static Establishment toEntity(
            EstablishmentRequestDTO requestDTO
    ) {
        return Establishment.builder()
                .name(requestDTO.name())
                .cnpj(requestDTO.cnpj())
                .address(AddressMapper.toEntity(requestDTO.address()))
                .services(List.of())
                .build();
    }

    public static EstablishmentResponseDTO toDTO(
            Establishment establishment
    ) {
        return new EstablishmentResponseDTO(
                establishment.getId(),
                establishment.getName(),
                establishment.getCnpj(),
                establishment.getAddress(),
                establishment.getServices().stream()
                        .map(ServiceMapper::toDTO)
                        .toList()
        );
    }

}
