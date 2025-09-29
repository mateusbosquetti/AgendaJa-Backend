package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.ServiceMapper;
import com.mateusbosquetti.agendaja.model.dto.request.ServiceRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import com.mateusbosquetti.agendaja.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository repository;
    private final ServiceProfessionalService serviceProfessionalService;

    public ServiceResponseDTO createService(ServiceRequestDTO requestDTO) {
        ServiceEntity serviceEntity = ServiceEntity.builder()
                .name(requestDTO.name())
                .description(requestDTO.description())
                .durationMinutes(requestDTO.durationMinutes())
                .price(requestDTO.price())
                .establishment(
                        Establishment.builder().id(requestDTO.establishmentId()).build()
                )
                .build();

        serviceEntity = repository.save(serviceEntity);

        return ServiceMapper.toDTO(serviceEntity);
    }

    public ServiceResponseDTO getServiceById(Long id) {
        return repository.findById(id)
                .map(ServiceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public Page<ServiceResponseDTO> getServices(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findAll(pageable).map(ServiceMapper::toDTO);
    }

    public List<ServiceResponseDTO> getServicesByEstablishment(Long establishmentId) {
        return repository.findServicesByEstablishment_Id(establishmentId).stream().map(
                ServiceMapper::toDTO
        ).toList();
    }

    public List<ServiceResponseDTO> getServicesByProfessional(Long professionalId) {
        return this.serviceProfessionalService.getServicesByProfessional(professionalId).stream().map(
                ServiceMapper::toDTO
        ).toList();
    }
}
