package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.entity.Address;
import com.mateusbosquetti.agendaja.model.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
