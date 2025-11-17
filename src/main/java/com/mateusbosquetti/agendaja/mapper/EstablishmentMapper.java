package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.establishment.EstablishmentRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentAllResponseDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.service.MinioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EstablishmentMapper {

    private static MinioService minioService;

    @Autowired
    public EstablishmentMapper(MinioService minioService) {
        EstablishmentMapper.minioService = minioService;
    }


//    public static Establishment toEntity(
//            EstablishmentRequestDTO requestDTO
//    ) {
//        return Establishment.builder()
//                .name(requestDTO.name())
//                .cnpj(requestDTO.cnpj())
//                .address(AddressMapper.toEntity(requestDTO.address()))
//                .serviceEntities(List.of())
//                .usersRelated(new ArrayList<>())
//                .build();
//    }

//    public static EstablishmentResponseDTO toDTO(
//            Establishment establishment
//    ) {
//        return new EstablishmentResponseDTO(
//                establishment.getId(),
//                establishment.getName(),
//                establishment.getCnpj(),
//                establishment.getAddress(),
//                minioService.getFileUrl(establishment.getLogo().getKey()),
//                establishment.getServiceEntities().stream()
//                        .map(ServiceMapper::toDTO)
//                        .toList(),
//                establishment.getUsersRelated().stream()
//                        .map(UserEstablishmentMapper::toUserInEstablishmentResponseDTO)
//                        .toList()
//
//        );
//    }

//    public static EstablishmentAllResponseDTO toAllDTO(
//            Establishment establishment
//    ) {
//        return new EstablishmentAllResponseDTO(
//                establishment.getId(),
//                establishment.getName(),
//                establishment.getCnpj(),
//                establishment.getAddress()
//        );
//    }

}
