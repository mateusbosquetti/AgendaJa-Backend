package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;

public class ServiceMapper {

    public static ServiceEntity toEntity(
            //DTO
    ) {
        return ServiceEntity.builder().build();
    }

    public static ServiceResponseDTO toDTO(
            ServiceEntity serviceEntity
    ) {
        return new ServiceResponseDTO(
                serviceEntity.getId(),
                serviceEntity.getName(),
                serviceEntity.getDescription(),
                serviceEntity.getDurationMinutes(),
                serviceEntity.getPrice(),
                serviceEntity.getEstablishment().getId()
        );
    }

}
