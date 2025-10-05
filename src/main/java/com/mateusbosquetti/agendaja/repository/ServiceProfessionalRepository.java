package com.mateusbosquetti.agendaja.repository;

import com.mateusbosquetti.agendaja.model.compositekey.ServiceProfessionalId;
import com.mateusbosquetti.agendaja.model.entity.ServiceProfessional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceProfessionalRepository extends JpaRepository<ServiceProfessional, ServiceProfessionalId> {
    List<ServiceProfessional> findServiceProfessionalsById_ProfessionalId(Long idProfessionalId);
}
