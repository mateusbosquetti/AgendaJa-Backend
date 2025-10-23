package com.mateusbosquetti.agendaja.model.dto.response.service;

import com.mateusbosquetti.agendaja.model.dto.response.user.UserResponseDTO;

public record ServiceProfessionalResponseDTO(
        ServiceResponseDTO serviceResponseDTO,
        UserResponseDTO professionalResponseDTO
) {
}
