package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.compositekey.ServiceProfessionalId;
import com.mateusbosquetti.agendaja.model.dto.request.ServiceProfessionalRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceProfessionalResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import com.mateusbosquetti.agendaja.model.entity.ServiceProfessional;
import com.mateusbosquetti.agendaja.model.entity.User;

public class ServiceProfessionalMapper {

    public static ServiceProfessional toEntity(
            ServiceProfessionalRequestDTO requestDTO
    ) {
        return ServiceProfessional.builder()
                .id(
                        ServiceProfessionalId.builder()
                                .professionalId(requestDTO.professionalId())
                                .serviceId(requestDTO.serviceId())
                                .build()
                )
                .professional(User.builder().id(requestDTO.professionalId()).build())
                .service(ServiceEntity.builder().id(requestDTO.serviceId()).build())
                .build();
    }

    public static ServiceProfessionalResponseDTO toDTO(
            ServiceProfessional serviceProfessional
    ) {
        return new ServiceProfessionalResponseDTO(
                ServiceMapper.toDTO(serviceProfessional.getService()),
                UserMapper.toDTO(serviceProfessional.getProfessional())
        );
    }
}