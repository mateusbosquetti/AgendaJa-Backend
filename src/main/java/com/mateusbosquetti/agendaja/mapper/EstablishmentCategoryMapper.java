package com.mateusbosquetti.agendaja.mapper;

import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentCategoryRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentCategoryResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Category;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.EstablishmentCategory;

public class EstablishmentCategoryMapper {

    public static EstablishmentCategory toEntity(
            EstablishmentCategoryRequestDTO requestDTO
    ) {
        return EstablishmentCategory.builder()
                .establishment(Establishment.builder().id(requestDTO.establishmentId()).build())
                .category(Category.builder().id(requestDTO.categoryId()).build())
                .build();
    }

    public static EstablishmentCategoryResponseDTO toDTO(
            EstablishmentCategory establishmentCategory
    ) {
        return new EstablishmentCategoryResponseDTO(
//                EstablishmentMapper.toDTO(establishmentCategory.getEstablishment()),
                null,
                CategoryMapper.toDTO(establishmentCategory.getCategory())
        );
    }

}
