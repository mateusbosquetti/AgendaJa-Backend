package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;

public class AddressMapper {

    public static Address toEntity(
            AddressRequestDTO requestDTO
    ) {
        System.out.printf("Mapping AddressRequestDTO to Address entity: %s%n", requestDTO);
        return Address.builder()
                .number(requestDTO.number())
                .street(requestDTO.street())
                .city(requestDTO.city())
                .stateProvince(requestDTO.stateProvince())
                .countryCode(requestDTO.countryCode())
                .postalCode(requestDTO.postalCode())
                .latitude(requestDTO.latitude())
                .longitude(requestDTO.longitude())
                .build();
    }

//    public static AddressResponseDTO toDTO(
//            Address address
//    ) {
//        return new AddressResponseDTO(
//                address.getId(),
//                address.getName(),
//                address.getCnpj(),
//                address.getAddress(),
//                address.getServices().stream()
//                        .map(ServiceMapper::toDTO)
//                        .toList()
//        );
//    }

}
