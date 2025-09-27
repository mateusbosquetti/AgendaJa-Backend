package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.ServiceMapper;
import com.mateusbosquetti.agendaja.model.dto.request.ServiceRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.repository.ServiceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceService {

    private final ServiceRepository repository;

    public ServiceResponseDTO createService(ServiceRequestDTO requestDTO) {
        com.mateusbosquetti.agendaja.model.entity.Service service = com.mateusbosquetti.agendaja.model.entity.Service.builder()
                .name(requestDTO.name())
                .description(requestDTO.description())
                .durationMinutes(requestDTO.durationMinutes())
                .price(requestDTO.price())
                .establishment(
                        Establishment.builder().id(requestDTO.establishmentId()).build()
                )
                .build();

        service = repository.save(service);

        return ServiceMapper.toDTO(service);
    }

    public ServiceResponseDTO getServiceById(Long id) {
        return repository.findById(id)
                .map(ServiceMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Service not found"));
    }

    public List<ServiceResponseDTO> getServices() {
        return repository.findAll().stream().map(ServiceMapper::toDTO).toList();
    }

    public List<ServiceResponseDTO> getServicesByEstablishment(Long establishmentId) {
        return repository.findServicesByEstablishment_Id(establishmentId).stream().map(
                ServiceMapper::toDTO
        ).toList();
    }
}
