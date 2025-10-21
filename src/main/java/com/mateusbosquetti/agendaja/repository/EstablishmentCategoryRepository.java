package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.compositekey.EstablishmentCategoryId;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.EstablishmentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstablishmentCategoryRepository extends JpaRepository<EstablishmentCategory, EstablishmentCategoryId> {
    List<EstablishmentCategory> findAllById_EstablishmentId(Long idEstablishmentId);
    List<EstablishmentCategory> findAllById_CategoryId(Long idCategoryId);
}
