package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    List<Service> findServicesByEstablishment_Id(Long establishmentId);
}
