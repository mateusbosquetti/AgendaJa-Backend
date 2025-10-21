package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.AddressRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.CategoryRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.CategoryResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Address;
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
        return new CategoryResponseDTO(category.getName(), category.getDescription());
    }

}
