package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.EstablishmentCategoryMapper;
import com.mateusbosquetti.agendaja.model.compositekey.EstablishmentCategoryId;
import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentCategoryRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.establishment.EstablishmentCategoryResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.EstablishmentCategory;
import com.mateusbosquetti.agendaja.repository.EstablishmentCategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EstablishmentCategoryService {

    private final EstablishmentCategoryRepository repository;

    public EstablishmentCategoryResponseDTO createEstablishmentCategory(EstablishmentCategoryRequestDTO requestDTO) {
        EstablishmentCategory establishmentCategory = EstablishmentCategoryMapper.toEntity(requestDTO);
        establishmentCategory = repository.save(establishmentCategory);
        return EstablishmentCategoryMapper.toDTO(establishmentCategory);
    }


    public List<EstablishmentCategoryResponseDTO> getEstablishmentCategoriesByEstablishmentId(Long establishmentId) {
        List<EstablishmentCategory> establishmentCategories = repository.findAllById_EstablishmentId(establishmentId);
        return establishmentCategories.stream()
                .map(EstablishmentCategoryMapper::toDTO)
                .toList();
    }

    public List<EstablishmentCategoryResponseDTO> getEstablishmentCategoriesByCategory(Long categoryId) {
        List<EstablishmentCategory> establishmentCategories = repository.findAllById_CategoryId(categoryId);
        return establishmentCategories.stream()
                .map(EstablishmentCategoryMapper::toDTO)
                .toList();
    }

    public void deleteEstablishimentCategory(Long establishmentId, Long categoryId) {
        repository.deleteById(EstablishmentCategoryId.builder().establishmentId(establishmentId).categoryId(categoryId).build());
    }
}
