package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    List<ServiceEntity> findServicesByEstablishment_Id(Long establishmentId);
}
