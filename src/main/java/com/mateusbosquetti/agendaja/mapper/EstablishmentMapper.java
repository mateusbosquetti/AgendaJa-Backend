package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentAllResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;

import java.util.ArrayList;
import java.util.List;

public class EstablishmentMapper {

    public static Establishment toEntity(
            EstablishmentRequestDTO requestDTO
    ) {
        return Establishment.builder()
                .name(requestDTO.name())
                .cnpj(requestDTO.cnpj())
                .address(AddressMapper.toEntity(requestDTO.address()))
                .serviceEntities(List.of())
                .usersRelated(new ArrayList<>())
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
                establishment.getServiceEntities().stream()
                        .map(ServiceMapper::toDTO)
                        .toList()
        );
    }

    public static EstablishmentAllResponseDTO toAllDTO(
            Establishment establishment
    ) {
        return new EstablishmentAllResponseDTO(
                establishment.getId(),
                establishment.getName(),
                establishment.getCnpj(),
                establishment.getAddress(),
                establishment.getUsersRelated().stream().map(ue -> UserMapper.toMeDTO(ue.getUser())).toList(),
                establishment.getServiceEntities().stream()
                        .map(ServiceMapper::toDTO)
                        .toList()
        );
    }

}
