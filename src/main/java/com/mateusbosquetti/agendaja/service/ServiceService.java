package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.ServiceMapper;
import com.mateusbosquetti.agendaja.model.dto.request.service.ServicePUTRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.request.service.ServiceRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.service.ServiceResponseDTO;
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
        ServiceEntity serviceEntity = this.getServiceEntityById(id);
        return ServiceMapper.toDTO(serviceEntity);
    }

    public ServiceEntity getServiceEntityById(Long id) {
        return repository.findById(id)
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

    public void disableService(Long serviceId) {
        repository.deleteById(serviceId);
    }

    public ServiceResponseDTO updateService(Long id, ServicePUTRequestDTO requestDTO) {
        ServiceEntity serviceEntity = this.getServiceEntityById(id);
        serviceEntity.setName(requestDTO.name());
        serviceEntity.setDescription(requestDTO.description());
        serviceEntity.setDurationMinutes(requestDTO.durationMinutes());
        serviceEntity.setPrice(requestDTO.price());

        serviceEntity = repository.save(serviceEntity);
        return ServiceMapper.toDTO(serviceEntity);
    }
}
