package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.CategoryMapper;
import com.mateusbosquetti.agendaja.model.dto.request.CategoryRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.category.CategoryResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Category;
import com.mateusbosquetti.agendaja.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public CategoryResponseDTO createCategory(CategoryRequestDTO requestDTO) {
        Category category = CategoryMapper.toEntity(requestDTO);
        category = repository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public List<CategoryResponseDTO> getCategories() {
        return repository.findAll().stream().map(CategoryMapper::toDTO).toList();
    }

    public CategoryResponseDTO getCategoryById(Long id) {
        Category category = this.getCategoryEntityById(id);
        return CategoryMapper.toDTO(category);
    }

    public Category getCategoryEntityById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public CategoryResponseDTO updateCategory(Long id, CategoryRequestDTO requestDTO) {
        Category category = this.getCategoryEntityById(id);
        category.setName(requestDTO.name());
        category.setDescription(requestDTO.description());

        category = repository.save(category);
        return CategoryMapper.toDTO(category);
    }

    public void disableCategory(Long id) {
        getCategoryById(id);
        repository.deleteById(id);
    }
}
