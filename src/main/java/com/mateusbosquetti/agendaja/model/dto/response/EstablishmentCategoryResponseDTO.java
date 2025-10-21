package com.mateusbosquetti.agendaja.model.dto.response;

import com.mateusbosquetti.agendaja.model.entity.Category;

public record EstablishmentCategoryResponseDTO(
        EstablishmentResponseDTO establishment,
        CategoryResponseDTO category
) {
}
