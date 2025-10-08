package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.Category;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EstablishmentRepository extends JpaRepository<Establishment, Long>, JpaSpecificationExecutor<Establishment> {
}
