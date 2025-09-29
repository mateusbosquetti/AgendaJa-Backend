package com.mateusbosquetti.agendaja.model.dto.response;

public record ServiceProfessionalResponseDTO(
        ServiceResponseDTO serviceResponseDTO,
        UserResponseDTO professionalResponseDTO
) {
}
