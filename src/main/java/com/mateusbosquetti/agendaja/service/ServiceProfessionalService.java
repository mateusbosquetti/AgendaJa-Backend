package com.mateusbosquetti.agendaja.service;

import com.mateusbosquetti.agendaja.mapper.ServiceProfessionalMapper;
import com.mateusbosquetti.agendaja.model.compositekey.ServiceProfessionalId;
import com.mateusbosquetti.agendaja.model.dto.request.ServiceProfessionalRequestDTO;
import com.mateusbosquetti.agendaja.model.dto.response.ServiceProfessionalResponseDTO;
import com.mateusbosquetti.agendaja.model.entity.Establishment;
import com.mateusbosquetti.agendaja.model.entity.ServiceEntity;
import com.mateusbosquetti.agendaja.model.entity.ServiceProfessional;
import com.mateusbosquetti.agendaja.model.entity.User;
import com.mateusbosquetti.agendaja.repository.ServiceProfessionalRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ServiceProfessionalService {

    private final ServiceProfessionalRepository repository;
    private final UserEstablishmentService userEstablishmentService;
    private final ServiceService serviceService;

    public ServiceProfessionalResponseDTO associateProfessionalToService(ServiceProfessionalRequestDTO requestDTO) {
        ServiceProfessional serviceProfessional = ServiceProfessionalMapper.toEntity(requestDTO);

        ServiceEntity service = serviceService.getServiceEntityById(requestDTO.serviceId());

        //Busca para ver se tem relação do profissional com o estabelecimento
        userEstablishmentService.getUserEstablishmentByUserIdAndEstablishmentId(
                requestDTO.professionalId(),
                service.getEstablishment().getId()
        );

        serviceProfessional = repository.save(serviceProfessional);
        return ServiceProfessionalMapper.toDTO(serviceProfessional);
    }

    public List<ServiceProfessionalResponseDTO> getServicesByProfessional(Long professionalId) {
        List<ServiceProfessional> serviceProfessionalList =  repository.findServiceProfessionalsById_ProfessionalId(professionalId);
        return serviceProfessionalList.stream().map(ServiceProfessionalMapper::toDTO).toList();
    }

    public void removeProfessionalFromService(Long serviceId, Long professionalId) {
        repository.deleteById(ServiceProfessionalId.builder().serviceId(serviceId).professionalId(professionalId).build());
    }
}
