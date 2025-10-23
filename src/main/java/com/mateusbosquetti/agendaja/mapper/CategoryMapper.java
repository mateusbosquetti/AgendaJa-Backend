package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.CategoryRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.category.CategoryResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Category;

public class CategoryMapper {

    public static Category toEntity(
            CategoryRequestDTO requestDTO
    ) {
        return Category.builder()
                .name(requestDTO.name())
                .description(requestDTO.description())
                .build();
    }

    public static CategoryResponseDTO toDTO(
            Category category
    ) {
        return new CategoryResponseDTO(category.getId(), category.getName(), category.getDescription());
    }

}
