package com.mateusbosquetti.agendaja.controller;

import com.mateusbosquetti.agendaja.model.dto.request.EstablishmentCategoryRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.EstablishmentCategoryResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.EstablishmentCategory;
import com.mateusbosquetti.agendaja.service.CategoryService;
import com.mateusbosquetti.agendaja.service.EstablishmentCategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/establishment-categories")
public class EstablishmentCategoryController {

    private final EstablishmentCategoryService service;

    @PostMapping
    public ResponseEntity<EstablishmentCategoryResponseDTO> createEstablishmentCategory(
            @RequestBody EstablishmentCategoryRequestDTO requestDTO
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createEstablishmentCategory(requestDTO));
    }

    @GetMapping("/establishment/{establishmentId}")
    public ResponseEntity<List<EstablishmentCategoryResponseDTO>> getEstablishmentCategoriesByEstablishmentId(
            @PathVariable Long establishmentId
    ) {
        return ResponseEntity.ok(service.getEstablishmentCategoriesByEstablishmentId(establishmentId));
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<List<EstablishmentCategoryResponseDTO>> getEstablishmentCategoriesByCategoryId(
            @PathVariable Long categoryId
    ) {
        return ResponseEntity.ok(service.getEstablishmentCategoriesByCategory(categoryId));
    }

    @DeleteMapping("/establishment/{establishmentId}/category/{categoryId}")
    public ResponseEntity<Void> deleteEstablishimentCategory(
            @PathVariable Long establishmentId,
            @PathVariable Long categoryId
    ) {
        service.deleteEstablishimentCategory(establishmentId, categoryId);
        return ResponseEntity.noContent().build();
    }

}
