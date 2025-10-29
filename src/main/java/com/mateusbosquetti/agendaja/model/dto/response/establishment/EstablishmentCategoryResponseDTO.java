package com.mateusbosquetti.agendaja.model.dto.response.establishment;

import com.mateusbosquetti.agendaja.model.dto.response.category.CategoryResponseDTO;

public record EstablishmentCategoryResponseDTO(
        EstablishmentResponseDTO establishment,
        CategoryResponseDTO category
) {
}
