package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.ServiceProfessionalMapper;
import com.mateusbosquetti.agendaja.model.dto.request.ServiceProfessionalRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceProfessionalResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import com.mateusbosquetti.agendaja.model.entity.ServiceProfessional;
import com.mateusbosquetti.agendaja.repository.ServiceProfessionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceProfessionalService {

    private final ServiceProfessionalRepository repository;

    public ServiceProfessionalResponseDTO associateProfessionalToService(ServiceProfessionalRequestDTO requestDTO) {
        ServiceProfessional serviceProfessional = ServiceProfessionalMapper.toEntity(requestDTO);
        serviceProfessional = repository.save(serviceProfessional);
        return ServiceProfessionalMapper.toDTO(serviceProfessional);
    }

    public List<ServiceEntity> getServicesByProfessional(Long professionalId) {
        return repository.findServiceProfessionalsById_ProfessionalId(professionalId).stream().map(
                ServiceProfessional::getService
        ).toList();
    }

}
