package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Service;

public class ServiceMapper {

    public static Service toEntity(
            //DTO
    ) {
        return Service.builder().build();
    }

    public static ServiceResponseDTO toDTO(
            Service service
    ) {
        return new ServiceResponseDTO(
                service.getId(),
                service.getName(),
                service.getDescription(),
                service.getDurationMinutes(),
                service.getPrice(),
                service.getEstablishment().getId()
        );
    }

}
